package com.edu.jnu.atm.core.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.edu.jnu.atm.util.DBConnection;

public class SVMStrategy extends Strategy
{	
	public SvmModel svmtrain(String DEV_CODE, Calendar TRNS_DATE)
	{
		/**
		 * dateNumber is the number of training days, TYPE = 0 means DEPOSIT, TYPE = 1 means WITHDRAW, TYPE = 2 means NETVALUE
		 * a model is trained as the output   
		 */
		String trnsdate = new String();
		double[] n = new double[3];
		List<Double> nodes = new ArrayList<Double>();
		DBConnection nodeList = new DBConnection(); 
        List<SvmNode[]> nodeSet = new ArrayList<SvmNode[]>();
        List<Double> label = new ArrayList<Double>();
        

        for (int i = 0; i < SvmTrainingDate; i++) 
        {
        	TRNS_DATE.add(Calendar.DATE, -(SvmInputDate+1));
    		for (int k = 0; k < (SvmInputDate+1); k++) 
    		{
    			n = nodeList.getNode(DEV_CODE, TRNS_DATE);
    			nodes.add(n[TYPE]);
    			TRNS_DATE.add(Calendar.DATE, 1);
    		}    		
    		SvmNode[] vector = new SvmNode[nodes.size() - 1];
            for (int j = 0; j < nodes.size() - 1; j++) 
            {
             	 SvmNode node = new SvmNode();
                 node.index = j + 1;
                 node.value = nodes.get(j);
                 vector[j] = node;
            }
            nodeSet.add(vector);
            double lablevalue = nodes.get(nodes.size() - 1);
            label.add(lablevalue);
            TRNS_DATE.add(Calendar.DATE, -1);
    	}       	 
        TRNS_DATE.add(Calendar.DATE, SvmTrainingDate);
        int dataRange = nodeSet.get(0).length;
        SvmNode[][] datas = new SvmNode[nodeSet.size()][dataRange]; 
        for (int i = 0; i < datas.length; i++)
        {
        	for (int j = 0; j < dataRange; j++) 
        	{
        		datas[i][j] = nodeSet.get(i)[j];
            }
        }
        double[] lables = new double[label.size()]; 
        for (int i = 0; i < lables.length; i++) 
        {
        	lables[i] = label.get(i);
        }
        
        SvmProblem problem = new SvmProblem();
        problem.l = nodeSet.size(); 
        problem.x = datas; 
        problem.y = lables; 
        SvmParameter param = new SvmParameter();
        param.svm_type = SvmParameter.EPSILON_SVR;
        param.kernel_type = SvmParameter.RBF;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1.9;
        SvmModel model = Svm.svm_train(problem, param);
        return model;
	}
	
	public SvmModel svmtrain(String DEV_CODE, Calendar TRNS_DATE)
	{
		/**
		 * dateNumber is the number of training days, TYPE = 0 means DEPOSIT, TYPE = 1 means WITHDRAW, TYPE = 2 means NETVALUE
		 * a model is trained as the output   
		 */
		String trnsdate = new String();
		double[] n = new double[3];
		List<Double> nodes = new ArrayList<Double>();
		DBConnection nodeList = new DBConnection(); 
        List<SvmNode[]> nodeSet = new ArrayList<SvmNode[]>();
        List<Double> label = new ArrayList<Double>();
        
        TRNS_DATE.add(Calendar.DATE, -7);
        for (int i = 0; i < SvmTrainingDate; i++) 
        {
        	TRNS_DATE.add(Calendar.DATE, -6);
    		for (int k = 0; k < 7; k++)
    		{
    			n = nodeList.getNode(DEV_CODE, TRNS_DATE);
    			nodes.add(n[TYPE]);
    			TRNS_DATE.add(Calendar.DATE, 1);
    		}    		
    		SvmNode[] vector = new SvmNode[nodes.size() - 1];
            for (int j = 0; j < nodes.size() - 1; j++)
            {
             	 SvmNode node = new SvmNode();
                 node.index = j + 1;
                 node.value = nodes.get(j);
                 vector[j] = node;
            }
            nodeSet.add(vector);
            double lablevalue = nodes.get(nodes.size() - 1);
            label.add(lablevalue);
            TRNS_DATE.add(Calendar.DATE, -8);
    	}       	 
        TRNS_DATE.add(Calendar.DATE, (SvmTrainingDate + 1) * 7);
        int dataRange = nodeSet.get(0).length;
        SvmNode[][] datas = new SvmNode[nodeSet.size()][dataRange]; 
        for (int i = 0; i < datas.length; i++)
        {
        	for (int j = 0; j < dataRange; j++) 
        	{
        		datas[i][j] = nodeSet.get(i)[j];
            }
        }
        double[] lables = new double[label.size()]; 
        for (int i = 0; i < lables.length; i++)
        {
        	lables[i] = label.get(i);
        }
        
        SvmProblem problem = new SvmProblem();
        problem.l = nodeSet.size(); 
        problem.x = datas; 
        problem.y = lables; 
        SvmParameter param = new SvmParameter();
        param.svm_type = SvmParameter.EPSILON_SVR;
        param.kernel_type = SvmParameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1.9;
        SvmModel model = Svm.svm_train(problem, param);
        return model;
	}
	
	public double svmpredict(SvmModel model, String DEV_CODE, Calendar TRNS_DATE)
	{
        /**
         * TYPE = 0 means DEPOSIT,TYPE = 1 means WITHDRAW,TYPE = 2 means NETVALUE
         * return the true value and the predict result
         */
		List<Double> nodes = new ArrayList<Double>();
		double[] n = new double[3];
		DBConnection nodeList = new DBConnection();      
		TRNS_DATE.add(Calendar.DATE, -SvmInputDate);
		for (int k = 0; k < SvmInputDate; k++) 
		{
			n = nodeList.getNode(DEV_CODE, TRNS_DATE);
			nodes.add(n[TYPE]);
			TRNS_DATE.add(Calendar.DATE, 1);
		}
    	SvmNode[] vector = new SvmNode[nodes.size() - 1];   	
        for (int j = 0; j < nodes.size() - 1; j++) 
        {
             SvmNode node = new SvmNode();
             node.index = j + 1;
             node.value = nodes.get(j);
             vector[j] = node;
        }
        double lable = nodes.get(nodes.size() - 1);   	       	 
        double predictValue = Svm.svm_predict(model, vector);
        return predictValue;
    }	
	
	public double svmpredict(SvmModel model, String DEV_CODE, Calendar TRNS_DATE)
	{
        /**
         * TYPE = 0 means DEPOSIT,TYPE = 1 means WITHDRAW,TYPE = 2 means NETVALUE
         * return the true value and the predict result
         */
		List<Double> nodes = new ArrayList<Double>();
		double[] n = new double[3];
		DBConnection nodeList = new DBConnection();      
		TRNS_DATE.add(Calendar.DATE, -6);
		for (int k = 0; k < 6; k++)
		{
			n = nodeList.getNode(DEV_CODE, TRNS_DATE);
			nodes.add(n[TYPE]);
			TRNS_DATE.add(Calendar.DATE, 1);
		}
    	SvmNode[] vector = new SvmNode[nodes.size() - 1];   	
        for (int j = 0; j < nodes.size() - 1; j++) 
        {
             SvmNode node = new SvmNode();
             node.index = j + 1;
             node.value = nodes.get(j);
             vector[j] = node;
        }
        double lable = nodes.get(nodes.size() - 1);   	       	 
        double predictValue = Svm.svm_predict(model, vector);
        return predictValue;
    }

	@Override
	public double Algorithm(String DEV_CODE, Calendar TRNS_DATE) {
		SVMStrategy ss = new SVMStrategy();
		SvmModel sm = ss.svmtrain(DEV_CODE, TRNS_DATE);
		double ForecastResult = ss.svmpredict(sm, DEV_CODE, TRNS_DATE);
		return 0;
	}	
}
