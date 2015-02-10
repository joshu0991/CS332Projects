package com.joshua.lilly.spreadsheet;
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
			System.out.println("Enter first node row");
			row1 = s.next();	
			System.out.println("Enter first node column");
			col1 = s.next();
			System.out.println("Enter second node row");
			row2 = s.next();
			System.out.println("Enter second node column");
			col2 = s.next();
			System.out.println("Enter population value column");
			String p = s.next();
			g.fill(row1, col1, row2, col2, p);
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
				System.out.println("Some cells not operated on");
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
				System.out.println("Some cells not operated on");
			}
			rVal = false;
			break;
		
		//add two rows and store in a row
		case("ar"):
			System.out.println("Enter first row");
			row1 = s.next();
			System.out.println("Enter second row");
			row2 = s.next();
			System.out.println("Enter target row");
			desRow = s.next();
			t = g.addRows(row1, row2, desRow);
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
		
		//multiple two rows and store in a row
		case("mr"):
			System.out.println("Enter first row");
			row1 = s.next();
			System.out.println("Enter second row");
			row2 = s.next();
			System.out.println("Enter target row");
			desRow = s.next();
			t = g.multiplyRows(row1, row2, desRow);
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
		
		//add columns
		case("ac"):
			System.out.println("Enter first column");
			col1 = s.next();
			System.out.println("Enter second column");
			col2 = s.next();
			System.out.println("Enter target column");
			desRow = s.next();
			t = g.addColumns(col1, col2, desRow);
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//multiply columns
		case("mc"):
			System.out.println("Enter first column");
			col1 = s.next();
			System.out.println("Enter second column");
			col2 = s.next();
			System.out.println("Enter target column");
			desRow = s.next();
			t = g.multiplyColumns(col1, col2, desRow);
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//insert a row
		case("ir"):
			System.out.println("Enter row before new row");
			row1 = s.next();
			g.insertRow(row1);
			rVal = false;
			break;
			
		//delete a row
		case("delr"):
			System.out.println("Enter row for deletion");
			row1 = s.next();
			t = g.deleteRow(row1);
			if(t == false){
				System.out.println("Can not delete from an empty list");
			}
			rVal = false;
			break;
			
		//quit the program
		case("q"):
			s.close();//close the scanner
			rVal = true;
			break;
		
		//assign cells
		case("as"):
			System.out.println("Enter first node row");
			row1 = s.next();	
			System.out.println("Enter first node column");
			col1 = s.next();
			System.out.println("Enter new value");
			String val = s.next();
			g.assignCell(row1, col1, val);
			rVal = false;
			break;
		
		//number all rows
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
			
		//subtract two rows
		case("sr"):
			System.out.println("Enter first row");
			row1 = s.next();
			System.out.println("Enter second row");
			row2 = s.next();
			System.out.println("Enter target row");
			desRow = s.next();
			t = g.subtractRows(row1, row2, desRow);
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//divide rows
		case("dr"):
			System.out.println("Enter first row");
			row1 = s.next();
			System.out.println("Enter second row");
			row2 = s.next();
			System.out.println("Enter target row");
			desRow = s.next();
			t = g.divideRows(row1, row2, desRow);
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		case("sc"):
			System.out.println("Enter first column");
			col1 = s.next();
			System.out.println("Enter second column");
			col2 = s.next();
			System.out.println("Enter target column");
			desRow = s.next();
			t = g.subtractColumns(col1, col2, desRow);
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//divide a column
		case("dc"):
			System.out.println("Enter first column");
			col1 = s.next();
			System.out.println("Enter second column");
			col2 = s.next();
			System.out.println("Enter target column");
			desRow = s.next();
			t = g.divideColumns(col1, col2, desRow);
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		case("ic"):
			rVal = false;
			break;
			
		//delete columns
		case("delc"):
			System.out.println("Enter the column before the column to delete");
			col1 = s.next();
			t = g.deleteColumns(col1);
			rVal = false;
			break;
		}
		return rVal;
	}
	
	
	public static void main(String [] args){
		boolean contin = false;
		while(contin == false){
			contin = buildMenu();
		}
		
	}

}
