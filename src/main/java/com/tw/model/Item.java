package com.tw.model;

import org.hibernate.annotations.Where;

import com.tw.generics.AbstractPersistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name=("item"))
@Where(clause = "deleted=false")
public class Item extends AbstractPersistable{


	/**
	 *  @author TAUFEEQ
	 */
	private static final long serialVersionUID = -4537563744776365416L;

	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "qyt")
	private int qyt;
	
	@Column(name = "price")
	private double price;
	 
	@Column(name = "amount")
	private double amount;
	
	
}
