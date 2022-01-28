package com.xtremedreamers.webuy.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
				
				int cartProducts = cartDao.getProductsCount(cart.getId());
				double cartPrice = cartDao.getProductsTotal(cart.getId());
				session.setAttribute("cartProductsQuantity", cartProducts);
				session.setAttribute("cartPrice", cartPrice);
			} catch (EmptyResultDataAccessException e) {
				cart = cartDao.createCart(user);
				session.setAttribute("cart", cart);
				session.setAttribute("cartProductsQuantity", 0);
				session.setAttribute("cartPrice", 0);
				if(cart == null) {
					session.setAttribute("error", "Error creating your cart. Please try again later.");
					return "redirect:/signin";
				}
			} catch(NullPointerException e) {
				session.setAttribute("cartProductsQuantity", 0);
				session.setAttribute("cartPrice", 0);
			}
			return "redirect:/home";
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
	
	@PostMapping("/registeruser")
	public String register(
			@ModelAttribute RegisteredUser registeredUser, 
			HttpServletRequest request, 
			HttpSession session) {
		
		request.getSession();
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		if(session.getAttribute("error") != null) {
			session.removeAttribute("error");
		}
		
		rUDao.save(registeredUser);
		
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
					return "redirect:/register";
				}
			}
			return "redirect:/home";
		}catch(EmptyResultDataAccessException e) {
			session.setAttribute("error", "Error with provided credentials. Please verify them.");
			return "redirect:/register";
		}
		
	}
}
