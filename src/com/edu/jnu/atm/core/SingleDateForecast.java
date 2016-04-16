package com.edu.jnu.atm.core;

import java.util.ArrayList;
import java.util.Calendar;
import com.edu.jnu.atm.io.DBConnectionPool;
import com.edu.jnu.atm.io.DBFactory;
import com.edu.jnu.atm.io.MySQLFactory;
import com.edu.jnu.atm.io.SourceData;
import com.edu.jnu.atm.io.SourceDataPool;
import com.edu.jnu.atm.util.DateProfileUtil;

/**
 * 实现单个日期的预测
 * 
 * @author Teacher Lee
 *
 */
public class SingleDateForecast {

	public double[] forecast(String DEV_CODE, Calendar TRNS_DATE, int HISTORY_DAYS, DBConnectionPool connPool) {
		double bpForecastResult = 0, svmForecastResult = 0, arimaForecastResult = 0, forecastResult = 0;

		// 历史数据放入源数据池
		SourceDataPool sdp = new SourceDataPool();
		ArrayList<DateProfileUtil> sourcedata = sdp.getSourceDataPool(DEV_CODE, TRNS_DATE, HISTORY_DAYS, connPool);

		// 神经网络模型
		// ForecastContext forecastContext0;
		// forecastContext0 = new ForecastContext (new BPStrategy ());
		// bpForecastResult = forecastContext0.forecast(sourcedata);

		// 支持向量机模型
		// ForecastContext forecastContext1;
		// forecastContext1 = new ForecastContext (new SVMStrategy ());
		// svmForecastResult = forecastContext1.forecast(sourcedata);

		// ARIMA模型
		ForecastContext forecastContext3;
		forecastContext3 = new ForecastContext(new ArimaStrategy());
		arimaForecastResult = forecastContext3.forecast(sourcedata);

		// forecastResult = 0.8 * bpForecastResult + 0.8 * svmForecastResult;

		// 得到TRNS_DATE的真实值
		SourceData dbcon;
		DBFactory datafactory = new MySQLFactory(); // MySQL数据库
		// DBFactory datafactory = new OracleFactory(); //Oracle数据库
		dbcon = datafactory.getDBConnection();
		double realValue = dbcon.getSourceData(DEV_CODE, TRNS_DATE, connPool);

		// 结果返回
		double[] result = new double[2];
		result[0] = realValue;
		result[1] = arimaForecastResult;
		return result;

	}

}
