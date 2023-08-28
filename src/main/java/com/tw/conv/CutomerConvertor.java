package com.tw.conv;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.tw.dto.CustomerDto;
import com.tw.dto.ItemDto;
import com.tw.model.Customer;
import com.tw.model.Item;

public class CutomerConvertor implements Function<Customer, CustomerDto> {

	@Override
	public CustomerDto apply(Customer t) {
		CustomerDto obj=new CustomerDto();
		obj.setId(t.getId());
		obj.setAddress(t.getAddress());
		obj.setCustomerName(t.getCustomerName());
		obj.setGstNumber(t.getGstNumber());
		obj.setInvoiceDate(t.getInvoiceDate());
		obj.setInvoiceNumber(t.getInvoiceNumber());
		obj.setMobileNumber(t.getMobileNumber());
		List<ItemDto> listdto=new ArrayList<>();
		for (Item i : t.getItem()) {
			ItemDto item=new ItemDto();
			item.setId(i.getId());
			item.setAmount(i.getAmount());
			item.setItemId(i.getItemId());
			item.setItemName(i.getItemName());
			item.setPrice(i.getPrice());
			item.setQuantity(i.getQuantity());
			listdto.add(item);
		}
		obj.setItem(listdto);
		return obj;
	}

}
