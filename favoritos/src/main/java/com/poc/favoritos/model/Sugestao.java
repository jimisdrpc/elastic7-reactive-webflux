package com.poc.favoritos.model;

public class Sugestao {

	private String id;

	// private Long timestamp;

	private String name;

	private String phone;

	private String account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Sugestao [id=" + id + ", name=" + name + ", phone=" + phone + ", account=" + account + "]";
	}

}
