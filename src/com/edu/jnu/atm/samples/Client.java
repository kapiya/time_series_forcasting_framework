package com.edu.jnu.atm.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.edu.jnu.atm.core.DateSeriesForecast;
import com.edu.jnu.atm.io.SourceDataPool;
import com.edu.jnu.atm.util.StringToCalendar;

public class Client {
	
	
	public static void main(String args[], int HistoryDays) throws Exception
	{		
		
		String DEV_CODE = "";
		String TRNS_DATE = "";
		int dates_of_predict = 0;
		final int HISTORY_DAYS  = 30;
		double[][] result = new double[dates_of_predict][2]; 
		
		try{		
			System.out.println("Please input the DEV_CODE :");
			BufferedReader br0 = new BufferedReader(new InputStreamReader(System.in));
			DEV_CODE = br0.readLine();
			System.out.println("Please input the TRNS_DATE:");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			TRNS_DATE = br1.readLine();				
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		StringToCalendar stc = new StringToCalendar();
		Calendar TRANS_DATE = stc.ToCalendar(TRNS_DATE);
		
		SourceDataPool sdp = new SourceDataPool();
		HashMap<Calendar,Double> sourcedata = sdp.SourceDataPool(DEV_CODE, TRANS_DATE, HistoryDays); 
		
		DateSeriesForecast dateseries = new DateSeriesForecast();
	    result = dateseries.forecast(sourcedata, TRNS_DATE, dates_of_predict);
		
		Window wnd = new Window();
	    wnd.show(result);	    
	    
	}
	
	
}
