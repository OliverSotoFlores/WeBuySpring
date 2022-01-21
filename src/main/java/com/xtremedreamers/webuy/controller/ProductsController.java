package com.xtremedreamers.webuy.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String ProductsList() {
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
		String name = request.getParameter("p-name");
		String company = request.getParameter("p-company");
		BigDecimal price = new BigDecimal(request.getParameter("p-price"));
		String description = request.getParameter("p-description");
		String imagePath = request.getParameter("p-image");
		int categoryId = Integer.parseInt(request.getParameter("p-category"));

		Product product = new Product();
		product.setName(name);
		product.setCompany(company);
		product.setPrice(price);
		product.setDescription(description);
		product.setImagePath(imagePath);
		product.setCategoryId(categoryId);
		productDao.save(product);

		return "redirect:/adminProductList";
	}

	// Delete a Product
	@RequestMapping("/deleteProduct/{id}")
	public String DeleteProduct(@PathVariable int id) {
		// int product_id = (productDao.getLastId()) - 1;
		// productDao.deleteProduct(id);
		int p_id = id;
		productDao.deleteProduct(p_id);

		return "redirect:/adminProductList";
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
}
