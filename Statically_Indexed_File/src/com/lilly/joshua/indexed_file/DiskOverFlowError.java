package com.lilly.joshua.indexed_file;

public class DiskOverFlowError extends Exception{

	/**
	 * Default ID
	 */
	private static final long serialVersionUID = 1L;
	
	//no message
	public DiskOverFlowError(){}

	public DiskOverFlowError(String message){
		super(message);
	}
}
