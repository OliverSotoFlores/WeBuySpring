package com.xtremedreamers.webuy.persistence;

import java.util.List;

import com.xtremedreamers.webuy.models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryDao implements GenericDao<Category, Integer> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Category> rowMapper = (rs, rowNum) -> {
        Category category = new Category();
        category.setId(rs.getInt(1));
        category.setName(rs.getString(2));
        category.setDescription(rs.getString(3));
        return category;
    };

    @Override
    public List<Category> findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT product_category_id, category_name, category_description from product_category";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Category> find(Category example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Category> find(String queryName, String[] paramNames, Object[] bindValues) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Category> getPagination(int pageNumber, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer save(Category instance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Category instance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Category instance) {
        // TODO Auto-generated method stub

    }

}
