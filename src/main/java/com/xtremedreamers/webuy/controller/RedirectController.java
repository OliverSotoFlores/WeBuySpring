package com.xtremedreamers.webuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

	@RequestMapping("/signin")
	public String getLogin() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	@RequestMapping("/cart")
	public String getCart() {
		return "cart";
	}
}
