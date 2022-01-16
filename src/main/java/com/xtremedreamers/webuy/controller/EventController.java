package com.xtremedreamers.webuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.xtremedreamers.webuy.models.Event;
import com.xtremedreamers.webuy.persistence.EventDao;

public class EventController {
	@Autowired
	EventDao eventDao;
	
	@RequestMapping("/")
	public String EventList() {
		List<Event> events = eventDao.findAll();
		System.out.println(events);
		return "mainView";
	}
}
