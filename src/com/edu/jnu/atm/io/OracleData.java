package com.edu.jnu.atm.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OracleData extends SourceData {
	
	
	/**
	 * DEV_CODE,TRANS_DATE as input, value of the source data as output
	 */
	@Override
	public double getSourceData (String DEV_CODE, Calendar TRANS_DATE, DBConnectionPool connPool) {
		
		double SqlResult = 0;
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");			
		String TRNS_DATE = dateformat.format(TRANS_DATE.getTime());
		
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String sql = "SELECT NETVALUE FROM ATM WHERE DEV_CODE = " + DEV_CODE + "AND TRNS_DATE = " + TRNS_DATE;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:ATM";
			String user = "jimmy";
			String password = "jimmy";
			con = DriverManager.getConnection(url,user,password);
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			while (result.next()) { 
				SqlResult = result.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while interact with Database!");
		}
		finally {
			try {
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return SqlResult;
	}	
	
	
}
