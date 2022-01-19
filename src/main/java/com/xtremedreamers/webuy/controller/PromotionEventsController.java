package com.xtremedreamers.webuy.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xtremedreamers.webuy.models.PromotionEvent;
import com.xtremedreamers.webuy.persistence.PromotionEventDao;
import com.xtremedreamers.webuy.shared.PagedList;



@Controller
public class PromotionEventsController {

	@Autowired
	PromotionEventDao promotionEventDao;
	
	@RequestMapping("/promotionevents")
	public String PromotionEventsList(Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		PagedList<PromotionEvent> promotionEvents = PagedList.toPagedList(promotionEventDao, currentPage, pageSize);
		model.addAttribute("paginationData", promotionEvents.getMetaData());
		model.addAttribute("promotionEvents", promotionEvents);
		System.out.println(promotionEvents);
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
	public String SearchPromotion(HttpServletRequest request, Model model) {
		
		List<PromotionEvent> promotionEvents = promotionEventDao.findByName(request.getParameter("search-bar"));
		PagedList<PromotionEvent> promotionEventsSearch = PagedList.toPagedList(promotionEvents);
		model.addAttribute("paginationData", promotionEventsSearch.getMetaData());
		model.addAttribute("promotionEvents", promotionEvents);
		System.out.println(promotionEvents);
		return "promotions";
		
		
		/*PromotionEvent promotionEvent;
		try {
			promotionEvent = promotionEventDao.findByName(request.getParameter("id"));
			System.out.println(promotionEvent);
			return ResponseEntity
						.ok()
						.body(promotionEvent);
		}catch(EmptyResultDataAccessException e) {
			promotionEvent = null;
			return ResponseEntity.badRequest().body(promotionEvent);
		}*/
	}
	
}
