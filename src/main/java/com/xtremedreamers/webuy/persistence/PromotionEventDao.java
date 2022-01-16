package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.PromotionEvent;

@Component
public class PromotionEventDao implements GenericDao<PromotionEvent, Integer> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<PromotionEvent> rowMapper = (rs, rowNum) -> {
		PromotionEvent promotionEvent = new PromotionEvent();
		promotionEvent.setPromotion_event_id(rs.getInt(1));
		promotionEvent.setPromotion_event_name(rs.getString(2));
		promotionEvent.setPromotion_event_description(rs.getString(3));
		promotionEvent.setPromotion_event_start_date(rs.getString(4));
		promotionEvent.setPromotion_event_end_date(rs.getString(5));
		promotionEvent.setPromotion_event_status(rs.getString(6));
		promotionEvent.setAdmin_id(rs.getInt(7));
		
		return promotionEvent;
	};
	
	@Override
	public PromotionEvent findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionEvent> findAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT promotion_event_id, "
						+ "promotion_event_name, "
						+ "promotion_event_description, "
						+ "promotion_event_start_date, "
						+ "promotion_event_end_date, "
						+ "promotion_event_status, "
						+ "admin_id "
						+ "FROM promotion_event";
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<PromotionEvent> find(PromotionEvent example) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<PromotionEvent> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(PromotionEvent instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PromotionEvent instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PromotionEvent instance) {
		// TODO Auto-generated method stub
		
	}

	
	
}
