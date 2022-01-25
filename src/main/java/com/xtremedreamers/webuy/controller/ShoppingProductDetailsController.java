package com.xtremedreamers.webuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.xtremedreamers.webuy.persistence.ShoppingProductDetailsDao;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ShoppingProductDetailsController {
	
	@Autowired
	ShoppingProductDetailsDao shoppingPDDao;
	
	@PostMapping("/shopping-cart/add")
	public String addProduct(Model model) {
		return null;
	}
}
