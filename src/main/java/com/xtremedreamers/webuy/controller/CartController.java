package com.xtremedreamers.webuy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.RegisteredUser;

@Controller
public class CartController {
	
	@RequestMapping("/cart")
	public String getCart(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		if (user == null) {
			return "redirect:/signin";
		}
		return "cart";
	}
}
