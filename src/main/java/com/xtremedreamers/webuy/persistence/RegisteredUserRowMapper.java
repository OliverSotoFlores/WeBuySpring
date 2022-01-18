package com.xtremedreamers.webuy.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xtremedreamers.webuy.models.RegisteredUser;

public class RegisteredUserRowMapper implements RowMapper<RegisteredUser>{

	@Override
	public RegisteredUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		RegisteredUser user = new RegisteredUser();
		user.setId(rs.getInt(1));
		user.setEmail(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setFullname(rs.getString(4));
		user.setAddress(rs.getString(5));
		user.setNumber(rs.getString(6));
		return user;
	}

}
