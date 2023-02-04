package com.example.customer.controller;

import com.example.customer.entity.CustomerCoupon;
import com.example.customer.service.CustomerCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers/coupons")
public class CustomerCouponController {

    private final CustomerCouponService customerCouponService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomerCoupon>> findByCustomerId(@PathVariable Long customerId) {
        List<CustomerCoupon> customerCoupons = customerCouponService.findByCustomerId(customerId);
        if (customerCoupons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerCoupons);
    }

    @GetMapping("/check/{customerId}/{couponId}")
    public ResponseEntity<CustomerCoupon> findByCustomerIdAndCouponId(@PathVariable Long customerId, @PathVariable String couponId) {
        CustomerCoupon customerCoupon = customerCouponService.findByCustomerIdAndCouponId(customerId, couponId);
        if (customerCoupon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerCoupon);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> saveCustomerCoupon(@RequestBody CustomerCoupon customerCoupon) {
        if (customerCouponService.checkDuplicate(customerCoupon)) {
            return ResponseEntity.badRequest().body("Duplicate CouponId");
        }
        CustomerCoupon savedCustomerCoupon = customerCouponService.saveCustomerCoupon(customerCoupon);

        if (savedCustomerCoupon == null) {
            return ResponseEntity.badRequest().body("Customer Not Found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerCoupon);

    }

    @DeleteMapping("/{customerId}/{couponId}")
    public ResponseEntity<Void> deleteCustomerCoupon(@PathVariable Long customerId, @PathVariable String couponId) {
        customerCouponService.deleteCustomerCoupon(customerId, couponId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{customerId}/{couponId}")
    public ResponseEntity<CustomerCoupon> updateCustomerCouponCount(@PathVariable Long customerId, @PathVariable String couponId, @RequestBody CustomerCoupon customerCoupon) {
        CustomerCoupon updatedCustomerCoupon = customerCouponService.updateCustomerCoupon(customerId, couponId, customerCoupon.getCount());
        if (updatedCustomerCoupon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomerCoupon);
    }
}
