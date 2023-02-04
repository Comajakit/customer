package com.example.customer.service;

import com.example.customer.entity.Customer;

import java.util.List;


public interface CustomerService {
    List<Customer> findAllCustomers();
    Customer findCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer updatedCustomer);
    void deleteCustomer(Long id);
}
