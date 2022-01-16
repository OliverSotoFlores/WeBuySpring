package com.xtremedreamers.webuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtremedreamers.webuy.models.PromotionEvent;
import com.xtremedreamers.webuy.persistence.PromotionEventDao;



@Controller
public class PromotionEventsController {

	@Autowired
	PromotionEventDao promotionEventDao;
	
	@RequestMapping("/promotionevents")
	public String PromotionEventsList(Model model) {
		List<PromotionEvent> promotionEvents = promotionEventDao.findAll();
		model.addAttribute("promotionEvents", promotionEvents);
		return "promotions";
	}
	
}
