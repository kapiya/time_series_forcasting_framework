package com.edu.jnu.atm.io;

public class OracleFactory implements DBFactory {	
	@Override
	public SourceData getDBConnection () {
		return new OracleData();		
	}	
}
