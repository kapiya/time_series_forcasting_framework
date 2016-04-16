package com.edu.jnu.atm.util;

import java.util.List;

/**
 * ŒÛ≤Ó∆¿π¿
 * 
 * @author Teacher Lee
 *
 */
public class ErrorEvaluationUtil {

	public static double computeError(List<Double> sourceList, List<Double> predictList) {
		double ER = 0.3;
		int n = 0;

		for (int i = 0; i < sourceList.size(); i++) {
			if (Math.abs(predictList.get(i) - sourceList.get(i)) / sourceList.get(i) < ER) {
				n++;
			}
		}

		double rate = n / (double) sourceList.size();
		return rate;
	}
}
