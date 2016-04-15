package com.edu.jnu.atm.core;

import java.util.ArrayList;

import org.neuroph.core.data.DataSet;
import org.neuroph.util.data.norm.MaxMinNormalizer;

import com.edu.jnu.atm.core.strategy.Svm;
import com.edu.jnu.atm.core.strategy.SvmModel;
import com.edu.jnu.atm.core.strategy.SvmNode;
import com.edu.jnu.atm.core.strategy.SvmParameter;
import com.edu.jnu.atm.core.strategy.SvmProblem;
import com.edu.jnu.atm.util.DateProfileUtil;

public class SVMProcess {
	int SvmInputDate = 6; //模型的输入值个数
	int SvmTrainingDate = 5; //训练天数
    MaxMinNormalizer DSN = new MaxMinNormalizer();

	/**
	 * 天预测模型训练过程
	 * @param sourcedata
	 * @return
	 */
	public SvmModel svmtrain(ArrayList<DateProfileUtil> sourcedata) {
		
        SvmNode[][] datas = new SvmNode[SvmTrainingDate][SvmInputDate]; //输入的训练矩阵
		DataSet trainingSet = new DataSet(SvmInputDate, 1); //训练数据集
        double lables[] = new double[SvmTrainingDate]; //目标输出向量
        
		//得到SVM输入数据格式
        for (int i = 0; i < SvmTrainingDate; i++) {
        	
    		double[] input = new double[SvmInputDate]; //归一化中的暂存矩阵
    		double[] label = new double[1]; //归一化中的暂存标签值矩阵
    		
        	for (int j = 0; j < SvmInputDate; j++) {
        		SvmNode node = new SvmNode();
        		node.index = j+1;
        		node.value = sourcedata.get(sourcedata.size()-(SvmInputDate + 1 + i) + j).value;
        		datas[i][j] = node;
        		input[j] = datas[i][j].value;
        	}
        	lables[i] = sourcedata.get(sourcedata.size()-(SvmInputDate + 1 + i) + SvmInputDate).value;
        	label[0] = lables[i];
        	trainingSet.addRow(input, label);
        }
        
        //数据归一化过程
		DSN.normalize(trainingSet);
		double[] inputVector;
        for (int i = 0; i < SvmTrainingDate; i++) {
        	
    		double[] label = new double[1]; //归一化中的暂存标签值矩阵
    		
        	inputVector = trainingSet.getRowAt(i).getInput();
        	for (int j = 0; j < SvmInputDate; j++) {
        		datas[i][j].value = inputVector[j];
        	}
        	label = trainingSet.getRowAt(i).getDesiredOutput();
        	lables[i] = label[0];
        }
        
        //训练参数设置
        SvmProblem problem = new SvmProblem();
        problem.l = datas.length; 
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
	
	/**
	 * 周预测模型训练过程
	 * @param sourcedata
	 * @return
	 */
    public SvmModel svmtrain(ArrayList<DateProfileUtil> sourcedata, int SvmInputDays) {
		
        SvmNode[][] datas = new SvmNode[SvmTrainingDate][SvmInputDate]; //输入的训练矩阵
		DataSet trainingSet = new DataSet(SvmInputDate, 1); //训练数据集
        double lables[] = new double[SvmTrainingDate]; //目标输出向量
        
		//得到SVM输入数据格式
        for (int i = 0; i < SvmTrainingDate; i++) {
        	
    		double[] input = new double[SvmInputDate]; //归一化中的暂存矩阵
    		double[] label = new double[1]; //归一化中的暂存标签值矩阵
    		
        	for (int j = 0; j < SvmInputDate; j++) {
        		SvmNode node = new SvmNode();
        		node.index = j+1;
        		node.value = sourcedata.get(sourcedata.size() - (SvmInputDate+1) * (i + 2) + j +1).value;
        		datas[i][j] = node;
        		input[j] = datas[i][j].value;
        	}
        	lables[i] = sourcedata.get(sourcedata.size() - (SvmInputDate+1) * (i + 2) + 7).value;
        	label[0] = lables[i];
        	trainingSet.addRow(input, label);
        }
        
        //数据归一化过程
		DSN.normalize(trainingSet);
		double[] inputVector;
        for (int i = 0; i < SvmTrainingDate; i++) {
        	
    		double[] label = new double[1]; //归一化中的暂存标签值矩阵
    		
        	inputVector = trainingSet.getRowAt(i).getInput();
        	for (int j = 0; j < SvmInputDate; j++) {
        		datas[i][j].value = inputVector[j];
        	}
        	label = trainingSet.getRowAt(i).getDesiredOutput();
        	lables[i] = label[0];
        }
        
        //训练参数设置
        SvmProblem problem = new SvmProblem();
        problem.l = datas.length; 
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

	/**
     * TYPE = 0 means DEPOSIT,TYPE = 1 means WITHDRAW,TYPE = 2 means NETVALUE
     * return the true value and the predict result
     */
	public double svmpredict(SvmModel model, ArrayList<DateProfileUtil> sourcedata) {
		
		//得到输入数据格式
        SvmNode[] vector = new SvmNode[SvmInputDate];
        double[] input0 = new double[SvmInputDate],input1 = new double[SvmInputDate]; //归一化暂存数组
		for (int i = 0; i < SvmInputDate; i++) {
        	SvmNode node = new SvmNode();
        	node.index = i+1;
        	node.value = sourcedata.get(sourcedata.size() - SvmInputDate + i).value;
        	vector[i] = node;
        	input0[i] = node.value;
        }
		
		//输入归一化过程
		double MaxIn, MinIn, MaxOut, MinOut;
		for (int i = 0; i < SvmInputDate; i++) {
			MaxIn = DSN.getMaxIn(i);
			MinIn = DSN.getMinIn(i);
		    input1[i] = (input0[i] - MinIn) / (MaxIn - MinIn);
		}
		for (int i = 0; i < SvmInputDate; i++) {
			vector[i].value = input1[i];
		}
		
		//预测
    	double predictValue = Svm.svm_predict(model, vector);
    	
    	//结果还原
    	MaxOut = DSN.getMaxOut(0);
		MinOut = DSN.getMinOut(0);
		predictValue = predictValue * (MaxOut - MinOut); 
		
        return predictValue; 
    }	
	
	
}
