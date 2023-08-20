package com.tw.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.model.Count;
import com.tw.model.Customer;
import com.tw.repository.CountRepository;
import com.tw.repository.CustomerRepository;
import com.tw.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	CountRepository countRepo;

	@Override
	public String saveData(Customer customer) {
		Customer c = customerRepo.save(customer);
		if (c != null) {
			Count obj = countRepo.getOne(1L);
			obj.setCount(obj.getCount() + 1);
			countRepo.save(obj);
		}
		return "Save Success ";
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> cus = customerRepo.findById(id);
		return cus.get();
	}

	@Override
	public String customerDelete(Long id) {
		Customer c=customerRepo.getOne(id);
		c.setDeleted(true);
		customerRepo.save(c);
		return "Deleted Successfully";
	}

//	@Override
//	public String saveData(CustomerDto dto) {
//		logger.info("Creating user: " + dto);
//		Customer c = new Customer();
//		if (dto.getId() != null) {
//			c = customerRepo.getOne(dto.getId());
//		}
//		c.setId(dto.getId());
//		c.setCustomerName(dto.getCustomerName());
//		c.setAddress(dto.getAddress());
//		c.setMobileNumber(dto.getMobileNumber());
//		customerRepo.save(c);
//		return ("ok");;
//	}

}
