package com.joshua.lilly.spreadsheet;
import java.util.Scanner;
public class driver {
	
	//instantiated objects
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

	//get the input
	public static boolean parseInput(String input){
		Scanner s = new Scanner(System.in);
		boolean t = false;
		boolean rVal = false;
		String row1 = null, col1 = null, row2 = null, col2 = null, desRow = null, desCol = null;
		switch(input){
		case("dis")://display the grid
			g.display();
			rVal = false;
			break;
		
		//fill a reigon
		case("f"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();	
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter population column");
				String p = s.next();
				g.fill(row1, col1, row2, col2, p);
			} catch (NumberFormatException e){
				System.out.println("Not a valad input please enter integers");
			}
			rVal = false;
			break;
		
		//add two cells
		case("a"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();	
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter destination row");
				desRow = s.next();
				System.out.println("Enter destination column");
				desCol = s.next();
				t = g.addCells(row1, col1, row2, col2, desRow, desCol);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully added cells");
			} else {
				System.out.println("Some cells not operated on");
			}
			rVal = false;
			break;
			
		//multiply cells
		case("m"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();	
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter destination row");
				desRow = s.next();
				System.out.println("Enter destination column");
				desCol = s.next();
				t = g.multiplyCells(row1, col1, row2, col2, desRow, desCol);
			} catch (NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully multiplied cells");
			} else {
				System.out.println("Some cells not operated on");
			}
			rVal = false;
			break;
		
		//add two rows and store in a row
		case("ar"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter target row");
				desRow = s.next();
				t = g.addRows(row1, row2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
		
		//multiple two rows and store in a row
		case("mr"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter target row");
				desRow = s.next();
				t = g.multiplyRows(row1, row2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
		
		//add columns
		case("ac"):
			try{
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter target column");
				desRow = s.next();
				t = g.addColumns(col1, col2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//multiply columns
		case("mc"):
			try{
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter target column");
				desRow = s.next();
				t = g.multiplyColumns(col1, col2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//insert a row
		case("ir"):
			try{
				System.out.println("Enter new row number");
				row1 = s.next();
				t = g.insertRow(row1);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully inserted row");
			}else{
				System.out.println("Failed to insert row");
			}
			rVal = false;
			break;
			
		//delete a row
		case("delr"):
			try{
				System.out.println("Enter row for deletion");
				row1 = s.next();
				t = g.deleteRow(row1);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == false){
				System.out.println("Can not delete last row. The sheet must have one row");
			} else {
				System.out.println("Successfully deleted row");
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
			try{
				System.out.println("Enter first row");
				row1 = s.next();	
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter new value");
				String val = s.next();
				g.assignCell(row1, col1, val);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			rVal = false;
			break;
		
		//number all rows
		case("n"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();	
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				t = g.number(row1, col1, row2, col2);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
		if(t == true){
			System.out.println("Successfully populated sub grid");
		}else{
			System.out.println("Failed to populate sub grid");
		}
			rVal = false;
			break;
		
		//subtract two cells
		case("s"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();	
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter destination row");
				desRow = s.next();
				System.out.println("Enter destination column");
				desCol = s.next();
				t = g.subCells(row1, col1, row2, col2, desRow, desCol);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully subtracted cells");
			} else {
				System.out.println("Could not complete operation");
			}
			rVal = false;
			break;
		
		//divide two cells
		case("d"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();	
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter destination row");
				desRow = s.next();
				System.out.println("Enter destination column");
				desCol = s.next();
				t = g.divideCells(row1, col1, row2, col2, desRow, desCol);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully divided cells");
			} else {
				System.out.println("Could not complete operation");
			}
			rVal = false;
			break;
			
		//subtract two rows
		case("sr"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter target row");
				desRow = s.next();
				t = g.subtractRows(row1, row2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//divide rows
		case("dr"):
			try{
				System.out.println("Enter first row");
				row1 = s.next();
				System.out.println("Enter second row");
				row2 = s.next();
				System.out.println("Enter target row");
				desRow = s.next();
				t = g.divideRows(row1, row2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed row operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//subtract a column
		case("sc"):
			try{
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter target column");
				desRow = s.next();
				t = g.subtractColumns(col1, col2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
			
		//divide a column
		case("dc"):
			try{
				System.out.println("Enter first column");
				col1 = s.next();
				System.out.println("Enter second column");
				col2 = s.next();
				System.out.println("Enter target column");
				desRow = s.next();
				t = g.divideColumns(col1, col2, desRow);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully completed column operation");
			}else{
				System.out.println("Some values may not have been changed due to invalid tag");
			}
			rVal = false;
			break;
		
		//insert a column	
		case("ic"):
			try{
				System.out.println("Enter the column to be inserted");
				col1 = s.next();
				t = g.insertColumn(col1);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Successfully inserted a column");
			} else {
				System.out.println("Failed to insert column");
			}
			rVal = false;
			break;
			
		//delete columns
		case("delc"):
			try{
				System.out.println("Enter the column before the column to delete");
				col1 = s.next();
				t = g.deleteColumns(col1);
			} catch(NumberFormatException e) {
				System.out.println("Not a valad input please enter integers");
			}
			if(t == true){
				System.out.println("Deleted column " + col1);
			} else {
				System.out.println("Failed to delete column");
			}
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
