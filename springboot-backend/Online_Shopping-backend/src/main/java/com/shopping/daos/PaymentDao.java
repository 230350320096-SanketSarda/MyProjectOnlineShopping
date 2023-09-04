package com.shopping.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entities.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

}
