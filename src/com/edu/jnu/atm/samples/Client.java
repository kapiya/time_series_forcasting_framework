package com.edu.jnu.atm.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.edu.jnu.atm.core.DateSeriesForecast;
import com.edu.jnu.atm.core.SingleDateForecast;
import com.edu.jnu.atm.io.SourceDataPool;
import com.edu.jnu.atm.util.DateProfile;
import com.edu.jnu.atm.util.StringToCalendar;

public class Client {
	
	public static void main (String args[]) {			
		String DEV_CODE = ""; //设备号
		String TRNS_DATE = "";//预测起始日期(待预测日期的前一天)
		int dates_of_predict = 0;//预测天数
		final int HISTORY_DAYS  = 30;//过去的历史数据
		double result ;//结果存放
		
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
		
		//转化为日期类
		StringToCalendar stc = new StringToCalendar();
		Calendar TRANS_DATE = stc.ToCalendar(TRNS_DATE);
		
	
		//单个日期预测
		SingleDateForecast dateseries = new SingleDateForecast();
	    result = dateseries.forecast(DEV_CODE, TRANS_DATE, 30);
		
		Window wnd = new Window();
	 //  wnd.show(result);	    
	    
	}
	
	
}
