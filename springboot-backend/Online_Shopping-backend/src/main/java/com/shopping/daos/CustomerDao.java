package com.shopping.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entities.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	Customer findByEmail(String email);
}
