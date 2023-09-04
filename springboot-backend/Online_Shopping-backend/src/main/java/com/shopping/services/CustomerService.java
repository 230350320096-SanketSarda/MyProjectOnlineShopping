package com.shopping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.daos.CustomerDao;
import com.shopping.dtos.Credentials;
import com.shopping.dtos.CustomerDto;
import com.shopping.dtos.DaoToEntityConverter;
import com.shopping.entities.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> findAllCustomers() {
		return customerDao.findAll();
	}
	
	public Optional<Customer> getCustomerById(int id) {
		return customerDao.findById(id);
	}
	
	public CustomerDto getCustomerDtoById (int id) {
		Optional<Customer> cust = getCustomerById(id);
		Customer c = null;
		try {
			c = cust.get();
		} catch (Exception e) {
			c = null;
			return null;
		}
		
		CustomerDto custDto = DaoToEntityConverter.customerEntityToDto(c);
		return custDto;
	}
	
	public boolean saveCustomer(Customer cust) {
		customerDao.save(cust);
		return true;
	}
	
	public CustomerDto findCustomerByEmailAndPassword(Credentials cred) {
		Customer cust = customerDao.findByEmail(cred.getEmail());
		if(cust == null || !cust.getPassword().equals(cred.getPassword()))
			return null;
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(cust, customerDto, "password");
		return customerDto;
	}
	
	public boolean updateAddressByCustomerId(int id, String addressText, int pinCode) {
		
		try {
			Customer cust = customerDao.getById(id);
			cust.setAddressText(addressText);
			cust.setPinCode(pinCode);
			customerDao.save(cust);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
