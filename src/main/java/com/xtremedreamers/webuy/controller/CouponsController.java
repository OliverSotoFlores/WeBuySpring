package com.xtremedreamers.webuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.Coupon;
import com.xtremedreamers.webuy.models.Product;
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
}
