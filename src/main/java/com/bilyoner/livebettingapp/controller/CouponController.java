package com.bilyoner.livebettingapp.controller;

import com.bilyoner.livebettingapp.dto.CouponRequestDTO;
import com.bilyoner.livebettingapp.dto.CouponResponseDTO;
import com.bilyoner.livebettingapp.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponResponseDTO> createCoupon(@Valid @RequestBody CouponRequestDTO couponRequestDTO) {
        CouponResponseDTO responseDTO;
        try {
            responseDTO = couponService.createCoupon(couponRequestDTO);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponseDTO> getCouponById(@PathVariable Long id) {
        CouponResponseDTO couponResponseDTO = couponService.getCouponById(id);
        return new ResponseEntity<>(couponResponseDTO, HttpStatus.OK);
    }
}

