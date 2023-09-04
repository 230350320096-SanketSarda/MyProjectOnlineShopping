package com.shopping.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.daos.OrdersDao;
import com.shopping.daos.RestaurantDao;
import com.shopping.daos.RestaurantManagerDao;
import com.shopping.dtos.Credentials;
import com.shopping.dtos.CustomerDto;
import com.shopping.dtos.DaoToEntityConverter;
import com.shopping.dtos.RestaurantManagerDto;
import com.shopping.dtos.RestaurantManagerHomePageDto;
import com.shopping.entities.Customer;
import com.shopping.entities.OrderItem;
import com.shopping.entities.Orders;
import com.shopping.entities.Restaurant;
import com.shopping.entities.RestaurantManager;

@Service
public class RestaurantManagerService {

	@Autowired
	private RestaurantManagerDao restaurantManagerDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	public List<RestaurantManager> findAllRestaurantManagers() {
		return restaurantManagerDao.findAll();
	}
	
	public Optional<RestaurantManager> getRestaurantManagerById(int id)
	{
		return restaurantManagerDao.findById(id);
	}
	
	public RestaurantManagerDto getRestaurantManagerDtoById(int id)
	{
		Optional<RestaurantManager> rest=getRestaurantManagerById(id);
		RestaurantManager r =null;
		try {
			r=rest.get();
		}
		catch(Exception e){
			r=null;
			return null;
		}
		RestaurantManagerDto restDto=DaoToEntityConverter.RestaurantManagerEntityToDto(r);
		return restDto;
		
	}
	
     public boolean saveRestaurantManager(RestaurantManager rest)
     {
    	 restaurantManagerDao.save(rest);
    	 return true;
     }

     public RestaurantManagerDto findRestaurantManagerByEmailAndPassword(Credentials cred) {
    	 RestaurantManager rest= restaurantManagerDao.findByEmail(cred.getEmail());
 		if(rest == null || !rest.getPassword().equals(cred.getPassword()))
 			return null;
 		RestaurantManagerDto restaurantManagerDto = DaoToEntityConverter.RestaurantManagerToRestaurantmanagerDto(rest);
 		return restaurantManagerDto;
 	}
     
//     public List<RestaurantManagerHomePageDto> getAllArrivedOrdersFromRestaurant(int restaurantId,String status)
//     {
//    	 Restaurant restId=null;
//		    try {
//		    	restId=restaurantDao.findById(restaurantId).get();
//		    }
//		    catch(Exception e)
//		    {
//		    	return null;
//		    }
//		    
//		    List<Orders> orders=ordersDao.findOrdersByRestaurantId(restaurantId, status);
//		    
//		    List<RestaurantManagerHomePageDto> restDto= new ArrayList<RestaurantManagerHomePageDto>();
//		    
//		  //  orders.forEach(order->restDto.add(DaoToEntityConverter.toRestaurantManagerHomePageDto(order)));
//		    
//			return null;
//     }
     
}
