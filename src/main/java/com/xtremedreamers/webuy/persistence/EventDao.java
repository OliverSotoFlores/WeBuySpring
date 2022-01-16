package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.xtremedreamers.webuy.models.Event;

public class EventDao implements GenericDao<Event, Integer> {

	
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Event> rowMapper = (rs, rowNum) -> {
		Event event = new Event();
		event.setId(rs.getInt(1));
		event.setName(rs.getString(2));
		event.setDescription(rs.getString(3));
		event.setStart_date(rs.getDate(4));
		event.setEnd_date(rs.getDate(5));
		event.setStatus(rs.getString(6));
		return event;
	};
	@Override
	public Event findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findAll() {
		String sql = "SELECT promotion_event_id, promotion_event_name, promotion_event_description,"
				+ "promotion_event_start_date, promotion_event_end_date, promotion_event_status,"
				+ "FROM promotion_event";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Event> find(Event example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Event instance) {
		String sql = "INSERT INTO promotion_event (promotion_event_id, promotion_event_name, promotion_event_description, "
				+ "promotion_event_start_date, promotion_event_end_date, promotion_event_status) VALUES(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql);
	}

	@Override
	public void update(Event instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Event instance) {
		// TODO Auto-generated method stub
		
	}

	}