package com.tw.dto;

import java.util.Calendar;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	private Long id;
	private String customerName;
	private String address;
	private String mobileNumber;
	private String gstNumber;
	private Calendar invoiceDate;
	private String invoiceNumber;
	private List<ItemDto> item;
	
	
}
