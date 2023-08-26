package com.tw.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tw.generics.Messages;
import com.tw.conv.CutomerConvertor;
import com.tw.dto.CustomerDto;
import com.tw.dto.CustomerSpecDto;
import com.tw.dto.PageDto;
import com.tw.generics.AppConstants;
import com.tw.generics.Code;
import com.tw.generics.Response;
import com.tw.model.Count;
import com.tw.model.Customer;
import com.tw.repository.CountRepository;
import com.tw.repository.CustomerRepository;
import com.tw.service.CustomerService;
import com.tw.spec.CustomerSpec;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CountRepository countRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> saveData(CustomerDto customer) {
		logger.info("save cutomer details");
		if (customer != null) {
			Customer cobj = modelMapper.map(customer, Customer.class);
			Customer c = customerRepo.save(cobj);
			if (c != null) {
				Count obj = countRepo.getOne(1L);
				obj.setCount(obj.getCount() + 1);
				countRepo.save(obj);
			}
		}
		if (customer.getId() != null && customer.getId() > 0) {
			return Response.build(Code.CREATED, Messages.UPDATED);
		} else {
			return Response.build(Code.CREATED, Messages.SAVED);
		}
	}

	@Override
	public ResponseEntity<?> getCustomers() {
		List<Customer> list = customerRepo.findAllByOrderByIdDesc();
		List<CustomerDto> dto = list.stream().map(new CutomerConvertor()).collect(Collectors.toList());
		return Response.build(Code.OK, dto);
	}

	@Override
	public ResponseEntity<?> getCustomerById(Long id) {
		Customer c = customerRepo.findByCustomerId(id);
		CustomerDto dto = modelMapper.map(c, CustomerDto.class);
		return Response.build(Code.OK, dto);
	}

	@Override
	public ResponseEntity<?> customerDelete(Long id) {
		Customer c = new Customer();
		if (id != null) {
			c = customerRepo.getOne(id);
			c.setDeleted(true);
			customerRepo.save(c);
		}
		return Response.build(Code.OK, Messages.DELETED);
	}

	@Override
	public ResponseEntity<?> findAllwithpage(CustomerSpecDto custSpeDto) {
		logger.info("showing list of customer " + custSpeDto);
		PageRequest pg = PageRequest.of(custSpeDto.getPage(), custSpeDto.getSize(), Direction.DESC,
				AppConstants.MODIFIED);

		Page<Customer> af = customerRepo
				.findAll(
						new CustomerSpec(custSpeDto.getCustomerName(), custSpeDto.getMobileNumber(),
								custSpeDto.getGstNumber(), custSpeDto.getInvoiceNumber(), custSpeDto.getInvoiceDate()),
						pg);
		List<CustomerDto> list = af.stream().map(new CutomerConvertor()).collect(Collectors.toList());
		// List<Customer> list = af.stream().collect(Collectors.toList());
		PageDto pageDto = new PageDto(list, af.getTotalElements());
		return Response.build(Code.OK, pageDto);
	}

}
