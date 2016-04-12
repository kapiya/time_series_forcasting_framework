package com.edu.jnu.atm.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.edu.jnu.atm.util.DateProfileUtil;

/**
 * ²ßÂÔÑ¡ÔñContext
 * @author Teacher Lee
 *
 */
public class ForecastContext {	
	private Strategy stg;
	public ForecastContext (Strategy stg)
	{
		this.stg =stg;
	}
	
	public double forcast (ArrayList<DateProfileUtil> sourcedata)
	{
		double predictingresult = stg.Algorithm(sourcedata);
		return predictingresult;
	}
	
	
}
