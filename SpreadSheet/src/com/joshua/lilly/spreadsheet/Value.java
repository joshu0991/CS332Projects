package com.joshua.lilly.spreadsheet;

import java.text.DecimalFormat;

public class Value {
	private String sVal = "";
	private double dVal;
	private String tag = "inv";//can be str dbl or inv
	
	/**
	 * @constructor Create a new String value.
	 * @param s the new string value.
	 */
	public Value(String s){
		s = s.substring(1);
		sVal = s;
		tag = "str";
	}
	
	/**
	 * @constructor Create a new double value.
	 * @param d The doubles new value
	 */
	public Value(double d){
		dVal = d;
		tag = "dbl";
	}
	
	/**
	 * The default constructor does nothing.
	 */
	public Value(){
		dVal = 0;
		sVal = null;
		tag = "inv";
	}
	
	/**
	 * Return the sVal
	 * @return sVal the strings value
	 */
	public String getsVal() {
		return sVal;
	}

	/**
	 * Set the strings value
	 * @param sVal new value.
	 */
	public void setsVal(String sVal) {
		this.sVal = sVal;
	}

	/**
	 * Return the dVal of the value
	 * @return dVal the double value
	 */
	public double getdVal() {
		return dVal;
	}

	/**
	 * Sets the dVal parameter
	 * @param dVal set the dVal
	 */
	public void setdVal(double dVal) {
		this.dVal = dVal;
	}

	/**
	 * Get the tag of the current value
	 * @return tag parameter
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * set the tag parameter
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * Return a formatted string of the value object
	 */
	public String toString(){
		String s = "";
		if(tag == "str"){
		s = String.format("%1$-" + 10 + "s", sVal);//create a string length 10
		}else if(tag == "dbl"){
			DecimalFormat df = new  DecimalFormat ("0.0000");//make a new decimal format
			s = df.format(dVal);//format the double store in a string
			s = String.format("%1$-" + 10 + "s", s);
		}else if(tag == "inv"){
			s = String.format("%1$-" + 10 + "s", s);
		}
		return s;
	}
	
	/**
	 * Add together two value objects
	 * @param n A value object
	 * @return A new value if criteria is met
	 */
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
	
	/**
	 * Subtract two value objects
	 * @param n A value object
	 * @return A new value
	 */
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
	
	/**
	 * Multiply two values together
	 * @param n A value object
	 * @return A new value object
	 */
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
	
	/**
	 * Divide two value objects
	 * If denominator is zero return invalid value.
	 * @param n A value
	 * @return A new value
	 */
	public Value divide(Value n){
		Value r = new Value();
		if(this.tag.equals("dbl") && n.tag.equals("dbl") && n.dVal != 0) {
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
