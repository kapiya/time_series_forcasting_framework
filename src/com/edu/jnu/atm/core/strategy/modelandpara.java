package com.edu.jnu.atm.core.strategy;

import java.util.Vector;

public class modelandpara  
{  
    public int[] model;  
    public Vector<double[]> para;  
    public modelandpara(int[] model,Vector<double[]> para)  
    {  
        this.model=model;  
        this.para=para;  
    }  
}  