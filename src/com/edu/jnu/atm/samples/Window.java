package com.edu.jnu.atm.samples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import com.edu.jnu.atm.core.DPPredition;
import com.edu.jnu.atm.strategy.BP;
import com.edu.jnu.atm.strategy.BPStrategy;


public class Window
{	
	protected int NumberOfPredictingDate = 0,
			      SvmTrainingDate = 5,
	              SvmInputDate = 7;
	protected int TYPE = 1;
	         double err = 0.3;
	
	protected void forcast(String DEV_CODE, String TRNS_DATE)
	{ 
		double[] PredictingResult = new double[2];
		List<Double> sourceList = new ArrayList<Double>(); 
		List<Double> predictList = new ArrayList<Double>(); 
		Calendar TRNSDATE = toCalendar(TRNS_DATE);
   		DPPredition dp = new DPPredition(); 
   		BPStrategy bp =new BPStrategy();
		BP model =bp.bptrain(DEV_CODE, TYPE);
        for (int i = 0; i < NumberOfPredictingDate; i++) 
        {
        	PredictingResult = dp.predict (model, DEV_CODE, TRNSDATE, TYPE, SvmTrainingDate, SvmInputDate);
            sourceList.add(PredictingResult[0]);
            predictList.add(PredictingResult[1]);
            TRNSDATE.add(Calendar.DATE,1);	
        }
        showResult(sourceList, predictList, err);	
	 }
            
	private Calendar toCalendar (String date)
	{
		/**
		 * translate String date into a Calendar object 
		 */
		int year = Integer.parseInt(date.substring(0, 4).trim());
		int month = Integer.parseInt(date.substring(4, 6).trim());
		int day = Integer.parseInt(date.substring(6, 8).trim());		
		Calendar DATE = Calendar.getInstance();
		DATE.set(year, month-1, day);
		return DATE;		
	}
	
	private void showResult(List<Double> sourceList, List<Double> predictList, double ER)
	{
		/**
		 * construct a new window to show the variation of sourceList and predictList
		 * ER denotes error ratio
		 */
		JFrame frame = new JFrame();
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height; 
	    frame.setBounds((width - 800) / 2, (height - 600) / 2, 800, 500);
		JPanel panel = new JPanel();
		JTextField text = new JTextField();	
		int n = 0;
		
		for (int i = 0; i < sourceList.size(); i ++)           
		{
			if ( (predictList.get(i) + ER * sourceList.get(i) - sourceList.get(i)) > 0 )
		//	if ( (predictList.get(i) + ER * Math.abs(predictList.get(i)) - sourceList.get(i)) > 0 ) 
			{
				n ++;
			}
		}
		double rate = n / (double)sourceList.size();	
		String r = String.valueOf(rate);
	    String err = String.valueOf(ER);
	    text.setEditable(false);
	    text.setFont(new Font("宋体", Font.BOLD, 20));
		text.setText(  "预测准确度为：" + r );	
		panel.add(text);
		ChartPanel tsc = LineChart(sourceList, predictList);
		frame.add(tsc, BorderLayout.CENTER );
		frame.add(panel, BorderLayout.SOUTH);   
		frame.setVisible(true);
	}
	
	private ChartPanel LineChart(List<Double> sourceData, List<Double> predictData)
	{
		/**
		 * draw the line chart 
		 */
    	ChartPanel frame1;
        DefaultCategoryDataset xydataset = createDataset(sourceData, predictData);
        JFreeChart jfreechart = ChartFactory.createLineChart("Forcasting Results", "Date", "Cash Flow", xydataset, PlotOrientation.VERTICAL, true, false, false);
        CategoryPlot plot = jfreechart.getCategoryPlot();
        plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        plot.setBackgroundPaint(Color.white);        
        frame1 = new ChartPanel(jfreechart,true);
        CategoryAxis domainAxis = (CategoryAxis)plot.getDomainAxis();
        domainAxis.setCategoryMargin(11);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseItemLabelsVisible(true);
        renderer.setSeriesPaint(0, Color.black);   
        renderer.setBaseShapesFilled(true);
        renderer.setBaseItemLabelsVisible(true);  
		return frame1;
    }
    
     private DefaultCategoryDataset createDataset(List<Double> sourceData, List<Double> predictData)
     { 
            DefaultCategoryDataset lineseries = new DefaultCategoryDataset();
            String title1 = "Actual Demand";
            String title2 = "Forcasted Demand";;
            for (int i = 0; i < sourceData.size(); i ++)
            {
            	    lineseries.addValue(sourceData.get(i),title1, String.valueOf(i));
            	    lineseries.addValue(predictData.get(i),title2, String.valueOf(i));
            }
            return lineseries;
     }   
}
