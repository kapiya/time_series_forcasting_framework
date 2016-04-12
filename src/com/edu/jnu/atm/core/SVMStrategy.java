package com.edu.jnu.atm.core;

import java.util.ArrayList;

import com.edu.jnu.atm.core.strategy.SvmModel;
import com.edu.jnu.atm.util.DateProfileUtil;

public class SVMStrategy extends Strategy {	
	
	@Override
	public double Algorithm(ArrayList<DateProfileUtil> sourcedata) {		
		double predictresult = 0;	
		SVMProcess svmp = new SVMProcess();
		
		//select week model or date model 
		SvmModel model = svmp.svmtrain(sourcedata);
		predictresult = svmp.svmpredict(model, sourcedata);
	//	SvmModel model = svmp.svmtrain(DEV_CODE, TRNS_DATE);
	//	predictresult = svmp.svmpredict(model, DEV_CODE, TRNS_DATE);
	
		return predictresult;	
	}

}
