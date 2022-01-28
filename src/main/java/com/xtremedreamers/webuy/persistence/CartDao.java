package com.xtremedreamers.webuy.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.Cart;
import com.xtremedreamers.webuy.models.RegisteredUser;

@Component
public class CartDao implements GenericDao<Cart, Integer>{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Cart> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Cart findByUser(RegisteredUser user) {
		String query = "Select shopping_cart_id, "
				+ "creation_date, "
				+ "cart_status, "
				+ "user_id "
				+ "FROM shopping_cart "
				+ "WHERE cart_status = 'in_session' "
				+ "AND user_id = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { user.getId() }, new CartRowMapper());
	}
	
	public Cart createCart(RegisteredUser user) {
		Cart cart = new Cart();
		LocalDate today = LocalDate.now();
		cart.setId(getLastId());
		cart.setDate(today.toString());
		cart.setStatus("in_session");
		cart.setUserId(user.getId());
		String sql = "INSERT INTO shopping_cart VALUES (?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, 
				new Object[] {cart.getId(),
							cart.getDate(),
							cart.getStatus(),
							cart.getUserId()});
		if(result>0)
			return cart;
		return null;
	}
	
	public Cart completePurchase(int cartId, RegisteredUser user) {
		String updateQuery = "UPDATE shopping_cart SET cart_status = 'complete' WHERE shopping_cart_id = ?";
		int updateResult = jdbcTemplate.update(updateQuery, 
				new Object[] {cartId});
		if(updateResult>0) {
			Cart cart = new Cart();
			LocalDate today = LocalDate.now();
			cart.setId(getLastId());
			cart.setDate(today.toString());
			cart.setStatus("in_session");
			cart.setUserId(user.getId());
			String sql = "INSERT INTO shopping_cart VALUES (?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, 
					new Object[] {cart.getId(),
								cart.getDate(),
								cart.getStatus(),
								cart.getUserId()});
			if(result>0)
				return cart;
			return null;
		}else {
			return null;
		}
		
	}
	
	public int getLastId() {
		return jdbcTemplate.queryForObject("select max(shopping_cart_id)+1 from shopping_cart", Integer.class);
	}

	@Override
	public List<Cart> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> find(Cart example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getProductsCount(int cartId) {
		return jdbcTemplate.queryForObject(
				"select sum(quantity) from shopping_product_details where shopping_cart_id = ?",
				new Object[] {cartId}, 
				Integer.class);
	}
	
	public double getProductsTotal(int cartId) {
		return jdbcTemplate.queryForObject(
				"select sum(cost_after_applying_coupon) from shopping_product_details where shopping_cart_id = ?",
				new Object[] {cartId}, 
				Double.class);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cart> getPagination(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Cart instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cart instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Cart instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cart> getPagination(int pageNumber, int pageSize, String sortByName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
