package com.ars.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.ars.dao.BookingDAO;

public class DBUtil 
{
	private static final Logger LOG=Logger.getLogger(DBUtil.class);
/*	
	public static void main(String[] args) throws Exception
	{
		System.out.println(DBUtil.getConnected());
		
	}*/
public static Connection getConnected() 
{
	
	ResourceBundle bundle=ResourceBundle.getBundle("database");
	Connection con=null;
	try {
		Class.forName(bundle.getString("driver"));
	
     con=DriverManager.getConnection(bundle.getString("url"),
    		                        bundle.getString("username"), 
    		                        bundle.getString("password"));
	} catch (Exception e) {
		LOG.error(e.getMessage());
		e.printStackTrace();
	}
	return con;
}
}
