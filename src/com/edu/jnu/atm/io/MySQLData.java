package com.edu.jnu.atm.io;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ¶ÁÈ¡MySQLÊý¾Ý
 * @author Teacher Lee
 *
 */
public class MySQLData extends SourceData {

	/**
	 * DEV_CODE,TRANS_DATE as input, value of the source data as output
	 */
	@Override
	public double getSourceData(String DEV_CODE, Calendar TRANS_DATE, DBConnectionPool connPool) {

		double SqlResult = 0;

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		String TRNS_DATE = dateformat.format(TRANS_DATE.getTime());

		ResultSet result = null;
		String sql = "SELECT WITHDRAW FROM guangfa WHERE TRNS_DATE = " + TRNS_DATE;
		Statement pre = null;
		try {
			Connection con = connPool.getConnection();
			pre = con.createStatement();
			result = pre.executeQuery(sql);
			while (result.next()) {
				SqlResult = result.getDouble(1);
			}
			connPool.returnConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while interact with Database!");
		}
		return SqlResult;
	}

}
