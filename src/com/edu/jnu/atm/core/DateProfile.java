package com.edu.jnu.atm.core;

import java.util.Calendar;

import com.edu.jnu.atm.util.DBConnection;

public class DateProfile implements java.io.Serializable
{
	/*
	 * PreviousDays[7] = Number of [1][2][3][4][5][6][7] days before.
	 */
	public Calendar Day;
	public int DayOfWeek;
	public double[] PreviousDays = new double[7];
	
	public void constructDateProfile (String DEV_CODE, Calendar TRNS_DATE, int TYPE) 
	{					
		DBConnection node = new DBConnection();
		double[] nodeList = new double[3];
		double[] PredictingResult = {0,0};
		
		Day = TRNS_DATE;
		if (TRNS_DATE.get(Calendar.DAY_OF_WEEK) == 1)
		{
			DayOfWeek =7;
		}
		else
		{
			DayOfWeek = TRNS_DATE.get(Calendar.DAY_OF_WEEK) - 1;
		}
		for (int i = 0; i < 7; i ++) 
		{
			TRNS_DATE.add(Calendar.DATE, -1);
			nodeList = node.getNode(DEV_CODE, TRNS_DATE);
			PreviousDays[i]= nodeList[TYPE];
		}		
		TRNS_DATE.add(Calendar.DATE, 7);
	}
}
