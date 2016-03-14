package com.edu.jnu.atm.core;

import java.util.Calendar;

public class SingleDateForecast {
	
	
	public double SingleDateForecast (String DEV_CODE, Calendar TRNS_DATE) {
		
		double bpforecastresult = 0, svmforecastresult = 0, forecastresult = 0;
		ForecastContext forecastcontext0;
		forecastcontext0 = new ForecastContext (new BPStrategy ());
	    bpforecastresult = forecastcontext0.forcast(DEV_CODE, TRNS_DATE);
		
	    ForecastContext forecastcontext1;
		forecastcontext1 = new ForecastContext (new SVMStrategy ());
	    svmforecastresult = forecastcontext1.forcast(DEV_CODE, TRNS_DATE);
    	
	    forecastresult = 0.7 * bpforecastresult + 0.3 * svmforecastresult;		
		return forecastresult;	
		
	}
	
	
}
