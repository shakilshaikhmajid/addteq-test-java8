package com.addteq.restdemo.component;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:resources/db.properties")
public class MyDBConnection {
	
	@Value("${db.driver_class}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
	private boolean isFirst=true;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public MyDBConnection(String driver,String url, String username, String password) 
	{
		super();
		this.url = url;
		this.username = username;
		this.password = password;
		this.driver = driver;
	}
	public MyDBConnection() {
		super();
		this.driver="org.postgresql.Driver";
		System.out.println(driver);
	}
	public void show()
	{
		System.out.println(driver);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println("OK");
	}
	public void removeQuotesFromString()
	{
		driver=driver.replace('\"',' ').trim();
		url=url.replace('\"',' ').trim();
		username=username.replace('\"',' ').trim();
		password=password.replace('\"',' ').trim();
		isFirst=false;

	}
	public Connection getConnection() throws Exception
	{
		
		if(isFirst)
		{
			removeQuotesFromString();
		}
		Class.forName(driver);
		Connection cn=DriverManager.getConnection(url,username,password);
		return cn;
	}
}
