package com.example.customer.service;

import com.example.customer.entity.CustomerCoupon;

import java.util.List;


public interface CustomerCouponService {

    List<CustomerCoupon> findByCustomerId(Long customerId);

    CustomerCoupon findByCustomerIdAndCouponId(Long customerId, String couponId);

    CustomerCoupon saveCustomerCoupon(CustomerCoupon customerCoupon);

    void deleteCustomerCoupon(Long customerId, String couponId);
    Boolean checkDuplicate(CustomerCoupon customerCoupon);

    CustomerCoupon updateCustomerCoupon(Long customerId, String couponId, int count);
}

