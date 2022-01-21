package com.xtremedreamers.webuy.models;

import java.math.BigDecimal;

public class ShoppingProductDetails {
	private int id;
	private int quantity;
	private BigDecimal shoppingCost;
	private BigDecimal costAfterCoupon;
	private int productId;
	private int shoppingCartId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getShoppingCost() {
		return shoppingCost;
	}
	public void setShoppingCost(BigDecimal shoppingCost) {
		this.shoppingCost = shoppingCost;
	}
	public BigDecimal getCostAfterCoupon() {
		return costAfterCoupon;
	}
	public void setCostAfterCoupon(BigDecimal costAfterCoupon) {
		this.costAfterCoupon = costAfterCoupon;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	
	@Override
	public String toString() {
		return "ShoppingProductDetails [id="+ id + ", quantity=" + quantity + ", shoppingCost=" + shoppingCost
				+ ", costAfterCoupon=" + costAfterCoupon + ", productId=" + productId + ", shoppingCartId" + shoppingCartId  + "]";
	}
}
