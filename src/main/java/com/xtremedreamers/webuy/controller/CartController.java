package com.xtremedreamers.webuy.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.Cart;
import com.xtremedreamers.webuy.models.CartDetails;
import com.xtremedreamers.webuy.models.Coupon;
import com.xtremedreamers.webuy.models.RegisteredUser;
import com.xtremedreamers.webuy.persistence.CartDao;
import com.xtremedreamers.webuy.persistence.CartDetailsDao;
import com.xtremedreamers.webuy.persistence.CouponDao;

@Controller
public class CartController {
	
	@Autowired
	CartDetailsDao dao;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	CouponDao couponDao;

	
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
	
	@PostMapping("/addtocart")
	public String addCart(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/signin";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		int q = Integer.parseInt(request.getParameter("quatity"));
		double original = Double.parseDouble(request.getParameter("original"));
		double finalP = Double.parseDouble(request.getParameter("final"));
		
		CartDetails details= new CartDetails(); 
		details.setProductId(id);
		details.setQuantity(q);
		details.setShoppingCost(BigDecimal.valueOf(original));
		details.setCostCoupon(BigDecimal.valueOf(finalP));
		details.setShoppingCartId(cart.getId());
		dao.save(details);
		int cartProducts = cartDao.getProductsCount(cart.getId());
		double cartPrice = cartDao.getProductsTotal(cart.getId());
		session.setAttribute("cartProductsQuantity", cartProducts);
		session.setAttribute("cartPrice", cartPrice);
		return "redirect:/cart";
		
	}
	
	@PostMapping("/updateCart")
	public String updateCart(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/signin";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		int q = Integer.parseInt(request.getParameter("quantity"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		CartDetails details= new CartDetails(); 
		details.setId(id);
		details.setQuantity(q);
		details.setCostCoupon(BigDecimal.valueOf(price));
		dao.update(details);
		int cartProducts = cartDao.getProductsCount(cart.getId());
		double cartPrice = cartDao.getProductsTotal(cart.getId());
		session.setAttribute("cartProductsQuantity", cartProducts);
		session.setAttribute("cartPrice", cartPrice);
		return "redirect:/cart";
		
	}
	
	@PostMapping("/deleteCart")
	public String deleteCart(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/signin";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		
		CartDetails details= new CartDetails(); 
		details.setId(id);
		dao.delete(details);
		int cartProducts = cartDao.getProductsCount(cart.getId());
		double cartPrice = cartDao.getProductsTotal(cart.getId());
		session.setAttribute("cartProductsQuantity", cartProducts);
		session.setAttribute("cartPrice", cartPrice);
		return "redirect:/cart";
		
	}
	
	@GetMapping("/purchase")
	public String purchaseProductsCart(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/signin";
		}
		double totalPrice = (double) session.getAttribute("cartPrice");
		session.setAttribute("cart", cartDao.completePurchase(cart.getId(), user, totalPrice));
		session.setAttribute("cartProductsQuantity", 0);
		session.setAttribute("cartPrice", 0);
		return "redirect:/cart";
		
	}

	@RequestMapping("/checkout")
	public String checkout(HttpServletRequest request, HttpSession session, Model model) {
		request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/signin";
		}
		
		List<CartDetails> details = dao.findByCartID(cart.getId());
		List<Coupon> coupons = couponDao.findAll2();
		System.out.println(coupons);
		model.addAttribute("details",details);
		model.addAttribute("coupons",coupons);
		
		return "checkout";
	}

	@RequestMapping("/thankyou")
	public String thankyou() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				
			}
		}, 0, 50000);

		return "thankyou";
	}
}
