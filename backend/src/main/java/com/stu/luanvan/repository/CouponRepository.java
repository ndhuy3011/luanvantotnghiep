package com.stu.luanvan.repository;

import com.stu.luanvan.model.coupon.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<CouponModel,Integer> {
}
