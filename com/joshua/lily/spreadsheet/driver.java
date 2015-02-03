package com.joshua.lily.spreadsheet;
import java.util.Scanner;
public class driver {
	
	private static final Grid g = new Grid();
	private static final Scanner s = new Scanner(System.in);
	private static boolean buildMenu(){
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
		return parseInput(input);
		
	}

	public static boolean parseInput(String input){
		Scanner s = new Scanner(System.in);
		boolean t;
		boolean rVal = false;
		String row1, col1, row2, col2, desRow, desCol;
		switch(input){
		case("dis")://display the grid
			g.display();
			rVal = false;
			break;
			
		case("f"):
			rVal = false;
			break;
		
		//add two cells
		case("a"):
			System.out.println("Enter first node row");
			row1 = s.next();	
			System.out.println("Enter first node column");
			col1 = s.next();
			System.out.println("Enter second node row");
			row2 = s.next();
			System.out.println("Enter second node column");
			col2 = s.next();
			System.out.println("Enter destination node row");
			desRow = s.next();
			System.out.println("Enter destination node column");
			desCol = s.next();
			t = g.addCells(row1, col1, row2, col2, desRow, desCol);
			if(t == true){
				System.out.println("Successfully added " + row1 + ", " + col1 + " by " + row2 + ", " + col2);
			} else {
				System.out.println("ould not complete operation");
			}
			rVal = false;
			break;
			
		//multiply cells
		case("m"):
			System.out.println("Enter first node row");
			row1 = s.next();	
			System.out.println("Enter first node column");
			col1 = s.next();
			System.out.println("Enter second node row");
			row2 = s.next();
			System.out.println("Enter second node column");
			col2 = s.next();
			System.out.println("Enter destination node row");
			desRow = s.next();
			System.out.println("Enter destination node column");
			desCol = s.next();
			t = g.multiplyCells(row1, col1, row2, col2, desRow, desCol);
			if(t == true){
				System.out.println("Successfully multiplied " + row1 + ", " + col1 + " by " + row2 + ", " + col2);
			} else {
				System.out.println("Could not complete operation");
			}
			rVal = false;
			break;
		
		
		case("ar"):
			System.out.println("Enter first row");
			row1 = s.next();
			System.out.println("Enter second row");
			row2 = s.next();
			System.out.println("Enter target row");
			desRow = s.next();
			t = g.addRows(row1, row2, desRow);
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
			s.close();//close the scanner
			rVal = true;
			break;
		case("as"):
			rVal = false;
			break;
		case("n"):
			System.out.println("Enter first node row");
			row1 = s.next();	
			System.out.println("Enter first node column");
			col1 = s.next();
			System.out.println("Enter second node row");
			row2 = s.next();
			System.out.println("Enter second node column");
			col2 = s.next();
			t = g.number(row1, col1, row2, col2);
			rVal = false;
			break;
		
		//subtract two cells
		case("s"):
			System.out.println("Enter first node row");
			row1 = s.next();	
			System.out.println("Enter first node column");
			col1 = s.next();
			System.out.println("Enter second node row");
			row2 = s.next();
			System.out.println("Enter second node column");
			col2 = s.next();
			System.out.println("Enter destination node row");
			desRow = s.next();
			System.out.println("Enter destination node column");
			desCol = s.next();
			t = g.subCells(row1, col1, row2, col2, desRow, desCol);
			if(t == true){
				System.out.println("Successfully subtracted " + row1 + ", " + col1 + " by " + row2 + ", " + col2);
			} else {
				System.out.println("ould not complete operation");
			}
			rVal = false;
			break;
		
		//divide two cells
		case("d"):
			System.out.println("Enter first node row");
			row1 = s.next();	
			System.out.println("Enter first node column");
			col1 = s.next();
			System.out.println("Enter second node row");
			row2 = s.next();
			System.out.println("Enter second node column");
			col2 = s.next();
			System.out.println("Enter destination node row");
			desRow = s.next();
			System.out.println("Enter destination node column");
			desCol = s.next();
			t = g.divideCells(row1, col1, row2, col2, desRow, desCol);
			if(t == true){
				System.out.println("Successfully divided " + row1 + ", " + col1 + " by " + row2 + ", " + col2);
			} else {
				System.out.println("ould not complete operation");
			}
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
