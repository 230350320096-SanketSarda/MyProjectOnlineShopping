package com.shopping.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entities.DeliveryPerson;

@Repository
public interface DeliveryPersonDao extends JpaRepository<DeliveryPerson, Integer> {

	DeliveryPerson findByEmail(String email);
	
	List<DeliveryPerson> findByIsAvailable(boolean status);
}
