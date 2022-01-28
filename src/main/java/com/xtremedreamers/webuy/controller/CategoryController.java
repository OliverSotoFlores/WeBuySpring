package com.xtremedreamers.webuy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xtremedreamers.webuy.models.Category;
import com.xtremedreamers.webuy.models.Product;
import com.xtremedreamers.webuy.persistence.CategoryDao;
import com.xtremedreamers.webuy.persistence.ProductDao;
import com.xtremedreamers.webuy.shared.PagedList;

@Controller
public class CategoryController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping("/home")
	public String categoryList(Model model) {
		List<Category> categories = categoryDao.findAll();
		model.addAttribute("categories", categories);
		return "index";
	}
	@RequestMapping("/homeadmin")
	public String homeadmin() {
		return "index";
	}
	
	@RequestMapping("/mainview/{category_name}")
	public String categorySearch(
			Model model,
			@PathVariable String category_name) {
		String category = category_name;

		List<Product> listSortedProducts = productDao.findByCategory(category_name);
		model.addAttribute("category_name", category);
		model.addAttribute("listProducts", listSortedProducts);
		
		return "mainView";
	}

}
