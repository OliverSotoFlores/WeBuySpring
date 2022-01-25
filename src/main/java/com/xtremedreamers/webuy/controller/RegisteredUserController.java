package com.xtremedreamers.webuy.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.xtremedreamers.webuy.models.Cart;
import com.xtremedreamers.webuy.models.RegisteredUser;
import com.xtremedreamers.webuy.persistence.CartDao;
import com.xtremedreamers.webuy.persistence.RegisteredUserDao;

@Controller
public class RegisteredUserController {

	@Autowired
	RegisteredUserDao rUDao;
	
	@Autowired
	CartDao cartDao;
	
	@PostMapping("/index")
	public String login(HttpServletRequest request, HttpSession session) {
		request.getSession();
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		if(session.getAttribute("error") != null) {
			session.removeAttribute("error");
		}
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		try {
			RegisteredUser user = rUDao.findUser(email, pass);
			session.setAttribute("user", user);
			Cart cart;
			try {
				cart = cartDao.findByUser(user);
				session.setAttribute("cart", cart);
			} catch (EmptyResultDataAccessException e) {
				cart = cartDao.createCart(user);
				if(cart == null) {
					session.setAttribute("error", "Error creating your cart. Please try again later.");
					return "redirect:/signin";
				}
			}
			return "index";
		}catch(EmptyResultDataAccessException e) {
			session.setAttribute("error", "Error with provided credentials. Please verify them.");
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/logout")
	public String logout( HttpSession session) {
		
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		if(session.getAttribute("cart") != null) {
			session.removeAttribute("cart");
		}
		return "redirect:/signin";
	}
}
