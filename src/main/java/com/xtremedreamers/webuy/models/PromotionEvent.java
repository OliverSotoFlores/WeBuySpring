package com.xtremedreamers.webuy.models;

public class PromotionEvent {
	private int promotion_event_id;
	private String promotion_event_name;
	private String promotion_event_description;
	private String promotion_event_start_date;
	private String promotion_event_end_date;
	private String promotion_event_status;
	private int admin_id;
	
	public int getPromotion_event_id() {
		return promotion_event_id;
	}
	public void setPromotion_event_id(int promotion_event_id) {
		this.promotion_event_id = promotion_event_id;
	}
	public String getPromotion_event_name() {
		return promotion_event_name;
	}
	public void setPromotion_event_name(String promotion_event_name) {
		this.promotion_event_name = promotion_event_name;
	}
	public String getPromotion_event_description() {
		return promotion_event_description;
	}
	public void setPromotion_event_description(String promotion_event_description) {
		this.promotion_event_description = promotion_event_description;
	}
	public String getPromotion_event_start_date() {
		return promotion_event_start_date;
	}
	public void setPromotion_event_start_date(String promotion_event_start_date) {
		this.promotion_event_start_date = promotion_event_start_date;
	}
	public String getPromotion_event_end_date() {
		return promotion_event_end_date;
	}
	public void setPromotion_event_end_date(String promotion_event_end_date) {
		this.promotion_event_end_date = promotion_event_end_date;
	}
	public String getPromotion_event_status() {
		return promotion_event_status;
	}
	public void setPromotion_event_status(String promotion_event_status) {
		this.promotion_event_status = promotion_event_status;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	
	@Override
	public String toString() {
		return "PromotionEvent [promotion_event_id=" + promotion_event_id + ", promotion_event_name=" + promotion_event_name + ", promotion_event_description=" + promotion_event_description
				+ ", promotion_event_start_date=" + promotion_event_start_date + ", promotion_event_end_date=" + promotion_event_end_date
				+ ", promotion_event_status=" + promotion_event_status + ", admin_id=" + admin_id + "]";
	}
}
