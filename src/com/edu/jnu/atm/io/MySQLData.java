package com.edu.jnu.atm.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MySQLData extends SourceData {
	
	
	/**
	 * DEV_CODE,TRANS_DATE as input, value of the source data as output
	 */
	@Override
	public double getSourceData (String DEV_CODE, Calendar TRANS_DATE) {
	
		double SqlResult = 0;
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");			
		String TRNS_DATE = dateformat.format(TRANS_DATE.getTime());
		
		Connection con = null;
        PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "SELECT WITHDRAW FROM guangfa WHERE TRNS_DATE = " + TRNS_DATE;
		try 
		{
            con = DBCP_Pool.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
			while (result.next()) { 
				SqlResult = result.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println ("Error while interact with Database!");
		}		
		finally {
			DBCP_Pool.release (con, ps, result);
		}	
		
		return SqlResult;
	}
	
	
}
