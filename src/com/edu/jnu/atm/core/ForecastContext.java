package com.edu.jnu.atm.core;

import java.util.Calendar;

public class ForecastContext {
	
	
	private Strategy stg;
	public ForecastContext (Strategy stg)
	{
		this.stg =stg;
	}
	
	public double forcast (String DEV_CODE, Calendar TRNS_DATE)
	{
		double predictingresult = stg.Algorithm(DEV_CODE, TRNS_DATE);
		return predictingresult;
	}
	
	
}
