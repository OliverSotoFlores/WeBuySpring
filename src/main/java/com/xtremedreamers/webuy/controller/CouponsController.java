package com.xtremedreamers.webuy.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xtremedreamers.webuy.models.Coupon;
import com.xtremedreamers.webuy.persistence.CategoryDao;
import com.xtremedreamers.webuy.persistence.CouponDao;
import com.xtremedreamers.webuy.persistence.PromotionEventDao;
import com.xtremedreamers.webuy.shared.PagedList;

@Controller
public class CouponsController {

	@Autowired
	CouponDao couponDao;
	
	@Autowired
	PromotionEventDao promotionDao;
	
	@Autowired
	CategoryDao categoryDao;

	@RequestMapping("/coupons")
	public String ProductsList(Model model, 
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/signinadmin";
		}
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		PagedList<Coupon> coupons = PagedList.toPagedList(couponDao, currentPage, pageSize);
		model.addAttribute("paginationData", coupons.getMetaData());
		model.addAttribute("coupons", coupons);
		model.addAttribute("promotions", promotionDao.findAll());
		model.addAttribute("categories", categoryDao.findAll());
		return "coupons";
	}

	@RequestMapping("/createCoupon")
	public ResponseEntity<String> CreateCoupons(HttpServletRequest request) {
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
		coupon.setProduct_category_id(product_category);
		coupon.setCoupon_type(coupon_type);
		coupon.setCoupon_discount(coupon_discount);
		
		try {
			couponDao.save(coupon);
			return ResponseEntity
					.ok()
					.body("OK");
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.badRequest().body("Error");
		}
	}

	@RequestMapping("/updateCoupon")
	public String UpdateCoupon(HttpServletRequest request) {
		int coupon_id = Integer.parseInt(request.getParameter("edit-element-id"));
		String coupon_name = request.getParameter("c-name");
		int promotion_event = Integer.parseInt(request.getParameter("pe-list"));
		int product_category = Integer.parseInt(request.getParameter("c-product-category"));
		String coupon_type = request.getParameter("coupon-type");
		int coupon_discount = Integer.parseInt(request.getParameter("c-discount"));
		Coupon coupon = new Coupon();
		coupon.setCoupon_id(coupon_id);
		coupon.setCoupon_name(coupon_name);
		coupon.setPromotion_event_id(promotion_event);
		coupon.setProduct_category_id(product_category);
		coupon.setCoupon_type(coupon_type);
		coupon.setCoupon_discount(coupon_discount);
		couponDao.update(coupon);
		return "redirect:/coupons";
	}

	@RequestMapping("/deleteCoupon/{coupon_id}")
	public String DeleteCoupon(@PathVariable int coupon_id) {
		int id = coupon_id;
		couponDao.deleteCoupon(id);
		return "redirect:/coupons";
	}

	@RequestMapping("/couponSearch")
	public ResponseEntity<Coupon> CouponSearch(HttpServletRequest request) {
		Coupon coupon;
		try {
			coupon = couponDao.findByName(request.getParameter("id"));
			return ResponseEntity
					.ok()
					.body(coupon);
		} catch (EmptyResultDataAccessException e) {
			coupon = null;
			return ResponseEntity.badRequest().body(coupon);
		}

	}
	
	@RequestMapping("/availablecoupons")
	public ResponseEntity<List<Coupon>> ProductSearch(HttpServletRequest request) {
		List<Coupon> coupons;
		coupons = couponDao.findCouponsByCategory(Integer.parseInt(request.getParameter("id")));
		if(coupons.size()>0) {
			return ResponseEntity
					.ok()
					.body(coupons);
		}
		else {
			return ResponseEntity.badRequest().body(coupons);
		}
	}
}
