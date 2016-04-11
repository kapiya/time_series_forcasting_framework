package com.edu.jnu.atm.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.edu.jnu.atm.core.strategy.BP;
import com.edu.jnu.atm.util.DateProfile;

/**
 * 神经网络策略实现预测
 * @author Teacher Lee
 *
 */
public class BPStrategy extends Strategy {

	@Override
	public double Algorithm (ArrayList<DateProfile> sourcedata) {				
		BPProcess bpp = new BPProcess();
		BP model = bpp.bptrain(sourcedata);
		double predictresult = bpp.bppredict(model, sourcedata);
		return predictresult;
	}
	
	
}
