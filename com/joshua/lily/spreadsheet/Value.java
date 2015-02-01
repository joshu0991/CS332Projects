package com.joshua.lily.spreadsheet;

import java.text.DecimalFormat;

public class Value {
	private String sVal = "";
	private double dVal = 0;
	private String tag = "dbl";//can be str dbl or inv
	
	public String getsVal() {
		return sVal;
	}

	public void setsVal(String sVal) {
		this.sVal = sVal;
	}

	public double getdVal() {
		return dVal;
	}

	public void setdVal(double dVal) {
		this.dVal = dVal;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	//return a formatted string of double null if invalad
	public String toString(){
		String s = null;
		if(tag == "str"){
		s = String.format("%1$-" + 10 + "s", s);//create a string length 10
		}else if(tag == "dbl"){
			DecimalFormat df = new  DecimalFormat ("0.####");//make a new decimal format
			s = df.format(dVal);//format the double store in a string
			s = String.format("%1$-" + 10 + "s", s);
		}else if(tag == "inv"){
			s = null;
		}
		return s;
	}
	
	//add dvals and return a new value obj
	public Value add(Value n){
		Value r = new Value();
		if(this.tag.equals("dbl") && n.tag.equals("dbl")) {
			r.dVal = this.dVal + n.dVal;
			r.tag = "dbl";
			}
		else
		{
			r.tag = "inv";
		}
		return r;
	}
	
	//subtract dvals and return a new value obj
	public Value subtract(Value n){
		Value r = new Value();
		if(this.tag.equals("dbl") && n.tag.equals("dbl")) {
			r.dVal = this.dVal - n.dVal;
			r.tag = "dbl";
			}
		else
		{
			r.tag = "inv";
		}
		return r;
	}
	
	//muultiply this value by an input
	public Value multiply(Value n){
		Value r = new Value();
		if(this.tag.equals("dbl") && n.tag.equals("dbl")) {
			r.dVal = this.dVal * n.dVal;
			r.tag = "dbl";
			}
		else
		{
			r.tag = "inv";
		}
		return r;
	}
	
	//divide this value by an input
	public Value divide(Value n){
		Value r = new Value();
		if(this.tag.equals("dbl") && n.tag.equals("dbl")) {
			r.dVal = this.dVal / n.dVal;
			r.tag = "dbl";
			}
		else
		{
			r.tag = "inv";
		}
		return r;
	}
	
}
