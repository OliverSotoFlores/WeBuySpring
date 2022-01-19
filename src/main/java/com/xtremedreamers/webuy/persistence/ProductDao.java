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
		product.setPrice(rs.getBigDecimal(4));
		product.setDescription(rs.getString(5));
		product.setImagePath(rs.getString(6));
		product.setCategoryId(rs.getInt(7));
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
	public Integer save(Product instance) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO product (product_name, product_company, product_price, product_description, product_image_path, product_category_id)"
				+ "VALUES (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { instance.getName(), instance.getCompany(), instance.getPrice(),
				instance.getDescription(), instance.getImagePath(), instance.getCategoryId() });
	}

	@Override
	public void update(Product instance) {
		// TODO Auto-generated method stub
		String sql = "UPDATE product SET product_name=?, product_company=?, product_price=?, product_description=?, product_image_path=?, product_category_id=?"
				+ " WHERE product_id = ?";
		int rows = jdbcTemplate.update(sql,
				new Object[] { instance.getName(), instance.getCompany(), instance.getPrice(),
						instance.getDescription(), instance.getImagePath(), instance.getCategoryId(),
						instance.getId() });
	}

	@Override
	public void delete(Product instance) {
		// TODO Auto-generated method stub
		
	}

	public void deleteProduct(int p_id) {
		jdbcTemplate.update("delete from product where id = " + p_id);
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
	public int count() {
		String sql = "SELECT count(*) from product";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public List<Product> getPagination(int pageNumber, int pageSize) {
		int skip = (pageNumber - 1) * pageSize;

		String sql = "SELECT product_id, product_name, "
				+ "product_company, product_price, product_description, "
				+ "product_image_path, product_category_id FROM product LIMIT " + skip + ", " + pageSize;

		List<Product> results = jdbcTemplate.query(sql, rowMapper);
		return results;
	}

}
