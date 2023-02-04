package com.example.customer.repository;

import com.example.customer.entity.CustomerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, Long> {
    List<CustomerCoupon> findByCustomerId(Long customerId);
    CustomerCoupon findByCustomerIdAndCouponId(Long customerId, String couponId);
}
