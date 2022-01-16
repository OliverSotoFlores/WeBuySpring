package com.xtremedreamers.webuy.models;

public class Coupon {
	private int coupon_id;
    private String coupon_name;
    private String coupon_type;
    private double coupon_discount;
    private int promotion_event_id;
    private int product_category_id;
    private String promotion_event_name;
    private String category_name;
    
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
	}
	public double getCoupon_discount() {
		return coupon_discount;
	}
	public void setCoupon_discount(double coupon_discount) {
		this.coupon_discount = coupon_discount;
	}
	public int getPromotion_event_id() {
		return promotion_event_id;
	}
	public void setPromotion_event_id(int promotion_event_id) {
		this.promotion_event_id = promotion_event_id;
	}
	public int getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(int product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getPromotion_event_name() {
		return promotion_event_name;
	}
	public void setPromotion_event_name(String promotion_event_name) {
		this.promotion_event_name = promotion_event_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	@Override
	public String toString() {
		return "Coupon [coupon_id=" + coupon_id + ", coupon_name=" + coupon_name + ", coupon_type=" + coupon_type
				+ ", coupon_discount=" + coupon_discount + ", promotion_event_id=" + promotion_event_id
				+ ", product_category_id=" + product_category_id + ", promotion_event_name=" + promotion_event_name
				+ ", category_name=" + category_name + "]";
	}
	
}
