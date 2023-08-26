package com.tw.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tw.dto.CustomerDto;
import com.tw.dto.CustomerSpecDto;

@Service
public interface CustomerService {

	ResponseEntity<?> saveData(CustomerDto dto);

	ResponseEntity<?> getCustomers();

	ResponseEntity<?> getCustomerById(Long id);

	ResponseEntity<?> customerDelete(Long id);

	ResponseEntity<?> findAllwithpage(CustomerSpecDto custSpeDto);

}
