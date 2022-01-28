package com.xtremedreamers.webuy.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xtremedreamers.webuy.models.CartDetails;

public class CartDetailsRowMapper implements RowMapper<CartDetails> {

	@Override
	public CartDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartDetails cartDetails = new CartDetails();
		cartDetails.setId(rs.getInt(1));
		cartDetails.setQuantity(rs.getInt(2));
		cartDetails.setShoppingCost(rs.getBigDecimal(3));
		cartDetails.setCostCoupon(rs.getBigDecimal(4));
		cartDetails.setProductId(rs.getInt(5));
		cartDetails.setProductName(rs.getString(6));
		cartDetails.setProductImage(rs.getString(7));
		cartDetails.setShoppingCartId(rs.getInt(8));
		return cartDetails;
	}

}
