package com.xtremedreamers.webuy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.xtremedreamers.webuy.models.Admin;
import com.xtremedreamers.webuy.persistence.AdminDao;

@Controller
public class AdminController {

	@Autowired
	AdminDao aDao;
	
	@PostMapping("/adminIndex")
	public String login(HttpServletRequest request, HttpSession session) {
		request.getSession();
		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
		}
		if (session.getAttribute("error") != null) {
			session.removeAttribute("error");
		}
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		try {
			Admin admin = aDao.findAdmin(email, pass);
			session.setAttribute("admin", admin);
			return "redirect:/admin/products";
		} catch (EmptyResultDataAccessException e) {
			session.setAttribute("error", "Error with provided credentials. Please verify them.");
			return "redirect:/signinadmin";
		}
	} 
	
	@GetMapping("/adminlogout")
	public String logout( HttpSession session) {
		
		if(session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
		}
		return "redirect:/signinadmin";
	}
}
