package com.edu.jnu.atm.core;

import java.util.Calendar;

import com.edu.jnu.atm.io.ResultDataPool;

/**
 * 多个日期的预测
 * @author Teacher Lee
 *
 */
public class DateSeriesForecast {		
	ResultDataPool rdp = new ResultDataPool();
	int HISTORY_DAYS = 30;
	
    public ResultDataPool seriesForest(String DEV_CODE, Calendar TRNS_DATE, int Days) {
		SingleDateForecast SDF = new SingleDateForecast();
    	for (int i = 0; i < Days; i++) {
    		double[] result = SDF.forecast(DEV_CODE, TRNS_DATE, HISTORY_DAYS);
			rdp.sourceList.add(result[0]);
			rdp.predictList.add(result[1]);
			TRNS_DATE.add(Calendar.DATE, 1);
		}
    	return rdp;		
	}
}
