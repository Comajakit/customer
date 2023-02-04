package com.example.customer.service;

import com.example.customer.entity.Customer;
import com.example.customer.entity.CustomerCoupon;
import com.example.customer.repository.CustomerCouponRepository;
import com.example.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCouponServiceImpl implements CustomerCouponService {

    private final CustomerCouponRepository customerCouponRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerCoupon> findByCustomerId(Long customerId) {
        return customerCouponRepository.findByCustomerId(customerId);
    }

    @Override
    public CustomerCoupon findByCustomerIdAndCouponId(Long customerId, String couponId) {
        return customerCouponRepository.findByCustomerIdAndCouponId(customerId, couponId);
    }

    @Override
    public CustomerCoupon saveCustomerCoupon(CustomerCoupon customerCoupon) {
        Long customerId = customerCoupon.getCustomer().getId();
        String couponId = customerCoupon.getCouponId();
        int count = customerCoupon.getCount();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return null;
        }
        CustomerCoupon newCustomerCoupon = new CustomerCoupon();
        newCustomerCoupon.setCustomer(customer);
        newCustomerCoupon.setCouponId(couponId);
        newCustomerCoupon.setCount(count);

        return customerCouponRepository.save(newCustomerCoupon);
    }

    @Override
    public void deleteCustomerCoupon(Long customerId, String couponId) {
        CustomerCoupon customerCoupon = customerCouponRepository.findByCustomerIdAndCouponId(customerId, couponId);
        if (customerCoupon != null) {
            customerCouponRepository.delete(customerCoupon);
        }
    }

    @Override
    public Boolean checkDuplicate(CustomerCoupon customerCoupon) {
        Long customerId = customerCoupon.getCustomer().getId();
        String couponId = customerCoupon.getCouponId();
        CustomerCoupon existingCoupon = customerCouponRepository.findByCustomerIdAndCouponId(customerId, couponId);

        if (existingCoupon != null) {
            System.out.println("found!!");
            return true;
        }else{
            System.out.println("not found!!!");
            return false;
        }
    }

    @Override
    public CustomerCoupon updateCustomerCoupon(Long customerId, String couponId, int count) {
        CustomerCoupon customerCoupon = customerCouponRepository.findByCustomerIdAndCouponId(customerId, couponId);
        if (customerCoupon == null) {
            return null;
        }

        customerCoupon.setCount(count);
        return customerCouponRepository.save(customerCoupon);
    }
}
