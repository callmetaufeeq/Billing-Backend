package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.model.Item;

public interface ItemReository extends JpaRepository<Item, Long>{

}
