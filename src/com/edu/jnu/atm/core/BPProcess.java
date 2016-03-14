package com.edu.jnu.atm.core;

import java.util.Calendar;

import com.edu.jnu.atm.core.strategy.BP;

public class BPProcess {
	
	
	public BP bptrain (String DEV_CODE, Calendar TRNS_DATE) {
		
		BP model = new BP(5,2,1); 
		DBConnection node = new DBConnection();
		Calendar DATE = Calendar.getInstance();
		//for (int iter = 0; iter < 10; iter++) {
			DATE.set(2014, 6, 11);
			for (int i = 0; i < 365; i++)
			{
				double[] nodeList = new double[6];
	            for (int j = 0; j < 6; j++)
	            {
	            	double[] nodes = node.getNode(DEV_CODE, DATE);
	            	nodeList[j] = nodes[TYPE];
	            	DATE.add(Calendar.DATE,1);
	            }
	            double[] inData = new double[5];
	            for (int k = 0; k < 5; k++)
	            {
	            	inData[k] = nodeList[k];
	            }
	            double[] target = new double[1];
	            target[0] = nodeList[5];
	        	model.train(inData, target);
	        	DATE.add(Calendar.DATE,-5);
			}	
	//	}				
		return model;
		
	}
	
	public double bppredict (BP BpModel, String DEV_CODE, Calendar TRNS_DATE) {
		
		double[] inData = new double[5];
		TRNS_DATE.add(Calendar.DATE,-5);
		for (int i = 0; i < 5; i++) {
			DBConnection node = new DBConnection();			
			double[] nodes = node.getNode(DEV_CODE, TRNS_DATE);
			inData[i] = nodes[TYPE];	 
			TRNS_DATE.add(Calendar.DATE, 1);
		}
		double[] H = BpModel.test(inData);
		double PredictingResult = H[0];
		return PredictingResult;
		
	}
	
	
}
