package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.dto.CouponResponseDTO;
import com.bilyoner.livebettingapp.entity.Coupon;
import com.bilyoner.livebettingapp.mapper.CouponMapper;
import com.bilyoner.livebettingapp.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponMapper couponMapper;

    @Mock
    private CouponResponseDTO couponResponseDTO;

    @Mock
    private Coupon coupon;

    @Test
    void testGetCouponByIdSuccess() {
        when(couponRepository.findById(anyLong())).thenReturn(Optional.of(coupon));
        when(couponMapper.toDTO(any(Coupon.class))).thenReturn(couponResponseDTO);

        CouponResponseDTO result = couponService.getCouponById(1L);

        assertNotNull(result);
        assertEquals(couponResponseDTO, result);
        verify(couponRepository).findById(1L);
        verify(couponMapper).toDTO(coupon);
    }

    @Test
    void testGetCouponByIdNotFound() {
        when(couponRepository.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> couponService.getCouponById(1L));
        assertEquals("Coupon with ID 1 not found", exception.getMessage());
        verify(couponRepository).findById(1L);
    }
}
