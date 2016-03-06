package com.edu.jnu.atm.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.edu.jnu.atm.strategy.BP;
import com.edu.jnu.atm.strategy.BPStrategy;
import com.edu.jnu.atm.strategy.SVMStrategy;
import com.edu.jnu.atm.strategy.SvmModel;
import com.edu.jnu.atm.util.DBConnection;

public class DPPredition 
{
	public double[] predict (BP model, String DEV_CODE, Calendar TRNS_DATE, int TYPE, int SvmTrainingDate, int SvmInputDate)
	{
		double AdjcentValue = 0;
		DateProfile dp = new DateProfile();						
		double[] PredictingResult = {0,0};
		DBConnection node = new DBConnection();
		double[] nodes = node.getNode(DEV_CODE, TRNS_DATE);
		PredictingResult[0] = nodes[TYPE];
				 
		SVMStrategy svm = new SVMStrategy();	
		
	//	SvmModel dateModel = svm.svmtrain(DEV_CODE, TRNS_DATE, SvmTrainingDate, SvmInputDate, TYPE);
		//double SvmDateResult = svm.svmpredict(dateModel, DEV_CODE, TRNS_DATE, SvmInputDate, TYPE);       
	//	PredictingResult[1] = SvmDateResult;
		
	    SvmModel weekModel = svm.svmtrain(DEV_CODE, TRNS_DATE, SvmTrainingDate, TYPE);
		double SvmWeekResult = svm.svmpredict(weekModel, DEV_CODE, TRNS_DATE, TYPE);		
    //   PredictingResult[1] = SvmWeekResult;

		BPStrategy bp =new BPStrategy();
		double BpDateResult = bp.bppredict(model, DEV_CODE, TRNS_DATE, TYPE);
	//	PredictingResult[1] =  BpDateResult ;
		
	//	BPPrediction bp =new BPPrediction();
	//	double BpDateResult = bp.bppredict(DEV_CODE, TRNS_DATE, TYPE);
	//	PredictingResult[1] =  BpDateResult ;
		
    	PredictingResult[1] = 0.7 * BpDateResult + 0.3 * SvmWeekResult;
		
		
		
		/*
		dp.constructDateProfile(DEV_CODE, TRNS_DATE, TYPE);
		nodeList = node.getNode(DEV_CODE, TRNS_DATE);
		PredictingResult[0]= nodeList[TYPE];
		double Avg = 0.23 * dp.PreviousDays[0] + 0.17 * dp.PreviousDays[1] + 0.12 * dp.PreviousDays[2] + 0.10 * dp.PreviousDays[3] + 0.08 * dp.PreviousDays[4] + 0.07 * dp.PreviousDays[5] + 0.23 * dp.PreviousDays[6]; 		
		switch (dp.DayOfWeek) {
		case 1 : AdjcentValue = Avg * (1.8425/1.4858); break;
		case 2 : AdjcentValue = Avg * (1.6786/1.4858); break;
		case 3 : AdjcentValue = Avg * (1.4956/1.4858); break;
		case 4 : AdjcentValue = Avg * (1.5073/1.4858); break;
		case 5 : AdjcentValue = Avg * (1.5047/1.4858); break;
		case 6 : AdjcentValue = Avg * (1.1823/1.4858); break;
		case 7 : AdjcentValue = Avg * (1.1892/1.4858); break;
		}		
		PredictingResult[1] = AdjcentValue;		
		*/
	//	System.out.println(PredictingResult[0] +"  " +PredictingResult[1]);
		return PredictingResult;		
	}
}
