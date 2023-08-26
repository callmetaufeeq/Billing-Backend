package com.tw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {	
	
	public List<Customer> findAllByOrderByIdDesc();

}
