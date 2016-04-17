package com.edu.jnu.atm.samples;

import java.util.Calendar;
import com.edu.jnu.atm.core.DateSeriesForecast;
import com.edu.jnu.atm.util.ResultDataPool;
import com.edu.jnu.atm.util.StringToCalendarUtil;

/**
 * 程序主入口，输入日期及预测参数，返回预测结果
 * 
 * @author Teacher Lee
 *
 */
public class Client {

	public static void main(String args[]) {
		String DEV_CODE = ""; // 设备号
		String TRNS_DATE = "";// 预测起始日期(待预测日期的前一天)
		int dates_of_predict = 200;// 预测天数

		// 用户输入参数
		DEV_CODE = "990030270001";
		TRNS_DATE = "20130515";

		// 转化为日期类
		Calendar TRANS_DATE = StringToCalendarUtil.ToCalendar(TRNS_DATE);

		// 预测
		DateSeriesForecast DSF = new DateSeriesForecast();
		ResultDataPool RDP = DSF.seriesForest(DEV_CODE, TRANS_DATE, dates_of_predict);

		// 结果输出
		Window wnd = new Window();
		wnd.show(RDP);

	}

}
