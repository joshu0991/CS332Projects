package com.joshua.lily.spreadsheet;
import java.util.Scanner;
public class driver {
	
	
	private static void buildMenu(){
		Scanner s = new Scanner(System.in);
		System.out.println("Operations: ");
		System.out.println("display				dis			assign cell			as");
		System.out.println("fill				f			number				n");
		System.out.println("add cells			a			subtract cells			s");
		System.out.println("multiply cells			m			divide cells			d");
		System.out.println("add rows			ar			subtract rows			sr");
		System.out.println("multiply rows			mr			divide rows			dr");
		System.out.println("add columns			ac			subtract columns		sc");
		System.out.println("multiply columns		mc			divide columns			dc");
		System.out.println("insert row			ir			insert column			ic");
		System.out.println("delete row			delr			delete column			delc");
		System.out.println("quit			q");
		String input = s.next();
		parseImport(input);
		
	}

	public static void parseImport(String input){
		Grid g = new Grid();
		switch(input){
		case("dis")://dispay the grid
			g.display();
			break;
		case("f"):
			
			break;
		case("a"):
			
			break;
		case("m"):
			
			break;
		case("ar"):
		
			break;
		case("mr"):
			
			break;
		case("ac"):
			
			break;
		case("mc"):
			
			break;
		case("ir"):
			
			break;
		case("delr"):
			
			break;
		case("q"):
			
			break;
		case("as"):
			
			break;
		case("n"):
			
			break;
		case("s"):
			
			break;
		case("d"):
			
			break;
		case("sr"):
			
			break;
		case("dr"):
			
			break;
		case("sc"):
			
			break;
		case("dc"):
			
			break;
		case("ic"):
			
			break;
		case("delc"):
			
			break;
		}
	}
	
	
	public static void main(String [] args){
		buildMenu();
		Grid g = new Grid();//for testing
		//g.displayGrid();
		
	}

}
