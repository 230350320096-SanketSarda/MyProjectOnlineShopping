package com.shopping.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.daos.PaymentDao;
import com.shopping.entities.Payment;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	
	public List<Payment> findAllPayments() {
		return paymentDao.findAll();
	}
}
