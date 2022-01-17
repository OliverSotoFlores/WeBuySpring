package com.xtremedreamers.webuy.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.Coupon;
import com.xtremedreamers.webuy.persistence.CouponDao;

@Controller
public class CouponsController {

	@Autowired
	CouponDao couponDao;
	
	@RequestMapping("/coupons")
	public String ProductsList(Model model) {
		List<Coupon> coupons = couponDao.findAll();
		model.addAttribute("coupons", coupons);
		return "coupons";
	}
	
	@RequestMapping("/createCoupon")
	public String CreateCoupons(HttpServletRequest request) {
		int coupon_id = couponDao.getLastId();
		String coupon_name = request.getParameter("c-name");
		int promotion_event = Integer.parseInt(request.getParameter("pe-list"));
		int product_category = Integer.parseInt(request.getParameter("c-product-category"));
		String coupon_type = request.getParameter("coupon-type");
		int coupon_discount = Integer.parseInt(request.getParameter("c-discount"));
		Coupon coupon = new Coupon();
		coupon.setCoupon_id(coupon_id);
		coupon.setCoupon_name(coupon_name);
		coupon.setPromotion_event_id(promotion_event);
		coupon.setPromotion_event_name("XD");
		coupon.setProduct_category_id(product_category);
		coupon.setPromotion_event_name("Equis de");
		coupon.setCoupon_type(coupon_type);
		coupon.setCoupon_discount(coupon_discount);
		int response = couponDao.save(coupon);
		if (response>0) {
			System.err.println("Se insertó de manera correcta");
		}else {
			System.out.println("Hubo un error al insertar");
		}
		return "redirect:/coupons";
	}
		
	@RequestMapping("/coupons/search")
	public String ProductSearch(Model model) {
		Coupon coupon = couponDao.findById(5);
		model.addAttribute("coupon", coupon);
		return "coupons";
	}
}
