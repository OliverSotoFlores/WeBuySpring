package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.CartDetails;

@Component
public class CartDetailsDao implements GenericDao<CartDetails, Integer>{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CartDetails> findById(Integer id) {
		return null;
	}
	
	public List<CartDetails> findByCartID(int id) {
		String sql = "SELECT shopping_product_details_id,"
				+ "d.quantity,"
				+ "d.shopping_cost,"
				+ "d.cost_after_applying_coupon,"
				+ "d.product_id,"
				+ "p.product_name,"
				+ "d.shopping_cart_id "
				+ "from shopping_product_details d "
				+ "INNER JOIN product p on p.product_id = d.product_id "
				+ "WHERE d.shopping_cart_id = ?";
		return jdbcTemplate.query(sql, new Object[] { id }, new CartDetailsRowMapper());
	}

	@Override
	public List<CartDetails> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartDetails> find(CartDetails example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartDetails> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartDetails> getPagination(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(CartDetails instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CartDetails instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CartDetails instance) {
		// TODO Auto-generated method stub
		
	}
	
}
