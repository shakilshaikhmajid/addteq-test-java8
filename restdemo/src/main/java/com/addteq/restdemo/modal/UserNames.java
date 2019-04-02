package com.addteq.restdemo.modal;


public class UserNames {
	private String name;
	private String username;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserNames(String name, String username) {
		super();
		this.name = name;
		this.username = username;
	}
	public UserNames() {
		super();
	}
	
	
}
