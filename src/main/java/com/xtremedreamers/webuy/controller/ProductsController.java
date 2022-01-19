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

	// Create a new Product
/*
    Check BigDecimal Parse
*/
@RequestMapping("/createProduct")
	public String CreateProduct(HttpServletRequest request) 
	{
		int id = productDao.getLastId();
		String name = request.getParameter("p-name");
		String company = request.getParameter("p-company");
		//BigDecimal price = 
		String description = request.getParameter("p-description");
		String imagePath = request.getParameter("p-image");
		int categoryId = request.getParameter("p-category");

		Product product = new Product();
			product.setProduct_id(id);
			product.setProduct_name(p-name);
			product.setProduct_company(p-company);
			product.setProduct_price(p-price);
			product.setProduct_description(p-description);
			product.setProduct_imagePath(p-image);
			product.setProduct_categoryId(p-category);

			return "redirect:/adminProductList";
	}

	// Delete a Product
	@RequestMapping("/deleteProduct")
	public String DeleteProduct() 
	{
		int product_id = (productDao.getLastId()) - 1;
		productDao.deleteProduct(id);

		return "redirect:/adminProductList";
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
		int pageSize = size.orElse(8);

		PagedList<Product> listProductsMejorado = PagedList.toPagedList(productDao, currentPage, pageSize);
		model.addAttribute("paginationData", listProductsMejorado.getMetaData());
		model.addAttribute("listProducts", listProductsMejorado);

		return "mainView";
	}
}
