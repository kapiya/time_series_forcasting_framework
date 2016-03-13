package com.edu.jnu.atm.io;

public class OracleFactory implements DataFactory {

	
	@Override
	public SourceData getDBConnection () {

		return new OracleData();
		
	}

	
}
