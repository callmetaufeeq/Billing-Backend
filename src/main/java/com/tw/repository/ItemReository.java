package com.tw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.model.Item;

public interface ItemReository extends JpaRepository<Item, Long>{
	

}
