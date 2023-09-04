package com.shopping.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.daos.FoodTypeDao;
import com.shopping.dtos.DaoToEntityConverter;
import com.shopping.dtos.FoodTypeDto;
import com.shopping.entities.FoodType;

@Service
public class FoodTypeService {

	@Autowired
	private FoodTypeDao foodTypeDao;
	
	public List<FoodTypeDto> findAllFoodTypes(){
		List<FoodType> foodTypeList = foodTypeDao.findAll();
		List<FoodTypeDto> foodtypeDtolist = new ArrayList<FoodTypeDto>();
				
		foodTypeList.forEach(foodType -> foodtypeDtolist.add(DaoToEntityConverter.FoodTypeToFoodTypeDto(foodType)));
		return foodtypeDtolist;
	}
}
