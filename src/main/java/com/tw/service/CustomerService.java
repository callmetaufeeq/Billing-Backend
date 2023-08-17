package com.tw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tw.model.Customer;
@Service
public interface CustomerService {

	String saveData(Customer dto);
	List<Customer> getCustomers();
	Customer getCustomerById(Long id);
	String customerDelete(Long id);
	

}
