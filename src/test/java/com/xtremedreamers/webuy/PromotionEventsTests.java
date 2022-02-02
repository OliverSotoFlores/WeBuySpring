package com.xtremedreamers.webuy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xtremedreamers.webuy.controller.PromotionEventsController;
import com.xtremedreamers.webuy.models.PromotionEvent;
import com.xtremedreamers.webuy.persistence.PromotionEventDao;

@SpringBootTest
public class PromotionEventsTests {
	
	@Autowired
	private PromotionEventDao promoDao;
	
	@Autowired
	private PromotionEventsController promotions;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(promotions).isNotNull();
	}
	
	@Test
	public void findsPromotions() {
		List<PromotionEvent> promotions = promoDao.findAll();
		assertThat(promotions).isNotNull();
	}
	
	@Test
	public void createPromotion() {
		PromotionEvent promotionEvent = new PromotionEvent();
		
		promotionEvent.setPromotion_event_id(8);
		promotionEvent.setPromotion_event_name("PromotionTest");
		promotionEvent.setPromotion_event_description("Test for promotions");
		promotionEvent.setPromotion_event_start_date("2022-02-01");
		promotionEvent.setPromotion_event_end_date("2022-02-03");
		promotionEvent.setPromotion_event_status("valid");
		promotionEvent.setAdmin_id(1);
		int result = promoDao.save(promotionEvent);
		assertThat(result).isEqualTo(1);
	}
	
	@Test
	public void findPromotionByName() {
		List<PromotionEvent> promotion = promoDao.findByName("PromotionTest");
		assertThat(promotion).isNotNull();
	}
	
	@Test
	public void getLastPromotionId() {
		int result = promoDao.getLastId();
		assertThat(result).isNotNull();
	}
	
}