package com.joshua.lily.spreadsheet;

public class driver {
	
	
	private static void buildMenu(){
		System.out.println("Select an option: ");
		
	}
	
	
	public static void main(String [] args){
		buildMenu();
		Grid g = new Grid();
		g.displayGrid();
	}

}
