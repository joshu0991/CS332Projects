package com.joshua.lily.spreadsheet;

public class Grid {

	private int rows = 9, columns = 5; // track number of rows and columns

	// node class for handling pointers to other nodes and values
	private class Node {
		Node bottom = null;
		Node right = null;
		Value val = new Value();
	}

	private Node head;

	public Grid() {
		head = new Node();
		setUpEmptyGrid();
	}

	// set up an empty 9x5 grid
	private void setUpEmptyGrid() {
		Node temp = new Node();// create a temp node init with with new node to
								// start off
		head.bottom = temp;// set entrance point
		double t_counter = 1;
		temp.val.setdVal(t_counter);// test value
		Node top = temp;// set the top value to be the one just created
		Node prevTop = null;// should init to null since the beginning has no
							// previous
		for (int i = 0; i < columns; i++) {
			if (i == 0) {
				// first iteration do nothing special case
			} else {
				prevTop = top; // no longer the first column track previous
								// column
				temp = new Node();
				t_counter += 1;// test value
				temp.val.setdVal(t_counter);
				top = temp;
				prevTop.right = temp; // link to the right
			}
			for (int j = 0; j < rows - 1; j++) {
				Node newNode = new Node();
				t_counter += 1;
				newNode.val.setdVal(t_counter);
				temp.bottom = newNode; // link new node to bottom
				temp = newNode;

				if (j == 7) { // last time though the loop and last node so link
								// it to the begining
					newNode.bottom = top;
				}

				if (prevTop != null) {// if it is null this is the first column
										// else need to link to the right
					prevTop = prevTop.bottom;
					prevTop.right = newNode;
				}
				if (i == 4) {// link the last column back to the beginning
					Node tmp = head.bottom;
					top.right = head.bottom;
					for (int k = 0; k <= j; k++) {// find the node by traversing
													// downward in the first
													// column
						tmp = tmp.bottom;
					}
					temp.right = tmp;// set the link to the beginning
				}
			}
		}

	}//end setup function

	//traverse the grid and display the values in a grid format
	public void display(){
		//print the column headers
	Node currentRow, currentNode;//pointer to current row and current node
	String s = "";
	for(int i = 0; i < columns + 1; i++){//go to columns plus one
		if(i == 0)//if the first go print out a blank spot
		{
			s = String.format("%1$-" + 10 + "s", s);
			System.out.print(s);
		} else 
		{
		s = "Column" + (i - 1);//print counter minus one since first place is blank
		s = String.format("%1$-" + 10 + "s", s);
		System.out.print(s);
		}
	}
	System.out.println();
	
	currentNode = head;//set the node
	currentRow = head.bottom;
	for(int j = 0; j < rows; j++){//print rows then columns
		currentNode = currentRow;
		s = "row " + j;
		s = String.format("%1$-" + 10 + "s", s);
		System.out.print(s);
		for(int k = 0; k < columns; k++){
			System.out.print(currentNode.val.toString());
			currentNode = currentNode.right;
		}
		System.out.println();
		currentRow = currentRow.bottom;
	}
	
	}
	
	public boolean addCells(String r1, String c1, String r2, String c2, String dRow, String dCol){
		int row1, row2, col1, col2, destRow, destCol;
		Node dest, n1, n2;
		row1 = Integer.parseInt(r1);
		row2 = Integer.parseInt(r2);
		col1 = Integer.parseInt(c1);
		col2 = Integer.parseInt(c2);
		destRow = Integer.parseInt(dRow);
		destCol = Integer.parseInt(dCol);
		
		//get the first cell reference
		n1 = getCell(row1, col1);
		System.out.println("Value " + n1.val.getdVal());
		
		//get second cell reference
		n2 = getCell(row2, col2);
		System.out.println("Value " + n2.val.getdVal());
		
		//check both tags
		dest = getCell(destRow, destCol);
		if(n1.val.getTag() == "dbl" && n2.val.getTag() =="dbl"){
			// if both tags are dbl set up destination and store sum of dVal in dest node
			dest.val = n1.val.add(n2.val);
			System.out.println("Dest val " + dest.val.getdVal());
			//return true if op was possible else return false
			return true;
		}else{
			dest.val.setTag("inv");
			return false;
		}
	}
	
	private Node getCell(int row, int col){
		Node n1 = head.bottom;
		for(int k = 0; k < row + 1; k++){//get firsts row
			if(k == 0){
				//do nothing
			}else{
			n1 = n1.bottom;
			}
		}
		for(int j = 0; j < col; j++){//get firsts col
			n1 = n1.right;
		}
		return  n1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// ~~~~~~~~~~~~~TEST~~~~~~~~~~~CODE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void displayGrid() {
		Node t = head.bottom;

		for (int a = 0; a < rows + 1; a++) {
			System.out.println("First column " + t.val.getdVal());
			t = t.bottom;

		}
		System.out.println("---------------------------------------");
		t = head.bottom.right;
		for (int b = 0; b < rows + 1; b++) {
			System.out.println("Second col " + t.val.getdVal());
			t = t.bottom;
		}
		System.out.println("---------------------------------------");

		t = head.bottom.right.right;
		for (int c = 0; c < rows + 1; c++) {
			System.out.println("third col " + t.val.getdVal());
			t = t.bottom;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.right.right.right;
		for (int d = 0; d < rows + 1; d++) {
			System.out.println("forth col " + t.val.getdVal());
			t = t.bottom;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.right.right.right.right;
		for (int e = 0; e < rows + 1; e++) {
			System.out.println("fifth col " + t.val.getdVal());
			t = t.bottom;
		}
		// check a random node
		t = head.bottom.right.bottom.bottom.right;
		System.out.println("Random " + t.val.getdVal());

		System.out.println("---------------------------------------");
		t = head.bottom;
		for (int e = 0; e < columns; e++) {
			System.out.println("first row " + t.val.getdVal());
			t = t.right;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.bottom;
		for (int e = 0; e < columns + 1; e++) {
			System.out.println("second row " + t.val.getdVal());
			t = t.right;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.bottom.bottom;
		for (int e = 0; e < columns + 1; e++) {
			System.out.println("third row " + t.val.getdVal());
			t = t.right;
		}
		System.out.println("---------------------------------------");
		t = head.bottom.bottom.bottom.bottom;
		for (int e = 0; e < columns + 1; e++) {
			System.out.println("forth row " + t.val.getdVal());
			t = t.right;
		}
		t.val.setdVal(10.342);
		System.out.println("From tostring " + t.val.toString());
		System.out.println("---------------------------------------");
		t = head.bottom.right.right.right.right.bottom;
		System.out.println("test row " + t.val.getdVal());
		
		t = head.bottom.right.right.right.right;
				System.out.println("T val " + t.val.getdVal());
				System.out.println("Node val " + t.right);
		
	}

}
