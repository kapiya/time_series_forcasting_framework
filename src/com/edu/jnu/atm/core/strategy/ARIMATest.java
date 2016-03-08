package com.edu.jnu.atm.core.strategy;
import java.io.*;  
import java.util.ArrayList;  
import java.util.Scanner;  
public class ARIMATest {  
	  
    public static void main(String args[])  
    {  
        Scanner ino=null;  
              
        try {  
            /*********************************************************/  
            ArrayList<Double> arraylist=new ArrayList<Double>();  
            ino=new Scanner(new File("E:\\work\\Arima\\Arima\\Data\\ceshidata.txt"));  
              
            while(ino.hasNext())  
            {  
                arraylist.add(Double.parseDouble(ino.next()));  
            }  
              
            double[] dataArray=new double[arraylist.size()];   
              
            for(int i=0;i<dataArray.length;i++)  
                dataArray[i]=arraylist.get(i);  
      
              
            ARIMAiFlex myarima=new ARIMAiFlex(dataArray);  
       //     currentAlgorithm cc=new currentAlgorithm(dataArray);  
              
            /*********************************************************/  
                  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            ino.close();  
        }  
    }  
      
}  