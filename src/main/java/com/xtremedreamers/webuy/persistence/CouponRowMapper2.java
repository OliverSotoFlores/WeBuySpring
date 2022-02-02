package com.xtremedreamers.webuy.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.xtremedreamers.webuy.models.Coupon;

public class CouponRowMapper2 implements RowMapper<Coupon>{

	@Override
	public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
		Coupon coupon = new Coupon();
		coupon.setCoupon_id(rs.getInt(1));
		coupon.setCoupon_name(rs.getString(2));
		coupon.setCoupon_type(rs.getString(3));
		coupon.setCoupon_discount(rs.getDouble(4));
		coupon.setPromotion_event_id(rs.getInt(5));
		coupon.setProduct_category_id(rs.getInt(6));
		return coupon;
	}

}
