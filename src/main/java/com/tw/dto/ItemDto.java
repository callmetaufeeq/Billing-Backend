package com.tw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

	private Long id;
	private String itemId;
	private String itemName;
	private int quantity;
	private double price;
	private double amount;
}
