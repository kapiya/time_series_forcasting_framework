package com.edu.jnu.atm.core;
import java.util.Collections;
import java.util.Stack;

public class Calculator {
	
    
    public static void main(String[] args) {
        System.out.println("1+5*(-3)+(1+5)");
        Calculator cal  = new Calculator();
        String s = "(1+5)*3+(1+5)";
        double result  = cal.calculate(s);
        System.out.println(result);
    }

    
    public double calculate(String expression) {
    	
    	Stack<String> postfixStack  = new Stack<String>();
        Stack<Character> opStack  = new Stack<Character>();
        int [] operatPriority  = new int[] {0,3,2,1,-1,1,0,2};
    	
        Stack<String> resultStack  = new Stack<String>();
        prepare(expression,postfixStack,opStack,operatPriority);
        Collections.reverse(postfixStack);
        String firstValue  ,secondValue,currentValue;
        while(!postfixStack.isEmpty()) {
            currentValue  = postfixStack.pop();
            if(currentValue.length() > 1 || !isOperator(currentValue.charAt(0))) {
                resultStack.push(currentValue);
            } else {
                 secondValue  = resultStack.pop();
                 firstValue  = resultStack.pop();
                 String tempResult  = calculate(firstValue, secondValue, currentValue.charAt(0));
                 resultStack.push(tempResult);
            }
        }
        return Double.valueOf(resultStack.pop());
    }
    
    private void prepare(String expression, Stack<String> postfixStack, Stack<Character> opStack, int[] operatPriority) {
        opStack.push(',');
        char[] arr  = expression.toCharArray();
        int currentIndex  = 0;
        int count = 0;
        char currentOp  ,peekOp;
 
        for(int i=0;i<arr.length;i++) {
            currentOp = arr[i];
            if(isOperator(currentOp)) {
                if(count > 0) {
                    postfixStack.push(new String(arr,currentIndex,count));
                }
                peekOp = opStack.peek();
                if(currentOp == ')') {
                    while(opStack.peek() != '(') {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                } else if (currentOp == '-' && peekOp == '(') {
               	 int positiveIndex = i;
               	 char positiveOp = currentOp;
               	 while (positiveOp != ')') {
               		 positiveOp = arr[++positiveIndex];
               	 }
               		 
               	 postfixStack.push(new String(arr,currentIndex,positiveIndex-i));
               	 i += positiveIndex-i-1;

                } else {
                    while(currentOp != '(' && peekOp != ',' && compare(currentOp,peekOp,operatPriority) ) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                        peekOp = opStack.peek();
                    }
                    opStack.push(currentOp);
                }
                count = 0;
                currentIndex = i+1;
            } else {
                count++;
            }
        }
        if(count > 1 || (count == 1 && !isOperator(arr[currentIndex]))) {//最后一个字符不是括号或者其他运算符的则加入后缀式栈中
            postfixStack.push(new String(arr,currentIndex,count));
        } 
        
        while(opStack.peek() != ',') {
            postfixStack.push(String.valueOf( opStack.pop()));//将操作符栈中的剩余的元素添加到后缀式栈中
        }
    }
    
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' ||c == ')';
    }
    
    public  boolean compare(char cur,char peek,int[] operatPriority) {// 如果是peek优先级高于cur，返回true，默认都是peek优先级要低
        boolean result  = false;
        if(operatPriority[(peek)-40] >= operatPriority[(cur) - 40]) {
           result = true;
        }
        return result;
    }
    
    private String calculate(String firstValue,String secondValue,char currentOp) {
        String result  = "";
        switch(currentOp) {
            case '+':
                result = String.valueOf(ArithHelper.add(firstValue, secondValue));
                break;
            case '-':
                result = String.valueOf(ArithHelper.sub(firstValue, secondValue));
                break;
            case '*':
                result = String.valueOf(ArithHelper.mul(firstValue, secondValue));
                break;
            case '/':
                result = String.valueOf(ArithHelper.div(firstValue, secondValue));
                break;
        }
        return result;
    }
}

class ArithHelper {

   private static final int DEF_DIV_SCALE = 16;

   private ArithHelper() {
   }

   public static double add(double v1, double v2) {
       java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
       java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
       return b1.add(b2).doubleValue();
   }
   
   public static double add(String v1, String v2) {
       java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
       java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
       return b1.add(b2).doubleValue();
   }

   public static double sub(double v1, double v2) {
       java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
       java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
       return b1.subtract(b2).doubleValue();
   }
   
   public static double sub(String v1, String v2) {
       java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
       java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
       return b1.subtract(b2).doubleValue();
   }

   public static double mul(double v1, double v2) {
       java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
       java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
       return b1.multiply(b2).doubleValue();
   }
   
   public static double mul(String v1, String v2) {
       java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
       java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
       return b1.multiply(b2).doubleValue();
   }

   public static double div(double v1, double v2) {
       return div(v1, v2, DEF_DIV_SCALE);
   }
   
   public static double div(String v1, String v2) {
       java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
       java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
       return b1.divide(b2, DEF_DIV_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
   }

   public static double div(double v1, double v2, int scale) {
       if (scale < 0) {
           throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
       }
       java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
       java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
       return b1.divide(b2, scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
   }

   public static double round(double v, int scale) {
       if (scale < 0) {
           throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
       }
       java.math.BigDecimal b = new java.math.BigDecimal(Double.toString(v));
       java.math.BigDecimal one = new java.math.BigDecimal("1");
       return b.divide(one, scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
   }
   
   public static double round(String v, int scale) {
       if (scale < 0) {
           throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
       }
       java.math.BigDecimal b = new java.math.BigDecimal(v);
       java.math.BigDecimal one = new java.math.BigDecimal("1");
       return b.divide(one, scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
   }
}