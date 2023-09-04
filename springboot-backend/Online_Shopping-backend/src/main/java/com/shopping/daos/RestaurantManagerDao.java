package com.shopping.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entities.RestaurantManager;

public interface RestaurantManagerDao extends JpaRepository<RestaurantManager, Integer> {
        
	RestaurantManager findByEmail(String email);
	
	
}
