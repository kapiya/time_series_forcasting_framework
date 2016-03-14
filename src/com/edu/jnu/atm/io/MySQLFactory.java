package com.edu.jnu.atm.io;

public class MySQLFactory implements DataFactory {

	
	@Override
	public SourceData getDBConnection () {

		return new MySQLData();
	}

	
}
