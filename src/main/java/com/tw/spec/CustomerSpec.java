package com.tw.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.model.Customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class CustomerSpec implements Specification<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String customerName;
	private String mobileNumber;
	private String gstNumber;
	private String invoiceNumber;
	//private Calendar invoiceDate;
	
	public CustomerSpec(String customerName, String mobileNumber, String gstNumber, String invoiceNumber) {
		super();
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.gstNumber = gstNumber;
		this.invoiceNumber = invoiceNumber;
	}
	
	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate conjunction = cb.conjunction();
		
		if( StringUtils.isNotEmpty( this.customerName ) ) {
			conjunction.getExpressions().add(cb.like(root.get("customerName"),"%"+ this.customerName ));
		}
		if( StringUtils.isNotEmpty( this.mobileNumber ) ) {
			conjunction.getExpressions().add( cb.like( root.get("mobileNumber") , "%" + this.mobileNumber +"%" ) );
		}
		if( StringUtils.isNotEmpty( this.gstNumber ) ) {
			conjunction.getExpressions().add( cb.like( root.get("gstNumber") , "%" + this.gstNumber +"%" ) );
		}
		if( StringUtils.isNotEmpty( this.invoiceNumber ) ) {
			conjunction.getExpressions().add( cb.like( root.get("invoiceNumber") , "%" + this.invoiceNumber +"%" ) );
		}
//		if( this.invoiceDate !=null ) {
//			conjunction.getExpressions().add( cb.like( root.get("invoiceDate") , "%" + this.invoiceDate +"%" ) );
//		}
		return conjunction;
	}
	

}
