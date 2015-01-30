package com.joshua.lily.spreadsheet;

public class Value {
	String sVal = "";
	double dVal = 0;
	String tag = "str";//can be str dbl or inv
	
	//return a formatted string of double
	public String toString(){
		String s = null;
		if(tag == "str"){
		s = sVal;
		}else if(tag == "dbl"){
			 s = Double.toString(dVal);
		}else if(tag == "inv"){
			s = null;
		}
		return s;
	}
}
