package com.shopping.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entities.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer>{

}
