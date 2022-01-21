package com.xtremedreamers.webuy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.RegisteredUser;
import com.xtremedreamers.webuy.models.ShoppingProductDetails;
import com.xtremedreamers.webuy.persistence.ShoppingProductDetailsDao;

@Controller
public class CartController {
	
	@Autowired
	ShoppingProductDetailsDao shoppingPDDao;
	
	@RequestMapping("/cart")
	public String getCart(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		if (user == null) {
			return "redirect:/signin";
		}
		
		List<ShoppingProductDetails> shoppingCartDetails = shoppingPDDao.findAll();
		
		System.out.println(shoppingCartDetails);
		
		return "cart";
	}
}
