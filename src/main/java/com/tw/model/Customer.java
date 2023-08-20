package com.tw.model;

import java.util.List;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tw.generics.AbstractPersistable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name=("customer"))
@Where(clause = "deleted=false")
public class Customer extends AbstractPersistable{
	/**
	 * @author FAIZAN
	 */
	private static final long serialVersionUID = 1276475713662172599L;

	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "gst_number")
	private String gstNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_item", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	
	private List<Item> item;
	 
}
