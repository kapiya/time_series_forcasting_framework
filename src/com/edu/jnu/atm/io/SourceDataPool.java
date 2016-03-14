package com.edu.jnu.atm.io;

import java.util.Calendar;
import java.util.HashMap;

public class SourceDataPool implements java.io.Serializable {	
	
	
	public HashMap<Calendar,Double> SourceDataPool (String DEV_CODE, Calendar TRNS_DATE, int HistoryDays) {
	
		
		double value = 0;
		HashMap<Calendar, Double> hashmap = new HashMap<Calendar, Double>();
		
		for (int i = 0; i < HistoryDays; i++) {
           
			TRNS_DATE.add(Calendar.DATE,-1);	
			SourceData dbcon;
			
			/*
			 * select the Database: MySQL or Oracle 
			 */
			DataFactory datafactory = new MySQLFactory();
		//	DataFactory datafactory = new OracleFactory();

			dbcon = datafactory.getDBConnection();
			value = dbcon.getSourceData(DEV_CODE, TRNS_DATE);
		
			hashmap.put(TRNS_DATE, value);

		}
		
		return hashmap;		
		
		
	}
	
	
}
