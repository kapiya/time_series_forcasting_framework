package com.edu.jnu.atm.samples;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.edu.jnu.atm.core.Window;

public class Client {
	
	public static void main(String args[]) throws Exception
	{		
		String DEV_CODE = "";
		String TRNS_DATE = "";
		int NumberOfPredictingDate = 0;
		
		try{
			
			System.out.println("Please input the DEV_CODE :");
			BufferedReader br0 = new BufferedReader(new InputStreamReader(System.in));
			DEV_CODE = br0.readLine();
			System.out.println("Please input the TRNS_DATE:");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			TRNS_DATE = br1.readLine();
			System.out.println("Please input the number of predicting date:");
			BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
			NumberOfPredictingDate = Integer.parseInt(br2.readLine());			
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		int i;
		switch (i)
		case 1:
		{
			Window Wnd = new Window();
		    Wnd.NumberOfPredictingDate = NumberOfPredictingDate;
	        Wnd.forcast(DEV_CODE, TRNS_DATE);	    
	    }
		case 2:
		{
			System.out.println("");
		}
}
