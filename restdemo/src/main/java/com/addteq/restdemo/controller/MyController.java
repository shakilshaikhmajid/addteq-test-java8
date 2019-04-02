package com.addteq.restdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addteq.restdemo.ReadUsersFromFile;
import com.addteq.restdemo.modal.UserNameRepository;
import com.addteq.restdemo.modal.UserNames;

@RestController
public class MyController {
	@Autowired
	UserNameRepository userrepo;
	
	@RequestMapping("test")
	public String test()
	{
		return("My Test OK");
	}
	
	@PostMapping("test")
	public String posttest()
	{
		return("This is post testing");
	}
	
	
	public List<UserNames> getUsersFromFileMethod()
	{
		
		List<UserNames> allusers;
		ReadUsersFromFile readUsersFromFile=new ReadUsersFromFile();
		readUsersFromFile.setFileName("resources/usernames.txt");
		
		allusers=readUsersFromFile.getAllUsers();
		return allusers;
	}
	@GetMapping(value= "getusersfromfile")
	public List<UserNames> getusersfromfile()
	{
		
		List<UserNames> allusers=getUsersFromFileMethod();
		return allusers;
	}
	
	@GetMapping(value= "getUserNamesFromDB")
	public List<UserNames> getUserNamesFromDB()
	{
		System.out.println("Inside get from db");
		List<UserNames> allusers=userrepo.getUserNamesFromDB();
		return allusers;
	}
	
	@GetMapping(value= "searchUserNamesFromDB/{name}")
	public List<UserNames> serahcUserNamesFromDB(@PathVariable("name")String name)
	{
		System.out.println("path valriable username="+name);
		List<UserNames> allusers=userrepo.searchUserNamesFromDB(name);
		return allusers;
	}
	
	//To call getusersfromfile/save we do not require to set the json data in raw 
	// of postman. It will retrive data from file and send to the DB
	@PostMapping(value= "getusersfromfile/save")
	public String saveUsersToDB()
	{
		String msg;
		List<UserNames> allusers=getUsersFromFileMethod();
		msg=userrepo.saveAllUsersInDB(allusers);
		return msg;
	}
}
