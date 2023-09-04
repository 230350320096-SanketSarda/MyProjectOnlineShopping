package com.shopping.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entities.FoodType;

public interface FoodTypeDao extends JpaRepository<FoodType, Integer> {

}
