package com.xtremedreamers.webuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.Product;
import com.xtremedreamers.webuy.persistence.ProductDao;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Controller
public class ProductsController {
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping("/")
	public String ProductsList() {
		List<Product> products = productDao.findAll();
		System.out.println(products);
		return "mainView";
	}
}
