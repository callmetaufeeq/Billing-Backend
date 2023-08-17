package com.tw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tw.model.Customer;
import com.tw.model.Item;
import com.tw.service.ItemService;

import jakarta.websocket.server.PathParam;
@RestController
@RequestMapping("/item")
@CrossOrigin
public class Itemcontroller {
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/save")
	public String saveData(@RequestBody Item item) {
		itemService.saveData(item);
		return "Save Success";
	}
	@GetMapping("/listitem")
	public List<Item> getItems() {
		return itemService.getItems();
	}
	
	@GetMapping("/customerById")
	public Item getCustomerById(@PathParam("id") Long id) {
		return itemService.getItemById(id);
	}

	@GetMapping("/deletebyid")
	public String deleteByID(@PathParam("id") Long id) {
		return itemService.delete(id);
	}
	

}
