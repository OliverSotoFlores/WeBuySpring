package com.xtremedreamers.webuy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xtremedreamers.webuy.models.Coupon;
import com.xtremedreamers.webuy.models.Product;

@Component
public class CouponDao implements GenericDao<Coupon, Integer> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Coupon> findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select c.coupon_id,"
				+ "c.coupon_name,"
				+ "c.coupon_type,"
				+ "c.coupon_discount,"
				+ "c.promotion_event_id,"
				+ "c.product_category_id,"
				+ "p.promotion_event_name,"
				+ "pc.category_name "
				+ "from coupon c "
				+ "inner join promotion_event p on c.promotion_event_id = p.promotion_event_id "
				+ "inner join product_category pc on c.product_category_id = pc.product_category_id "
				+ "WHERE c.coupon_id = ?";
		Coupon c = jdbcTemplate.queryForObject(query, new Object[] { id }, new CouponRowMapper());
		return (List<Coupon>) c;
	}

	public Coupon findByName(String name) {
		// TODO Auto-generated method stub
		String query = "select c.coupon_id,"
				+ "c.coupon_name,"
				+ "c.coupon_type,"
				+ "c.coupon_discount,"
				+ "c.promotion_event_id,"
				+ "c.product_category_id,"
				+ "p.promotion_event_name,"
				+ "pc.category_name "
				+ "from coupon c "
				+ "inner join promotion_event p on c.promotion_event_id = p.promotion_event_id "
				+ "inner join product_category pc on c.product_category_id = pc.product_category_id "
				+ "WHERE c.coupon_name = ?";
		Coupon c = jdbcTemplate.queryForObject(query, new Object[] { name }, new CouponRowMapper());
		return c;
	}
	
	public List<Coupon> findCouponsByCategory(int id){
		String query = "select c.coupon_id,"
				+ "c.coupon_name,"
				+ "c.coupon_type,"
				+ "c.coupon_discount,"
				+ "c.promotion_event_id,"
				+ "c.product_category_id,"
				+ "p.promotion_event_name,"
				+ "pc.category_name "
				+ "from coupon c "
				+ "inner join promotion_event p on c.promotion_event_id = p.promotion_event_id "
				+ "inner join product_category pc on c.product_category_id = pc.product_category_id "
				+ "WHERE c.product_category_id = "+ id + " "
				+ "AND c.coupon_type = 'open'";
		return jdbcTemplate.query(query, new CouponRowMapper());
	}

	@Override
	public List<Coupon> findAll() {
		// TODO Auto-generated method stub
		String sql = "select c.coupon_id,"
				+ "c.coupon_name,"
				+ "c.coupon_type,"
				+ "c.coupon_discount,"
				+ "c.promotion_event_id,"
				+ "c.product_category_id,"
				+ "p.promotion_event_name,"
				+ "pc.category_name "
				+ "from coupon c "
				+ "inner join promotion_event p on c.promotion_event_id = p.promotion_event_id "
				+ "inner join product_category pc on c.product_category_id = pc.product_category_id";
		return jdbcTemplate.query(sql, new CouponRowMapper());
	}

	@Override
	public List<Coupon> find(Coupon example) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<Coupon> find(String queryName, String[] paramNames, Object[] bindValues) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Coupon instance) {
		return jdbcTemplate.update(
				"insert into coupon (coupon_id, coupon_name, coupon_type, coupon_discount, promotion_event_id, product_category_id )"
						+ "values (?,?,?,?,?,?)",
				new Object[] { instance.getCoupon_id(), instance.getCoupon_name(),
						instance.getCoupon_type(), instance.getCoupon_discount(),
						instance.getPromotion_event_id(), instance.getProduct_category_id() });
	}

	@Override
	public void update(Coupon instance) {
		jdbcTemplate.update("update coupon set " +
				" coupon_name = '" + instance.getCoupon_name() +
				"', coupon_type = '" + instance.getCoupon_type() +
				"' , coupon_discount = " + instance.getCoupon_discount() +
				", promotion_event_id = " + instance.getPromotion_event_id() +
				", product_category_id = " + instance.getProduct_category_id() +
				" where coupon_id = " + instance.getCoupon_id());

	}

	@Override
	public void delete(Coupon instance) {
		// TODO Auto-generated method stub

	}

	public void deleteCoupon(int id) {
		jdbcTemplate.update("delete from coupon where coupon_id = " + id);
	}

	public int getLastId() {
		int result = jdbcTemplate.queryForObject("select max(coupon_id)+1 from coupon", Integer.class);
		return result;
	}

	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from coupon", Integer.class);
	}

	@Override
	public List<Coupon> getPagination(int pageNumber, int pageSize) {
		int skip = (pageNumber - 1) * pageSize;
		String sql = "select c.coupon_id,"
				+ "c.coupon_name,"
				+ "c.coupon_type,"
				+ "c.coupon_discount,"
				+ "c.promotion_event_id,"
				+ "c.product_category_id,"
				+ "p.promotion_event_name,"
				+ "pc.category_name "
				+ "from coupon c "
				+ "inner join promotion_event p on c.promotion_event_id = p.promotion_event_id "
				+ "inner join product_category pc on c.product_category_id = pc.product_category_id "
				+ "limit " + skip + ", " + pageSize;
		return jdbcTemplate.query(sql, new CouponRowMapper());
	}

}
