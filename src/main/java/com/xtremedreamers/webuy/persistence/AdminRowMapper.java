package com.xtremedreamers.webuy.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xtremedreamers.webuy.models.Admin;

public class AdminRowMapper implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		admin.setAdmin_id(rs.getInt(1));
		admin.setAdmin_name(rs.getString(2));
		admin.setEmail(rs.getString(3));
		admin.setAdmin_password(rs.getString(4));
		
		return admin;
	}

}
