package com.edu.jnu.atm.core;

import java.util.Calendar;
import com.edu.jnu.atm.core.strategy.BP;
import com.edu.jnu.atm.core.strategy.BPProcess;

public class BPStrategy extends Strategy {
	

	@Override
	public double Algorithm (String DEV_CODE, Calendar TRNS_DATE) {
		
		BPProcess bpp = new BPProcess();
		BP bp = bpp.bptrain(DEV_CODE,TRNS_DATE);
		double predictingresult = bpp.bppredict(bp, DEV_CODE, TRNS_DATE);
		return predictingresult;
	
	}
	
	
}
