package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {	
	

}
