package com.tw.service;

import java.util.List;

import com.tw.model.Customer;
import com.tw.model.Item;

public interface ItemService {
	String saveData(Item item);

	List<Item> getItems();

	String delete(Long id);

	Item getItemById(Long id);
	

}
