package com.edu.jnu.atm.io;

import java.util.ArrayList;
import java.util.Calendar;
import com.edu.jnu.atm.util.DateProfileUtil;

/**
 * 从数据库中取出历史数据组成一个按日期索引的HashMap.
 * 
 * @author Teacher Lee
 *
 */
public class SourceDataPool implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public ArrayList<DateProfileUtil> getSourceDataPool(String DEV_CODE, Calendar TRNS_DATE, int HISTORY_DAYS,
			DBConnectionPool connPool) {
		double value = 0;
		ArrayList<DateProfileUtil> sourcedata = new ArrayList<>();
		TRNS_DATE.add(Calendar.DATE, -HISTORY_DAYS);

		// 历史数据放入sourcedata
		for (int i = 0; i < HISTORY_DAYS; i++) {
			SourceData dbcon;

			// select the Database: MySQL or Oracle
			DBFactory datafactory = new MySQLFactory();
			// DBFactory datafactory = new OracleFactory();

			dbcon = datafactory.getDBConnection();
			value = dbcon.getSourceData(DEV_CODE, TRNS_DATE, connPool);
			DateProfileUtil df = new DateProfileUtil();
			df.DATE = TRNS_DATE;
			df.value = value;
			sourcedata.add(df);
			TRNS_DATE.add(Calendar.DATE, 1);

		}
		return sourcedata;
	}

}
