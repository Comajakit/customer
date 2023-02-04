package com.example.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String status;
    private String phoneNumber;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<CustomerCoupon> customerCoupons;
}