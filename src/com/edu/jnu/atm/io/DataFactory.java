package com.edu.jnu.atm.io;

import java.util.Calendar;

public interface DataFactory {
	
	double getSourceData(String DEV_CODE, Calendar TRANS_DATE);
	
}
