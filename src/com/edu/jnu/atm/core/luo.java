package com.edu.jnu.atm.core;
import java.util.ArrayList;
import java.util.Scanner;


public class luo
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		
		String[] testStr = transform(str);

	}
	
	public static String[] transform(String str){
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> tempList = new ArrayList<String>();
		
		for(int i =0; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))){
				tempList.add(str.charAt(i) + "");
			}else {
				
				
				
				String tempStr = "";
				for(int j = 0; j < tempList.size(); j++){
					tempStr = tempStr + tempList.get(j);
				}
				arrayList.add(tempStr);
				tempList.clear();
				arrayList.add(str.charAt(i) + "");	
			}
		}
		
		String[] strArray = new String[arrayList.size()];
		for(int i = 0; i < arrayList.size(); i++) {
			strArray[i] = arrayList.get(i);
		}
		
		System.out.println(arrayList);
		return strArray;
		
		
	}

}
