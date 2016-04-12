package com.edu.jnu.atm.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.edu.jnu.atm.io.DBFactory;
import com.edu.jnu.atm.io.MySQLFactory;
import com.edu.jnu.atm.io.SourceData;
import com.edu.jnu.atm.io.SourceDataPool;
import com.edu.jnu.atm.util.DateProfileUtil;

/**
 * 实现单个日期的预测
 * @author Teacher Lee
 *
 */
public class SingleDateForecast {
	
	
	public double[] forecast (String DEV_CODE, Calendar TRNS_DATE, int HISTORY_DAYS ) {	
		//历史数据放入源数据池
		SourceDataPool sdp = new SourceDataPool();
		ArrayList<DateProfileUtil> sourcedata = sdp.getSourceDataPool(DEV_CODE, TRNS_DATE, HISTORY_DAYS); 
		TRNS_DATE.add(Calendar.DATE, HISTORY_DAYS);//日期恢复
		double bpforecastresult = 0, svmforecastresult = 0, forecastresult = 0;
	/*	ForecastContext forecastcontext0;
		forecastcontext0 = new ForecastContext (new BPStrategy ());
	    bpforecastresult = forecastcontext0.forcast(sourcedata);
	*/	
	    ForecastContext forecastcontext1;
		forecastcontext1 = new ForecastContext (new SVMStrategy ());
	    svmforecastresult = forecastcontext1.forcast(sourcedata);
    	
	 //   forecastresult = 0.7 * bpforecastresult + 0.3 * svmforecastresult;	
	  
	    SourceData dbcon;
	    //select the Database: MySQL or Oracle 	 
		DBFactory datafactory = new MySQLFactory();
	    //DBFactory datafactory = new OracleFactory();

		dbcon = datafactory.getDBConnection();
		double realValue = dbcon.getSourceData(DEV_CODE, TRNS_DATE);
		double[] result = new double[2];
		result[0] = realValue;
		result[1] = svmforecastresult;
		return result;	
		
	}
	
	
}
