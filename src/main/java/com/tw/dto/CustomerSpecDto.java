package com.tw.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerSpecDto {
	
	private String customerName;
	private String mobileNumber;
	private String gstNumber;
	private String invoiceNumber;
	private Calendar invoiceDate;
	private Integer page;
	private Integer size;

}
