package com.edu.jnu.atm.core;

import java.util.ArrayList;

import com.edu.jnu.atm.util.DateProfileUtil;

/**
 * 数学统计方法的实现
 * 
 * @author Teacher Lee
 *
 */
public class StatisticStrategy extends Strategy {

	@Override
	public double Algorithm(ArrayList<DateProfileUtil> sourcedata) {
		double result = 0;

		// 加权平均		
	 //   result = 0.35 * sourcedata.get(sourcedata.size() - 1).value +
	  //  		 0.25 * sourcedata.get(sourcedata.size() - 2).value + 
	  //  		 0.15 * sourcedata.get(sourcedata.size() - 3).value +
	  //  		 0.10 * sourcedata.get(sourcedata.size() - 4).value + 
	  //  		 0.05 * sourcedata.get(sourcedata.size() - 5).value +
	  //  		 0.05 * sourcedata.get(sourcedata.size() - 6).value +
	  //  		 0.05 * sourcedata.get(sourcedata.size() - 1).value;
		 
	    result = sourcedata.get(sourcedata.size() - 1).value;
		return result;
	}

}
