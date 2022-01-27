package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.Admin;

@Component
public class AdminDao implements GenericDao<Admin, Integer>{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Admin> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Admin findAdmin(String email, String password) {
		String query = " SELECT admin_id"
							 + ",admin_name"
							 + ",email"
							 + ",admin_password "
							 + "FROM admin "
							 + "WHERE email = ? "
							 + "AND admin_password = ?";
		
		Admin result =  jdbcTemplate.queryForObject(query, new Object[] { email, password }, new AdminRowMapper());
		return result;
	}

	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> find(Admin example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Admin> getPagination(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getPagination(int pageNumber, int pageSize, String sortByName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Admin instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Admin instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Admin instance) {
		// TODO Auto-generated method stub
		
	}
}
