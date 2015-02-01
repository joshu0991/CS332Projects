package com.joshua.lily.spreadsheet;
import java.util.Scanner;
public class driver {
	
	private static final Grid g = new Grid();
	
	private static boolean buildMenu(){
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
		System.out.println("quit				q");
		System.out.print("-> ");
		String input = s.next();
		return parseImport(input);
		
	}

	public static boolean parseImport(String input){
		Scanner s = new Scanner(System.in);
		boolean rVal = false;
		switch(input){
		case("dis")://dispay the grid
			g.display();
			rVal = false;
			break;
		case("f"):
			rVal = false;
			break;
		case("a"):
			System.out.println("Enter first node row");
			String row1 = s.next();	
			System.out.println("Enter first node column");
			String col1 = s.next();
			System.out.println("Enter second node row");
			String row2 = s.next();
			System.out.println("Enter second node column");
			String col2 = s.next();
			System.out.println("Enter destination node row");
			String desRow = s.next();
			System.out.println("Enter destination node column");
			String desCol = s.next();
			g.addCells(row1, col1, row2, col2, desRow, desCol);
			rVal = false;
			break;
		case("m"):
			rVal = false;
			break;
		case("ar"):
			rVal = false;
			break;
		case("mr"):
			rVal = false;
			break;
		case("ac"):
			rVal = false;
			break;
		case("mc"):
			rVal = false;
			break;
		case("ir"):
			rVal = false;
			break;
		case("delr"):
			rVal = false;
			break;
		case("q"):
			rVal = true;
			break;
		case("as"):
			rVal = false;
			break;
		case("n"):
			rVal = false;
			break;
		case("s"):
			rVal = false;
			break;
		case("d"):
			rVal = false;
			break;
		case("sr"):
			rVal = false;
			break;
		case("dr"):
			rVal = false;
			break;
		case("sc"):
			rVal = false;
			break;
		case("dc"):
			rVal = false;
			break;
		case("ic"):
			rVal = false;
			break;
		case("delc"):
			rVal = false;
			break;
		}
		return rVal;
	}
	
	
	public static void main(String [] args){
		boolean contin = false;
		while(contin == false){
			//Grid g = new Grid();//for testing
			//g.displayGrid();
			contin = buildMenu();
		}
		
	}

}
