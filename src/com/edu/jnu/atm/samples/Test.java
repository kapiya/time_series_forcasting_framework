package com.edu.jnu.atm.samples;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Test 
{	
	private static String DEV_CODE = "990030270001";   
	//DEV_CODE is the string of device code.
	private static String TRNS_DATE = "20140912";
	//TRNS_DATE is the starting date of your predict.
	private static int NumberOfPredictingDate = 400;
	//NumberOfPredictingDate is the number of date you hope to predict.
    
	public static void main(String args[]) throws Exception
	{		
		Window Wnd = new Window();
		Wnd.NumberOfPredictingDate = NumberOfPredictingDate;
	    Wnd.forcast(DEV_CODE, TRNS_DATE);	    
	}
}
