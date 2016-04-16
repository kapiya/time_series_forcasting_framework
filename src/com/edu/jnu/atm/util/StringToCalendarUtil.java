package com.edu.jnu.atm.util;

import java.util.Calendar;

/**
 * translate String "date" into a Calendar object
 */
public class StringToCalendarUtil {

	public static Calendar ToCalendar(String date) {
		int year = Integer.parseInt(date.substring(0, 4).trim());
		int month = Integer.parseInt(date.substring(4, 6).trim());
		int day = Integer.parseInt(date.substring(6, 8).trim());

		Calendar DATE = Calendar.getInstance();
		DATE.set(year, month - 1, day);
		return DATE;
	}

}
