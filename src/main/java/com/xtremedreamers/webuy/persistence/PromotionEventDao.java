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

	@Override
	public PromotionEvent findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "SELECT promotion_event_id, "
				+ "promotion_event_name, "
				+ "promotion_event_description, "
				+ "promotion_event_start_date, "
				+ "promotion_event_end_date, "
				+ "promotion_event_status, "
				+ "admin_id "
				+ "FROM promotion_event "
				+ "WHERE promotion_event_id = ?";

		PromotionEvent promo = jdbcTemplate.queryForObject(query, new Object[] { id }, new PromotionRowMapper());

		return promo;
	}

	public PromotionEvent findByName(String name) {

		String query = "SELECT promotion_event_id, "
				+ "promotion_event_name, "
				+ "promotion_event_description, "
				+ "promotion_event_start_date, "
				+ "promotion_event_end_date, "
				+ "promotion_event_status, "
				+ "admin_id "
				+ "FROM promotion_event "
				+ "WHERE promotion_event_name LIKE '%?%'";

		PromotionEvent promo = jdbcTemplate.queryForObject(query, new Object[] { name }, new PromotionRowMapper());

		return promo;
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

		return jdbcTemplate.query(sql, new PromotionRowMapper());
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

		return jdbcTemplate.update(
				"INSERT INTO promotion_event (promotion_event_id, promotion_event_name, promotion_event_description, promotion_event_start_date, promotion_event_end_date, promotion_event_status, admin_id)"
						+ "values(?,?,?,?,?,?,?)",
				new Object[] { instance.getPromotion_event_id(), instance.getPromotion_event_name(),
						instance.getPromotion_event_description(), instance.getPromotion_event_start_date(),
						instance.getPromotion_event_end_date(), instance.getPromotion_event_status(),
						instance.getAdmin_id() });
	}

	@Override
	public void update(PromotionEvent instance) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("UPDATE promotion_event SET " +
				" promotion_event_name = '" + instance.getPromotion_event_name() +
				"' , promotion_event_description = '" + instance.getPromotion_event_description() +
				"' , promotion_event_start_date = '" + instance.getPromotion_event_start_date() +
				"' , promotion_event_end_date = '" + instance.getPromotion_event_end_date() +
				"' , promotion_event_status = '" + instance.getPromotion_event_status() +
				"' WHERE promotion_event_id = " + instance.getPromotion_event_id());
	}

	public void deletePromotionEvent(int promotion_event_id) {
		jdbcTemplate.update("DELETE FROM promotion_event WHERE promotion_event_id = " + promotion_event_id);
	}

	@Override
	public void delete(PromotionEvent instance) {
		// TODO Auto-generated method stub

	}

	public int getLastId() {
		int result = jdbcTemplate.queryForObject("SELECT max(promotion_event_id)+1 FROM promotion_event",
				Integer.class);
		return result;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PromotionEvent> getPagination(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
