package com.edu.jnu.atm.core;

import java.sql.SQLException;
import java.util.Calendar;

import com.edu.jnu.atm.io.DBConnectionPool;
import com.edu.jnu.atm.util.ResultDataPool;

/**
 * 多个日期的预测
 * 
 * @author Teacher Lee
 *
 */
public class DateSeriesForecast {
	ResultDataPool rdp = new ResultDataPool();
	int HISTORY_DAYS = 60; // 历史数据个数

	public ResultDataPool seriesForest(String DEV_CODE, Calendar TRNS_DATE, int Days) {

		// 建立数据库连接池
		DBConnectionPool connPool = new DBConnectionPool("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/ATM?autoReconnect=true&useSSL=false", "root", "administrator");
		try {
			connPool.createPool();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 逐个日期预测
		SingleDateForecast SDF = new SingleDateForecast();

		// 初始化过程，预测的第一天
		double[] result = SDF.forecast(DEV_CODE, TRNS_DATE, HISTORY_DAYS, connPool, 1);
		rdp.sourceList.add(result[0]);
		rdp.predictList.add(result[1]);
		TRNS_DATE.add(Calendar.DATE, 1);

		for (int i = 1; i < Days; i++) {
			result = SDF.forecast(DEV_CODE, TRNS_DATE, HISTORY_DAYS, connPool, 1);
			double[] result0 = SDF.forecast(DEV_CODE, TRNS_DATE, HISTORY_DAYS, connPool, 4);
			double[] result1 = SDF.forecast(DEV_CODE, TRNS_DATE, HISTORY_DAYS, connPool, 2);

			// 预测值增强学习调整过程
			if ((result[1] < result0[1]))
				result = result0;

			result[1] += 0.5 * result1[1];
			rdp.sourceList.add(result[0]);
			rdp.predictList.add(result[1]);
			TRNS_DATE.add(Calendar.DATE, 1);
		}

		// 释放数据库连接
		try {
			connPool.closeConnectionPool();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rdp;

	}
}
