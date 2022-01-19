package com.xtremedreamers.webuy.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xtremedreamers.webuy.models.PromotionEvent;

public class PromotionRowMapper implements RowMapper<PromotionEvent> {

	@Override
	public PromotionEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		PromotionEvent promotionEvent = new PromotionEvent();
		promotionEvent.setPromotion_event_id(rs.getInt(1));
		promotionEvent.setPromotion_event_name(rs.getString(2));
		promotionEvent.setPromotion_event_description(rs.getString(3));
		promotionEvent.setPromotion_event_start_date(rs.getString(4));
		promotionEvent.setPromotion_event_end_date(rs.getString(5));
		promotionEvent.setPromotion_event_status(rs.getString(6));
		promotionEvent.setAdmin_id(rs.getInt(7));
		
		return promotionEvent;
	}

}
