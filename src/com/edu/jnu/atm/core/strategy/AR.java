package com.edu.jnu.atm.core.strategy;
import java.util.*;

public class AR {
	
	double[] stdoriginalData={};
	int p;
	ARMAMath armamath=new ARMAMath();
	
	/**
	 * AR模型
	 * @param stdoriginalData
	 * @param p //p为MA模型阶数
	 */
	public AR(double [] stdoriginalData,int p)
	{
		this.stdoriginalData=stdoriginalData;
		this.p=p;
	}
	
	public Vector<double[]> ARmodel()
	{
		Vector<double[]> v=new Vector<double[]>();
		v.add(armamath.parcorrCompute(stdoriginalData, p, 0));
		return v;//得到了自回归系数
		
		//还要估计方差项吗？
	}
	
}
