package com.addteq.restdemo.modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.addteq.restdemo.component.MyDBConnection;

@Repository
public class UserNameRepositoryImpl implements UserNameRepository {

	@Autowired
	MyDBConnection myDBConnection;
	
	@Override
	public List<UserNames> getUserNamesFromDB() {
		// TODO Auto-generated method stub
	   	List<UserNames> list=new ArrayList<UserNames>();
	   	try
	   	{
	   		//MyDBConnection mydb=new MyDBConnection("org.postgresql.Driver","jdbc:postgresql://stampy.db.elephantsql.com:5432/zcxfobqy","zcxfobqy","sY06qww_01TI7aB2ORFNBOg4zw9r_SBF");
	   		myDBConnection.show();
	   		Connection cn=myDBConnection.getConnection();
	   		Statement st=cn.createStatement();
	   		String sql="select name,username from users";
	   		ResultSet rs=st.executeQuery(sql);
	   		
	     	while(rs.next()) list.add(new UserNames(rs.getString("name"),rs.getString("username")));
	    	
	    	rs.close();
	    	st.close();
	    	cn.close();
	    }
	   	catch(Exception ex)
	   	{
	   		System.out.println("Error in getUserNamesFromDB:"+ex);
	   	}
	    return list;
	}
	
	@Override
	public String saveAllUsersInDB(List<UserNames> list) {
		Connection cn=null;
		int count=list.size();
		try
		{
	   		//MyDBConnection mydb=new MyDBConnection("org.postgresql.Driver","jdbc:postgresql://stampy.db.elephantsql.com:5432/zcxfobqy","zcxfobqy","sY06qww_01TI7aB2ORFNBOg4zw9r_SBF");
	   		cn=myDBConnection.getConnection();
	   		cn.setAutoCommit(false);
	   		PreparedStatement pst=cn.prepareStatement("insert into users values(?,?)");
	   		list.stream().forEach(un->{
	   			try {
					pst.setString(1,un.getName());
					pst.setString(2,un.getUsername());
		   			pst.addBatch();
				} catch (SQLException e) {
					System.out.println("Exception inside foreach:"+e);
				}
	   		});
	   		
	   		pst.executeBatch();
	    	pst.close();
	    	cn.commit();
	    	cn.close();	
	    	return "Saved "+count+" UserNames";
	    }
		catch(Exception ex)
		{
			if (cn!=null)
				try {
					cn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			System.out.println("Exception in saveAllUsersInDB:"+ex);
			return "Saved "+0+" UserNames";
		}
	}

	@Override
	public List<UserNames> searchUserNamesFromDB(String name) {
		// TODO Auto-generated method stub
	  	List<UserNames> list=new ArrayList<UserNames>();
	   	try
	   	{
	   		//MyDBConnection mydb=new MyDBConnection("org.postgresql.Driver","jdbc:postgresql://stampy.db.elephantsql.com:5432/zcxfobqy","zcxfobqy","sY06qww_01TI7aB2ORFNBOg4zw9r_SBF");
	   		Connection cn=myDBConnection.getConnection();
	   		Statement st=cn.createStatement();
	   		String sql="select name,username  from users where username like '%"+name+"%'";
	   		ResultSet rs=st.executeQuery(sql);
    	
	     	while(rs.next()) list.add(new UserNames(rs.getString("name"),rs.getString("username")));
	     	
	     	rs.close();
	    	st.close();
	    	cn.close();
	   	}
	   	catch(Exception ex)
	   	{
	   		System.out.println("Error in getUserNamesFromDB:"+ex);
	   	}
	    return list;
		
	}

}
