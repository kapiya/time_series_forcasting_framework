package com.edu.jnu.atm.core;

import java.util.ArrayList;

import com.edu.jnu.atm.core.strategy.SvmModel;
import com.edu.jnu.atm.util.DateProfileUtil;

public class SVMStrategy extends Strategy {

	@Override
	public double Algorithm(ArrayList<DateProfileUtil> sourcedata) {
		double predictresult = 0;
		SVMProcess svmp = new SVMProcess();

		// select week model or date model
		 SvmModel datemodel = svmp.svmtrain(sourcedata);
		 predictresult = svmp.svmpredict(datemodel, sourcedata);

	//	SvmModel weekmodel = svmp.svmtrain(sourcedata, 6);
	//	predictresult = svmp.svmpredict(weekmodel, sourcedata);

		return predictresult;
	}
	
}
