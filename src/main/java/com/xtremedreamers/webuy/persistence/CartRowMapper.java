package com.xtremedreamers.webuy.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xtremedreamers.webuy.models.Cart;

public class CartRowMapper implements RowMapper<Cart>{

	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cart cart = new Cart();
		cart.setId(rs.getInt(1));
		cart.setDate(rs.getString(2));
		cart.setStatus(rs.getString(3));
		cart.setUserId(rs.getInt(4));
		return cart;
	}

}
