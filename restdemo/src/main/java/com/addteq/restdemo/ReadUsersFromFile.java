package com.addteq.restdemo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.addteq.restdemo.modal.UserNames;

public class ReadUsersFromFile {
	private String  fileName;
	
	public void setFileName(String fileName) {
		this.fileName=fileName;
	}
	public String getFileName()
	{
		return fileName;
	}
	public List<UserNames> getAllUsers()
	{
		List<UserNames> list=new ArrayList<UserNames>();
		System.out.println("File name:"+fileName);
        ClassLoader classLoader = new ReadUsersFromFile().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        System.out.println("File Found : " + file.exists());
        
		try(Stream<String> stream = Files.lines(Paths.get(file.toURI())))
		{
			stream.filter(s->s.trim().length()>0).forEach((s)->{
				System.out.println(s);
				String data[]=s.split(",");
				list.add(new UserNames(data[0], data[1]));
			});
		}
		catch(Exception ex)
		{
			System.out.println("Error in file operation:"+ex);
		}
		return(list);
	}
}
