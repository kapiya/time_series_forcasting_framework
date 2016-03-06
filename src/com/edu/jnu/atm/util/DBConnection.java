package com.edu.jnu.atm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Statement;

public class DBConnection
{				
	public double[] getNode (String DEV_CODE, Calendar TRANS_DATE) 
	{
		/**
		 * DEV_CODE,TRANS_DATE as input, value of DEPOSIT,WITHDRAW,NETVALUE in String[] as output
		 * String[3] =String[DEPOSIT],String[WITHDRAW],String[NETVALUE]
		 */
		String DEPOSIT = new String();
		String WITHDRAW = new String();
		String NETVALUE = new String();
		double[] SqlResult = new double[3];
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");			
	    String TRNS_DATE = dateformat.format(TRANS_DATE.getTime());
		Connection con = null;
	//	PreparedStatement pre = null;
	    java.sql.Statement pre = null;
		ResultSet result = null;
		//String sql = "SELECT DEPOSIT,WITHDRAW,NETVALUE FROM ATM WHERE DEV_CODE = " + DEV_CODE + " AND TRNS_DATE = " + TRNS_DATE;
		String sql = "SELECT DEPOSIT,WITHDRAW,NETVALUE FROM guangfa WHERE TRNS_DATE = " + TRNS_DATE;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ATM?autoReconnect=true&useSSL=false";
			String user = "root";
			String password = "administrator";
			con = DriverManager.getConnection(url,user,password);
			//pre = con.prepareStatement(sql);
			pre = con.createStatement();
			result = pre.executeQuery(sql);
			while (result.next()) { 
				SqlResult[0] = result.getDouble(1);
				SqlResult[1] = result.getDouble(2);
				SqlResult[2] = result.getDouble(3);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while interact with Database!");
		}
		finally 
		{
			try 
			{
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}		
		return SqlResult;
	}
	
}
