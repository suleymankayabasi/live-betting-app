package com.bilyoner.livebettingapp.repository;

import com.bilyoner.livebettingapp.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {


}
