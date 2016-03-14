package com.edu.jnu.atm.core;

import java.util.Calendar;

import com.edu.jnu.atm.core.strategy.SvmModel;

public class SVMStrategy extends Strategy {	
	

	@Override
	public double Algorithm(String DEV_CODE, Calendar TRNS_DATE) {
		
		double predictresult = 0;
		
		SVMProcess svmp = new SVMProcess();
		
		/*
		 *select week model or date model 
		 */
		SvmModel model = svmp.svmtrain(DEV_CODE, TRNS_DATE);
		predictresult = svmp.svmpredict(model, DEV_CODE, TRNS_DATE);
	//	SvmModel model = svmp.svmtrain(DEV_CODE, TRNS_DATE);
	//	predictresult = svmp.svmpredict(model, DEV_CODE, TRNS_DATE);
	
		return predictresult;
		
	}

	
}
