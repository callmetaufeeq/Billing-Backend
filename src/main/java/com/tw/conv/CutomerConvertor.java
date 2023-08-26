package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.CustomerDto;
import com.tw.model.Customer;

public class CutomerConvertor implements Function<Customer, CustomerDto> {

	@Override
	public CustomerDto apply(Customer t) {
		CustomerDto obj=new CustomerDto();
		return obj;
	}

}
