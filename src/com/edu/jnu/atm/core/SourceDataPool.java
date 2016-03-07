package com.edu.jnu.atm.core;

import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;

import com.edu.jnu.atm.util.DBConnection;

public class SourceDataPool implements java.io.Serializable
{	
	public ArrayList<Double> SourceDataPool (String DEV_CODE, Calendar TRNS_DATE) 
	{			
		ArrayList<Double> SourceDataList = new ArrayList<Double>(); 
		DBConnection node = new DBConnection();
	    int HistoryDays = 30;
		for (int i = 0; i < HistoryDays; i ++) 
		{
			TRNS_DATE.add(Calendar.DATE, -1);
			nodeList = node.getNode(DEV_CODE, TRNS_DATE);
			PreviousDays[i]= nodeList[TYPE];
		}		
		TRNS_DATE.add(Calendar.DATE, 7);
	}
}
