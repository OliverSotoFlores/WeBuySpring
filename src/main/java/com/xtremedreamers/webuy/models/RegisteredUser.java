package com.xtremedreamers.webuy.models;

public class RegisteredUser {

	private int id;
    private String email;
    private String password;
    private String fullname;
    private String address;
    private String number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "RegisteredUser [id=" + id + ", email=" + email + ", password=" + password + ", fullname=" + fullname
				+ ", address=" + address + ", number=" + number + "]";
	}
    
}
