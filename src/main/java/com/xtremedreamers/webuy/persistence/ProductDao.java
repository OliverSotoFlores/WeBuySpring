package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.Product;

@Component
public class ProductDao implements GenericDao<Product, Integer> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Product> rowMapper = (rs, rowNum) -> {
		Product product = new Product();
		product.setId(rs.getInt(1));
		product.setName(rs.getString(2));
		product.setCompany(rs.getString(3));
		return product;
		
	};
	
	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		String sql = "SELECT product_id, product_name, "
				+ "product_company, product_price, product_description, "
				+ "product_image_path, product_category_id FROM product";
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Product> find(Product example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> find(String queryName, String[] paramNames, Object[] bindValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Product instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Product instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product instance) {
		// TODO Auto-generated method stub
		
	}

}
