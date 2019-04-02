package com.addteq.restdemo.modal;

import java.util.List;

import org.springframework.stereotype.Repository;

public interface UserNameRepository
{
	public List<UserNames> getUserNamesFromDB();
	public List<UserNames> searchUserNamesFromDB(String name);
	public String saveAllUsersInDB(List<UserNames> list);
}
