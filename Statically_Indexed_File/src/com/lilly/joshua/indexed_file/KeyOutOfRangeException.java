package com.lilly.joshua.indexed_file;

public class KeyOutOfRangeException extends Exception{

	/**
	 * Default ID
	 */
	private static final long serialVersionUID = 1L;
	
	//no message
	public KeyOutOfRangeException(){}

	public KeyOutOfRangeException(String message){
		super(message);
	}
}
