package com.edu.jnu.atm.core.strategy;

import java.util.*;

/**
 * AR模型
 * 
 * @author Teacher Lee
 *
 */
public class AR {

	double[] stdoriginalData;
	int p;
	ARMAMath armamath = new ARMAMath();

	/**
	 * AR模型
	 * 
	 * @param stdoriginalData//预处理过后的数据
	 * @param p
	 *            //p为MA模型阶数
	 */
	public AR(double[] stdoriginalData, int p) {
		this.stdoriginalData = stdoriginalData;
		this.p = p;
	}

	/**
	 * 得到AR模型的自回归系数
	 * 
	 * @return
	 */
	public Vector<double[]> ARmodel() {
		Vector<double[]> v = new Vector<double[]>();
		v.add(armamath.parcorrCompute(stdoriginalData, p, 0));
		return v;
	}

}
