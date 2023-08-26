package com.tw.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.model.Item;
import com.tw.repository.ItemReository;
import com.tw.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemReository itemReository;

	@Override
	public String saveData(Item item) {
		itemReository.save(item);
		return "Save success";
	}

	@Override
	public List<Item> getItems() {

		return itemReository.findAll();
	}

	@Override
	public String delete(Long id) {
		itemReository.deleteById(id);
		return "Deleted Successfuly";
	}

	@Override
	public Item getItemById(Long id) {
		return itemReository.getById(id);
	}

}
