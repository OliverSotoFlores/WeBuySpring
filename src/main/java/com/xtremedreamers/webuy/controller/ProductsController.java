package com.xtremedreamers.webuy.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xtremedreamers.webuy.models.Category;
import com.xtremedreamers.webuy.models.Product;
import com.xtremedreamers.webuy.persistence.CategoryDao;
import com.xtremedreamers.webuy.persistence.ProductDao;
import com.xtremedreamers.webuy.shared.PagedList;

@Controller
public class ProductsController {

	@Autowired
	ProductDao productDao;

	@Autowired
	CategoryDao categoryDao;

	@RequestMapping("/")
	public String ProductsList(HttpServletRequest request, HttpSession session) {
		request.setAttribute("session", session.getAttribute("user"));
		List<Product> products = productDao.findAll();
		return "index";
	}

	@PostMapping("/products/new")
	public String createProduct(Model model) {
		List<Category> categories = categoryDao.findAll();
		model.addAttribute("categories", categories);
		return null;
	}

	// Create a new Product
	/*
	 * Check BigDecimal Parse
	 */
	@PostMapping("/admin/products/create")
	public String CreateProduct(@ModelAttribute Product product) {

		productDao.save(product);
		
		return "redirect:/admin/products";
	}

	// Delete a Product
	@RequestMapping("/deleteProduct/{id}")
	public String DeleteProduct(@PathVariable int id) {
		// int product_id = (productDao.getLastId()) - 1;
		// productDao.deleteProduct(id);
		int p_id = id;
		productDao.deleteProduct(p_id);

		return "redirect:/admin/products";
	}

	@GetMapping("/products")
	public String getProducts(Model model) {
		List<Product> listProducts = productDao.getPagination(1, 5);
		model.addAttribute("listProducts", listProducts);
		return "index";
	}

	@GetMapping("/admin/products")
	public String getAdminProducts(Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(8);

		List<Category> categories = categoryDao.findAll();
		model.addAttribute("categories", categories);

		PagedList<Product> listProducts = PagedList.toPagedList(productDao, currentPage, pageSize);
		model.addAttribute("paginationData", listProducts.getMetaData());
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("product", new Product());
		return "adminProductList";
	}

	@PostMapping("/admin/products/edit")
	public String editProduct(@ModelAttribute Product product) {
		productDao.update(product);
		return "redirect:/admin/products";
	}

	@RequestMapping(value = "/mainview")
	public String productsPaginated(
			Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(8);

		PagedList<Product> listProducts = PagedList.toPagedList(productDao, currentPage, pageSize);
		model.addAttribute("paginationData", listProducts.getMetaData());
		model.addAttribute("listProducts", listProducts);

		return "mainView";
	}
	
	@RequestMapping("/productdetails")
	public ResponseEntity<List<Product>> productDetails(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Product> product = productDao.findById(id);
		return ResponseEntity
				.ok()
				.body(product);
	}
}
