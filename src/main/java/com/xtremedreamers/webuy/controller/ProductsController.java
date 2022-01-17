package com.xtremedreamers.webuy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xtremedreamers.webuy.models.Product;
import com.xtremedreamers.webuy.persistence.ProductDao;
import com.xtremedreamers.webuy.shared.PagedList;

@Controller
public class ProductsController {

	@Autowired
	ProductDao productDao;

	@RequestMapping("/")
	public String ProductsList() {
		List<Product> products = productDao.findAll();
		System.out.println(products);
		return "index";
	}

	@GetMapping("/products")
	public String getProducts(Model model) {
		List<Product> listProducts = productDao.getPagination(1, 5);
		model.addAttribute("listProducts", listProducts);
		return "index";
	}

	@RequestMapping(value = "/mainview")
	public String productsPaginated(
			Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		List<Product> listProducts = productDao.getPagination(currentPage, pageSize);
		PagedList<Product> listProductsMejorado = PagedList.toPagedList(productDao, currentPage, pageSize);
		model.addAttribute("paginationData", listProductsMejorado.getMetaData());
		model.addAttribute("listProducts", listProducts);

		return "mainView";
	}
}
