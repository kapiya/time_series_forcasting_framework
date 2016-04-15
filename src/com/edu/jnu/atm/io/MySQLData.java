package com.edu.jnu.atm.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MySQLData extends SourceData {
	
	
	/**
	 * DEV_CODE,TRANS_DATE as input, value of the source data as output
	 */
	@Override
	public double getSourceData (String DEV_CODE, Calendar TRANS_DATE, DBConnectionPool connPool) {
	
		double SqlResult = 0;
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");			
		String TRNS_DATE = dateformat.format(TRANS_DATE.getTime());
		
		//Connection con = null;
        ResultSet result = null;
		String sql = "SELECT WITHDRAW FROM guangfa WHERE TRNS_DATE = " + TRNS_DATE;
		Statement pre = null;
		try 
		{
		//	Class.forName("com.mysql.jdbc.Driver");
		//	String url = "jdbc:mysql://localhost:3306/ATM?autoReconnect=true&useSSL=false";
		//	String user = "root";
		//	String password = "administrator";
		//	con = DriverManager.getConnection(url,user,password);
            Connection con = connPool.getConnection();

			pre = con.createStatement();
			result = pre.executeQuery(sql);
			while (result.next()) { 
				SqlResult = result.getDouble(1);
			}
			connPool.returnConnection(con);
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while interact with Database!");
		}
	//	finally 
	//	{
		/*	try 
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
			*/
	//	}		
		return SqlResult;	
	}
	
	
}
