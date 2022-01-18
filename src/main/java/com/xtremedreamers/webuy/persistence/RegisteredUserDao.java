package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.RegisteredUser;

@Component
public class RegisteredUserDao implements GenericDao<RegisteredUser, Integer>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public RegisteredUser findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RegisteredUser findUser(String e, String p) {
		// TODO Auto-generated method stub
		String query = "select user_id,"
				+ "user_email,"
				+ "user_password,"
				+ "user_fullname,"
				+ "user_address,"
				+ "user_contact_number "
				+ "from registered_user "
				+ "WHERE user_email = ? "
				+ "AND user_password = ?";
		RegisteredUser c = jdbcTemplate.queryForObject(query, new Object[] { e, p }, new RegisteredUserRowMapper());
		return c;
	}


	@Override
	public List<RegisteredUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisteredUser> find(RegisteredUser example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisteredUser> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RegisteredUser> getPagination(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(RegisteredUser instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(RegisteredUser instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(RegisteredUser instance) {
		// TODO Auto-generated method stub
		
	}

}
