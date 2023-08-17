package com.tw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.model.Count;
import com.tw.repository.CountRepository;
import com.tw.service.CountService;

@Service
public class CountServiceImpl implements CountService {
	
	@Autowired
	CountRepository countRepo;
	
	@Override
	@SuppressWarnings("deprecation")
	public String countInvoice() {
		Count obj = countRepo.getOne(1L);
		int i=obj.getCount();
		String s ="INV-"+i;
		obj.setCount(i+1);
		countRepo.save(obj);
		return s;
	}


}
