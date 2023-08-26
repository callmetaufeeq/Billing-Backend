package com.tw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tw.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> ,JpaSpecificationExecutor<Customer>{	
	
	public List<Customer> findAllByOrderByIdDesc();

	public Customer findByCustomerId(Long id);

}
