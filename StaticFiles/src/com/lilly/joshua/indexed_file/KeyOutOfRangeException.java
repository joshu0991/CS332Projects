package com.lilly.joshua.indexed_file;

/**
 * Custom error to throw when something is wrong.
 * @author Joshua Lilly
 *
 */
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
