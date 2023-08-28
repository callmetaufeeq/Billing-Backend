package com.tw.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.tw.dto.ItemDto;
import com.tw.dto.PageDto;
import com.tw.generics.AppConstants;
import com.tw.generics.Code;
import com.tw.generics.Response;
import com.tw.model.Count;
import com.tw.model.Customer;
import com.tw.model.Item;
import com.tw.repository.CountRepository;
import com.tw.repository.CustomerRepository;
import com.tw.repository.ItemReository;
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
	private ItemReository itemrepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> saveData(CustomerDto dto) {
		logger.info("save cutomer details");
		if (dto != null) {
			Customer obj = new Customer();
			if (dto.getId() != null && dto.getId() > 0) {
				obj = customerRepo.getById(dto.getId());
				obj.setId(dto.getId());
				obj.setModified(Calendar.getInstance());
			} else {
				obj.setModified(Calendar.getInstance());
				obj.setCreated(Calendar.getInstance());
			}
			obj.setAddress(dto.getAddress());
			obj.setCustomerName(dto.getCustomerName());
			obj.setGstNumber(dto.getGstNumber());
			obj.setInvoiceNumber(dto.getInvoiceNumber());
			obj.setInvoiceDate(dto.getInvoiceDate());
			obj.setMobileNumber(dto.getMobileNumber());
			List<Item> list = new ArrayList<>();
			for (ItemDto i : dto.getItem()) {
				Item itemObj = new Item();
				if (i.getId() != null && i.getId() > 0) {
					itemObj = itemrepo.getById(i.getId());
					itemObj.setId(i.getId());
					itemObj.setModified(Calendar.getInstance());
				} else {
					itemObj.setModified(Calendar.getInstance());
					itemObj.setCreated(Calendar.getInstance());
				}
				itemObj.setAmount(i.getAmount());
				itemObj.setItemId(i.getItemId());
				itemObj.setItemName(i.getItemName());
				itemObj.setPrice(i.getPrice());
				itemObj.setQuantity(i.getQuantity());
				list.add(itemObj);
			}
			obj.setItem(list);
			Customer c = customerRepo.save(obj);
			if (c != null) {
				Count count = countRepo.getOne(1L);
				count.setCount(count.getCount() + 1);
				countRepo.save(count);
			}
		}
		if (dto.getId() != null && dto.getId() > 0) {
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
		Customer c = customerRepo.getOne(id);
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
		PageRequest pg = PageRequest.of(custSpeDto.getPage() - 1, custSpeDto.getSize(), Direction.DESC,
				AppConstants.MODIFIED);

		Page<Customer> af = customerRepo.findAll(new CustomerSpec(custSpeDto.getCustomerName(),
				custSpeDto.getMobileNumber(), custSpeDto.getGstNumber(), custSpeDto.getInvoiceNumber()), pg);
		List<CustomerDto> list = af.stream().map(new CutomerConvertor()).collect(Collectors.toList());
		// List<Customer> list = af.stream().collect(Collectors.toList());
		PageDto pageDto = new PageDto(list, af.getTotalElements());
		return Response.build(Code.OK, pageDto);
	}

}
