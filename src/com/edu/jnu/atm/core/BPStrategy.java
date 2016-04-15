package com.edu.jnu.atm.core;

import java.util.ArrayList;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.data.norm.MaxMinNormalizer;
import org.neuroph.util.random.WeightsRandomizer;

import com.edu.jnu.atm.util.DateProfileUtil;

/**
 * 神经网络策略实现预测
 * @author Teacher Lee
 *
 */
public class BPStrategy extends Strategy {

	@Override
	public double Algorithm (ArrayList<DateProfileUtil> sourcedata) {	
		int inputDate = 6; //神经网络输入层个数
		int trainingDate = 40; //训练天数
		double[] predictresult; //预测结果
		
		//BP神经网络的构建与初始化,各层的神经元个数分别为：inputDate，3，1	
		int[] neuronsInLayers = {inputDate,3,2,1};
		NeuralNetwork<BackPropagation> neuralNetwork = new MultiLayerPerceptron(neuronsInLayers);
		WeightsRandomizer WR = new WeightsRandomizer();
		WR.randomize(neuralNetwork);
		
		//添加训练集数据
		DataSet trainingSet = new DataSet(inputDate, 1);
		for (int i = 0; i < trainingDate; i++) {
			double[] data = new double[inputDate]; //训练输入数组
			double[] label = new double[1]; //训练输出数组
        	for (int j = 0; j < inputDate; j++) {
        		double value = sourcedata.get(sourcedata.size()-(inputDate + 1 + i) + j).value;
        		data[j] = value;
        	}
        	label[0] = sourcedata.get(sourcedata.size()-(inputDate + 1 + i) + inputDate).value;
    		trainingSet.addRow(data, label);
        }
		
		//归一化训练集
		MaxMinNormalizer DSN = new MaxMinNormalizer();
		DSN.normalize(trainingSet);
		
		//神经网络参数设置
		BackPropagation learningRule = new BackPropagation();
		learningRule.setMaxIterations(10000);
		learningRule.setLearningRate(0.05);
		neuralNetwork.learn(trainingSet, learningRule);
		
		//预测过程
		double[] input0 = new double[inputDate], input1 = new double[inputDate];
		for (int i = 0; i < inputDate; i++) {
			input0[i] = sourcedata.get(sourcedata.size()-(inputDate-i)).value;
		}
		
		//输入归一化过程
		double MaxIn, MinIn, MaxOut, MinOut;
		for (int i = 0; i < inputDate; i++) {
			MaxIn = DSN.getMaxIn(i);
			MinIn = DSN.getMinIn(i);
			input1[i] = (input0[i] - MinIn) / (MaxIn - MinIn);
		}
		
		neuralNetwork.setInput(input1);
		neuralNetwork.calculate();
		predictresult = neuralNetwork.getOutput();
		
		//输出还原
		MaxOut = DSN.getMaxOut(0);
		MinOut = DSN.getMinOut(0);
		predictresult[0] = predictresult[0] * (MaxOut - MinOut); 
		
		//返回结果
		return predictresult[0];
		
	}
	
	
}
