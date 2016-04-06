package com.edu.jnu.atm.core;

import java.util.*;
import java.io.*;

public class test{

	public static void main(String[] args){
	
		String input = "81*(3-21)+3";
		ArrayList<String> result = new ArrayList<String>();
		
		String temp = "";
		for(int i=0;i<input.length();i++){
			if(input.charAt(i)>='0' && input.charAt(i)<='9')
					temp += input.charAt(i)+"";
			else{
					if(!temp.isEmpty()){
						result.add(temp);
						temp = "";
					}
					result.add(input.charAt(i)+"");
			}		
		}
		Object[] res = result.toArray();
		for(Object t : res)
			System.out.print(t+"\t");
	}

}
