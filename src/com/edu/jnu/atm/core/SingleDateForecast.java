package com.edu.jnu.atm.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.edu.jnu.atm.io.SourceDataPool;
import com.edu.jnu.atm.util.DateProfile;

/**
 * 实现单个日期的预测
 * @author Teacher Lee
 *
 */
public class SingleDateForecast {
	
	
	public double forecast (String DEV_CODE, Calendar TRNS_DATE, int HISTORY_DAYS ) {	
		//历史数据放入源数据池
		SourceDataPool sdp = new SourceDataPool();
		ArrayList<DateProfile> sourcedata1 = sdp.getSourceDataPool(DEV_CODE, TRNS_DATE, HISTORY_DAYS); 
		
		double bpforecastresult = 0, svmforecastresult = 0, forecastresult = 0;
	/*	ForecastContext forecastcontext0;
		forecastcontext0 = new ForecastContext (new BPStrategy ());
	    bpforecastresult = forecastcontext0.forcast(sourcedata);
	*/	
	    ForecastContext forecastcontext1;
		forecastcontext1 = new ForecastContext (new SVMStrategy ());
	    svmforecastresult = forecastcontext1.forcast(sourcedata1);
    	
	 //   forecastresult = 0.7 * bpforecastresult + 0.3 * svmforecastresult;		
		return svmforecastresult;	
		
	}
	
	
}
