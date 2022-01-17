package com.xtremedreamers.webuy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/createPromotionEvent")
	public String CreatePromotionEvent(HttpServletRequest request) {
		
		int promotion_event_id = promotionEventDao.getLastId();
		String promotion_event_name = request.getParameter("promotion-event-name");
		String promotion_event_description = request.getParameter("promotion-event-description");
		String promotion_event_start_date = request.getParameter("promotion-event-start-date");
		String promotion_event_end_date = request.getParameter("promotion-event-end-date");
		String promotion_event_status = request.getParameter("promotion-event-status");
		int admin_id = 1;
		
		PromotionEvent promotionEvent = new PromotionEvent();
		
		promotionEvent.setPromotion_event_id(promotion_event_id);
		promotionEvent.setPromotion_event_name(promotion_event_name);
		promotionEvent.setPromotion_event_description(promotion_event_description);
		promotionEvent.setPromotion_event_start_date(promotion_event_start_date);
		promotionEvent.setPromotion_event_end_date(promotion_event_end_date);
		promotionEvent.setPromotion_event_status(promotion_event_status);
		promotionEvent.setAdmin_id(admin_id);
		
		promotionEventDao.save(promotionEvent);
		
		return "redirect:/promotionevents";
	}
	
	@RequestMapping("/updatePromotionEvent")
	public String UpdatePromotionEvent(HttpServletRequest request) {
		
		int promotion_event_id = Integer.parseInt(request.getParameter("promotional-event-id"));
		String promotion_event_name = request.getParameter("promotion-event-name");
		String promotion_event_description = request.getParameter("promotion-event-description");
		String promotion_event_start_date = request.getParameter("promotion-event-start-date");
		String promotion_event_end_date = request.getParameter("promotion-event-end-date");
		String promotion_event_status = request.getParameter("promotion-event-status");
		int admin_id = Integer.parseInt(request.getParameter("admin-id"));
		
		PromotionEvent promotionEvent = new PromotionEvent();
		
		promotionEvent.setPromotion_event_id(promotion_event_id);
		promotionEvent.setPromotion_event_name(promotion_event_name);
		promotionEvent.setPromotion_event_description(promotion_event_description);
		promotionEvent.setPromotion_event_start_date(promotion_event_start_date);
		promotionEvent.setPromotion_event_end_date(promotion_event_end_date);
		promotionEvent.setPromotion_event_status(promotion_event_status);
		promotionEvent.setAdmin_id(admin_id);
		
		promotionEventDao.update(promotionEvent);
		
		return "redirect:/promotionevents";
	}
	
	@RequestMapping("/deletePromotionEvent/{promotion_id}")
	public String DeletePromotionEvent(@PathVariable int promotion_id) {
		
		int promotion_event_id = promotion_id;
		
		promotionEventDao.deletePromotionEvent(promotion_event_id);
		
		return "redirect:/promotionevents";
	}
	
	@RequestMapping("/searchPromotionEvent")
	public ResponseEntity<PromotionEvent> SearchPromotion(HttpServletRequest request) {
		PromotionEvent promotionEvent;
		try {
			promotionEvent = promotionEventDao.findByName(request.getParameter("id"));
			System.out.println(promotionEvent);
			return ResponseEntity
						.ok()
						.body(promotionEvent);
		}catch(EmptyResultDataAccessException e) {
			promotionEvent = null;
			return ResponseEntity.badRequest().body(promotionEvent);
		}
	}
	
}