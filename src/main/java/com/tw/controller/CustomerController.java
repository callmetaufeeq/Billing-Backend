package com.tw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.model.Customer;
import com.tw.service.CustomerService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/save")
	public String saveData(@RequestBody Customer customer) {
		return customerService.saveData(customer);
	}

	@GetMapping("/listcustomer")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping("/customerById")
	public Customer getCustomerById(@PathParam("id") Long id) {
		return customerService.getCustomerById(id);
	}

	@GetMapping("/deletebyid")
	public String deleteByID(@PathParam("id") Long id) {
		return customerService.customerDelete(id);
	}
}
