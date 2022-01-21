package com.xtremedreamers.webuy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.Cart;
import com.xtremedreamers.webuy.models.CartDetails;
import com.xtremedreamers.webuy.persistence.CartDetailsDao;

@Controller
public class CartController {
	
	@Autowired
	CartDetailsDao dao;
	
	@RequestMapping("/cart")
	public String getCart(HttpServletRequest request, HttpSession session, Model model) {
		request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/signin";
		}
		List<CartDetails> details = dao.findByCartID(cart.getId());
		model.addAttribute("details",details);
		return "cart";
	}
}
