package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.CustomerDto;
import com.tw.dto.CustomerSpecDto;
import com.tw.service.CustomerService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/save")
	public ResponseEntity<?> saveData(@RequestBody CustomerDto customer) {
		return customerService.saveData(customer);
	}

	@GetMapping("/listcustomer")
	public ResponseEntity<?>  getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping("/customerById")
	public ResponseEntity<?> getCustomerById(@PathParam("id") Long id) {
		return customerService.getCustomerById(id);
	}

	@GetMapping("/deletebyid")
	public ResponseEntity<?> deleteByID(@PathParam("id") Long id) {
		return customerService.customerDelete(id);
	}

	@PostMapping(value = "/findallwithpage")
	public ResponseEntity<?> findAllwithpage(@RequestBody CustomerSpecDto custSpeDto) {
		return customerService.findAllwithpage(custSpeDto);
	}
}
