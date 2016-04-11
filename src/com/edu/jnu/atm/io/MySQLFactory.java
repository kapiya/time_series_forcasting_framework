package com.edu.jnu.atm.io;

public class MySQLFactory implements DBFactory {	
	@Override
	public SourceData getDBConnection () {
		return new MySQLData();
	}	
}
