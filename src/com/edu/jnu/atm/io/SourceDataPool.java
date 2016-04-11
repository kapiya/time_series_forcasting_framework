package com.edu.jnu.atm.io;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.edu.jnu.atm.util.DateProfile;
/**
 * 从数据库中取出历史数据组成一个按日期索引的HashMap.
 * @author Teacher Lee
 *
 */
public class SourceDataPool implements java.io.Serializable {	
	private static final long serialVersionUID = 1L;

	public ArrayList<DateProfile> getSourceDataPool (String DEV_CODE, Calendar TRNS_DATE, int HISTORY_DAYS) {	
		double value = 0;
		ArrayList<DateProfile> sourcedata = new ArrayList<>();
		
		for (int i = 0; i < HISTORY_DAYS; i++) {           
			TRNS_DATE.add(Calendar.DATE,-1);	
			SourceData dbcon;
			
			//select the Database: MySQL or Oracle 	 
			DBFactory datafactory = new MySQLFactory();
		    //DBFactory datafactory = new OracleFactory();

			dbcon = datafactory.getDBConnection();
			value = dbcon.getSourceData(DEV_CODE, TRNS_DATE);
			DateProfile df = new DateProfile();
			df.DATE = TRNS_DATE;
			df.value = value;
			sourcedata.add(df);

		}	
		return sourcedata;				
	}
		
}
