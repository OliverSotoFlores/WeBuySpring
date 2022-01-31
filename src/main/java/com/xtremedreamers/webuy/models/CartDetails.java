package com.xtremedreamers.webuy.models;

import java.math.BigDecimal;

public class CartDetails {

	private int id;
	private int quantity;
	private BigDecimal shoppingCost;
	private BigDecimal costCoupon;
	private int productId;
	private String productName;
	private String productImage;
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
	public BigDecimal getCostCoupon() {
		return costCoupon;
	}
	public void setCostCoupon(BigDecimal costCoupon) {
		this.costCoupon = costCoupon;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	@Override
	public String toString() {
		return "CartDetails [id=" + id + ", quantity=" + quantity + ", shoppingCost=" + shoppingCost + ", costCoupon="
				+ costCoupon + ", productId=" + productId + ", productName=" + productName + ", shoppingCartId="
				+ shoppingCartId + "]";
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	
}
