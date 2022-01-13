package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductsController {
	
	
	@RequestMapping("/")
	public String ProductsList() {
		
		return "ProductsView.jsp";
	}
}
