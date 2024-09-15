package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.dto.CouponRequestDTO;
import com.bilyoner.livebettingapp.dto.CouponResponseDTO;
import com.bilyoner.livebettingapp.entity.Coupon;
import com.bilyoner.livebettingapp.mapper.CouponMapper;
import com.bilyoner.livebettingapp.repository.CouponRepository;
import com.bilyoner.livebettingapp.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.*;

@RequiredArgsConstructor
@Service
public class CouponService {

    private static final int MAX_COUPONS_PER_MATCH = 500;
    private final ExecutorService executor = Executors.newFixedThreadPool(100);
    private final CouponRepository couponRepository;
    private final CouponMapper mapper;
    private final MatchRepository matchRepository;

    @Value("${coupon.creation.timeout.seconds}")
    private int timeoutSeconds;

    @Transactional
    public CouponResponseDTO createCoupon(CouponRequestDTO couponRequest) throws TimeoutException {
        Future<CouponResponseDTO> future = executor.submit(() -> processCouponRequest(couponRequest));

        try {
            return future.get(timeoutSeconds, TimeUnit.SECONDS);  // Configurable timeout
        } catch (TimeoutException e) {
            throw new TimeoutException("Coupon creation timed out!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Coupon creation was interrupted", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("An error occurred during coupon creation", e.getCause());
        }
    }

    public CouponResponseDTO getCouponById(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon with ID " + couponId + " not found"));
        return mapper.toDTO(coupon);
    }

    private CouponResponseDTO processCouponRequest(CouponRequestDTO couponRequest) {

        couponRequest.getBets().forEach(betRequest -> {
            long couponCount = couponRequest.getRepetitionCount();
            if (couponCount > MAX_COUPONS_PER_MATCH) {
                throw new IllegalArgumentException("Match has reached the limit of 500 coupons.");
            }
        });

        Coupon coupon = mapper.toEntity(couponRequest);
        double totalOdds = calculateTotalOdds(couponRequest);
        double stake = coupon.getStake();
        int repetitionCount = coupon.getRepetitionCount();
        coupon.setTotalOdds(totalOdds);
        coupon.setPotentialWinnings(calculatePotentialWinnings(stake, repetitionCount, totalOdds));
        Coupon savedCoupon = couponRepository.save(coupon);
        return mapper.toDTO(savedCoupon);
    }

    private double calculateTotalOdds(CouponRequestDTO requestDTO) {
        return requestDTO.getBets().stream()
                .mapToDouble(betRequest -> {
                    var match = matchRepository.findById(betRequest.getMatchId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid match ID: " + betRequest.getMatchId()));
                    return match.getOddsForOutcome(betRequest.getSelectedOutcome());
                })
                .sum();
    }

    private double calculatePotentialWinnings(double stake, double totalOdds, double repetitionCount) {
        return stake * totalOdds * repetitionCount;
    }
}
