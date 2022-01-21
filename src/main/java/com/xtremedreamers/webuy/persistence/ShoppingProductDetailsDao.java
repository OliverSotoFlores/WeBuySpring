package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.ShoppingProductDetails;

@Component
public class ShoppingProductDetailsDao implements GenericDao<ShoppingProductDetails, Integer> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<ShoppingProductDetails> rowMapper = (rs, rowNum) -> {
		ShoppingProductDetails details = new ShoppingProductDetails();
		details.setId(rs.getInt(1));
		details.setQuantity(rs.getInt(2));
		details.setShoppingCost(rs.getBigDecimal(3));
		details.setCostAfterCoupon(rs.getBigDecimal(4));
		details.setProductId(rs.getInt(5));
		details.setShoppingCartId(rs.getInt(6));
		
		return details;
	};
	
	@Override
	public List<ShoppingProductDetails> findById(Integer id) {
		String sql = "SELECT spd.shopping_product_details_id, spd.quantity, "
				+ "spd.shopping_cost, spd.cost_after_applying_coupon, "
				+ "p.product_id, p.product_name, "
				+ "sp.shopping_cart_id, sp.cart_status "
				+ "FROM shopping_product_details spd "
				+ "INNER JOIN product p ON spd.product_id = p.product_id "
				+ "INNER JOIN shopping_cart sp ON spd.shopping_cart_id = sp.shopping_cart_id "
				+ "WHERE spd.shopping_cart_id = " + id;
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<ShoppingProductDetails> findAll() {
		String sql = "SELECT spd.shopping_product_details_id, spd.quantity, "
				+ "spd.shopping_cost, spd.cost_after_applying_coupon, "
				+ "p.product_id, p.product_name, "
				+ "sp.shopping_cart_id, sp.cart_status "
				+ "FROM shopping_product_details spd "
				+ "INNER JOIN product p ON spd.product_id = p.product_id "
				+ "INNER JOIN shopping_cart sp ON spd.shopping_cart_id = sp.shopping_cart_id";
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<ShoppingProductDetails> find(ShoppingProductDetails example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShoppingProductDetails> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ShoppingProductDetails> getPagination(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(ShoppingProductDetails instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ShoppingProductDetails instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShoppingProductDetails instance) {
		// TODO Auto-generated method stub
		
	}

}
