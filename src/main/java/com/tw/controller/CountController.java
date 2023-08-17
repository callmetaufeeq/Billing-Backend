package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.model.Customer;
import com.tw.service.CountService;
import com.tw.service.CustomerService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/count")
@CrossOrigin
public class CountController {

	@Autowired
	CountService countService;
	
	@GetMapping("/counts")
	 public String countInvoice(@PathParam("id")Long id) {
		return countService.countInvoice();
		
	}


}
