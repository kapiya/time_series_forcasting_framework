package com.edu.jnu.atm.io;

import java.util.Calendar;

public class MySQLFactory implements DataFactory {

	
	@Override
	public SourceData getDBConnection () {

		return new MySQLData();
	}

	
}
