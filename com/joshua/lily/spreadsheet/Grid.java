package com.joshua.lily.spreadsheet;

public class Grid {

	private int rows = 10, columns = 6; // track number of rows and columns

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
		//double t_counter = 1;
		//temp.val.setdVal(t_counter);// test value
		//temp.val.setTag("dbl");
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
				//t_counter += 1;// test value
				//temp.val.setdVal(t_counter);
				//temp.val.setTag("dbl");
				top = temp;
				prevTop.right = temp; // link to the right
			}
			for (int j = 0; j < rows - 1; j++) {
				Node newNode = new Node();
				//t_counter += 1;
				//newNode.val.setdVal(t_counter);
				//newNode.val.setTag("dbl");
				temp.bottom = newNode; // link new node to bottom
				temp = newNode;

				if (j == 8) { // last time though the loop and last node so link
					newNode.bottom = top;// it to the begining
				}

				if (prevTop != null) {// if it is null this is the first column
										// else need to link to the right
					prevTop = prevTop.bottom;
					prevTop.right = newNode;
				}
				if (i == 5) {// link the last column back to the beginning
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
	if(head.bottom == null){ //means the list is empty
		System.out.println("Nothing to display");
		return;
	}
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
	
	//add two cells
	public boolean addCells(String r1, String c1, String r2, String c2, String dRow, String dCol){
		int row1, row2, col1, col2, destRow, destCol;
		Node dest, n1, n2;
		row1 = Integer.parseInt(r1);
		row2 = Integer.parseInt(r2);
		col1 = Integer.parseInt(c1);
		col2 = Integer.parseInt(c2);
		destRow = Integer.parseInt(dRow);
		destCol = Integer.parseInt(dCol);
		Value t = new Value();
		
		//get the first cell reference
		n1 = getCell(row1, col1);
		
		//get second cell reference
		n2 = getCell(row2, col2);
		
		dest = getCell(destRow, destCol);
		t = n1.val.add(n2.val);
		if(!t.getTag().equals("inv")){
			dest.val = t;
			return true;
		}else{
			return false;
		}
	}
	
	//sub two cells
	public boolean subCells(String r1, String c1, String r2, String c2, String dRow, String dCol){
		int row1, row2, col1, col2, destRow, destCol;
		Node dest, n1, n2;
		row1 = Integer.parseInt(r1);
		row2 = Integer.parseInt(r2);
		col1 = Integer.parseInt(c1);
		col2 = Integer.parseInt(c2);
		destRow = Integer.parseInt(dRow);
		destCol = Integer.parseInt(dCol);
		Value t = new Value();
		
		//get the first cell reference
		n1 = getCell(row1, col1);
		//System.out.println("Value " + n1.val.getdVal());
		
		//get second cell reference
		n2 = getCell(row2, col2);
		//System.out.println("Value " + n2.val.getdVal());
		
		//check both tags
		dest = getCell(destRow, destCol);
		t = n1.val.add(n2.val);
		t = n1.val.subtract(n2.val);
		if(!t.getTag().equals("inv")){
			dest.val = t;
			return true;
		}else{
			return false;
		}
		
	}
	
	//multiply two cells
	public boolean multiplyCells(String r1, String c1, String r2, String c2, String dRow, String dCol){
		int row1, row2, col1, col2, destRow, destCol;
		Node dest, n1, n2;
		row1 = Integer.parseInt(r1);
		row2 = Integer.parseInt(r2);
		col1 = Integer.parseInt(c1);
		col2 = Integer.parseInt(c2);
		destRow = Integer.parseInt(dRow);
		destCol = Integer.parseInt(dCol);
		Value t = new Value();
		
		//get the first cell reference
		n1 = getCell(row1, col1);
		//System.out.println("Value " + n1.val.getdVal());
		
		//get second cell reference
		n2 = getCell(row2, col2);
		//System.out.println("Value " + n2.val.getdVal());
		
		//check both tags
		dest = getCell(destRow, destCol);
		t = n1.val.multiply(n2.val);
		if(!t.getTag().equals("inv")){
			dest.val = t;
			return true;
		}else{
			return false;
		}
	}
	
	//divide two cells
	public boolean divideCells(String r1, String c1, String r2, String c2, String dRow, String dCol){
		int row1, row2, col1, col2, destRow, destCol;
		Node dest, n1, n2;
		row1 = Integer.parseInt(r1);
		row2 = Integer.parseInt(r2);
		col1 = Integer.parseInt(c1);
		col2 = Integer.parseInt(c2);
		destRow = Integer.parseInt(dRow);
		destCol = Integer.parseInt(dCol);
		Value t = new Value();
		
		//get the first cell reference
		n1 = getCell(row1, col1);
		//System.out.println("Value " + n1.val.getdVal());
		
		//get second cell reference
		n2 = getCell(row2, col2);
		//System.out.println("Value " + n2.val.getdVal());
		
		//check both tags
		dest = getCell(destRow, destCol);
		t = n1.val.divide(n2.val);
		if(!t.getTag().equals("inv")){
			dest.val = t;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean number(String r1, String c1, String r2, String c2){
		int row1, row2, col1, col2;
		Node n1;
		row1 = Integer.parseInt(r1);
		row2 = Integer.parseInt(r2);
		col1 = Integer.parseInt(c1);
		col2 = Integer.parseInt(c2);
		double counter = 0;
		//get the first cell reference
		Node marker = getCell(row1, col1);		

		int[] distance = calculateDistance(row1, col1, row2, col2); 
		
		for(int i = 0; i < distance[0] + 1; i++){//for columns
			n1 = marker;
			for(int j = 0; j < distance[1] + 1; j++){//for rows
				n1.val.setTag("dbl");
				n1.val.setdVal(counter++);
				n1 = n1.right;
			}
			marker = marker.bottom;
		}
		return true;
		
	}
	
	int [] calculateDistance(int row1, int col1, int row2, int col2){
		int[] rVal = new int[2];
		rVal[0] = row2 - row1;
		rVal[1] = col2 - col1;
		return rVal;
	}
	
 	
	boolean addRows(String r1, String r2, String t){
		Node n1, n2, tar;
		int row1 = Integer.parseInt(r1);
		int row2 = Integer.parseInt(r2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(row1, 0);
		
		//get first node in second row
		n2 = getCell(row2, 0);
		
		//get first node in target row
		tar = getCell(target, 0);
		
		for(int i = 0; i < columns; i++){
			temp = n1.val.add(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	//multiply two rows
	boolean multiplyRows(String r1, String r2, String t){
		Node n1, n2, tar;
		int row1 = Integer.parseInt(r1);
		int row2 = Integer.parseInt(r2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(row1, 0);
		
		//get first node in second row
		n2 = getCell(row2, 0);
		
		//get first node in target row
		tar = getCell(target, 0);
		
		for(int i = 0; i <= columns; i++){
			temp = n1.val.multiply(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	//divide two rows
	boolean divideRows(String r1, String r2, String t){
		Node n1, n2, tar;
		int row1 = Integer.parseInt(r1);
		int row2 = Integer.parseInt(r2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(row1, 0);
		
		//get first node in second row
		n2 = getCell(row2, 0);
		
		//get first node in target row
		tar = getCell(target, 0);
		
		for(int i = 0; i <= columns; i++){
			temp = n1.val.divide(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	//subtract two rows
	boolean subtractRows(String r1, String r2, String t){
		Node n1, n2, tar;
		int row1 = Integer.parseInt(r1);
		int row2 = Integer.parseInt(r2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(row1, 0);
		
		//get first node in second row
		n2 = getCell(row2, 0);
		
		//get first node in target row
		tar = getCell(target, 0);
		
		for(int i = 0; i <= columns; i++){
			temp = n1.val.subtract(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	boolean addColumns(String c1, String c2, String t){
		Node n1, n2, tar;
		int col1 = Integer.parseInt(c1);
		int col2 = Integer.parseInt(c2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(0, col1);
		
		//get first node in second row
		n2 = getCell(0, col2);
		
		//get first node in target row
		tar = getCell(0, target);
		
		for(int i = 0; i < rows; i++){
			temp = n1.val.add(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.bottom;
				n1 = n1.bottom;
				n2 = n2.bottom;
			} else {
				n1 = n1.bottom;
				n2 = n2.bottom;
				tar = tar.bottom;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	boolean subtractColumns(String c1, String c2, String t){
		Node n1, n2, tar;
		int col1 = Integer.parseInt(c1);
		int col2 = Integer.parseInt(c2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(0, col1);
		
		//get first node in second row
		n2 = getCell(0, col2);
		
		//get first node in target row
		tar = getCell(0, target);
		
		for(int i = 0; i < rows; i++){
			temp = n1.val.subtract(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.bottom;
				n1 = n1.bottom;
				n2 = n2.bottom;
			} else {
				n1 = n1.bottom;
				n2 = n2.bottom;
				tar = tar.bottom;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	boolean multiplyColumns(String c1, String c2, String t){
		Node n1, n2, tar;
		int col1 = Integer.parseInt(c1);
		int col2 = Integer.parseInt(c2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(0, col1);
		
		//get first node in second row
		n2 = getCell(0, col2);
		
		//get first node in target row
		tar = getCell(0, target);
		
		for(int i = 0; i < rows; i++){
			temp = n1.val.multiply(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.bottom;
				n1 = n1.bottom;
				n2 = n2.bottom;
			} else {
				n1 = n1.bottom;
				n2 = n2.bottom;
				tar = tar.bottom;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	//divide columns
	boolean divideColumns(String c1, String c2, String t){
		Node n1, n2, tar;
		int col1 = Integer.parseInt(c1);
		int col2 = Integer.parseInt(c2);
		int target = Integer.parseInt(t);
		boolean rVal = true;
		Value temp = new Value();
		//get first node in first row
		n1 = getCell(0, col1);
		
		//get first node in second row
		n2 = getCell(0, col2);
		
		//get first node in target row
		tar = getCell(0, target);
		
		for(int i = 0; i < rows; i++){
			temp = n1.val.divide(n2.val);
			if(temp.getTag() == "dbl"){
				tar.val = temp;
				tar = tar.bottom;
				n1 = n1.bottom;
				n2 = n2.bottom;
			} else {
				n1 = n1.bottom;
				n2 = n2.bottom;
				tar = tar.bottom;
				rVal = false;
			}
			
		}
		return rVal;
	}
	
	//assign a cell another cells value
	public void assignCell(String r1, String c1, String val){
		Node n1;
		Value newV;
		if(val.charAt(0) == '\"'){
			newV = new Value(val);
		}else{
			//int num = Integer.parseInt(val);
			newV = new Value(Integer.parseInt(val));
		}
		int row1 = Integer.parseInt(r1);
		int col1 = Integer.parseInt(c1);
		n1 = getCell(row1, col1);
		n1.val = newV;
	}
	
	//fill a sub grid with a value
	public boolean fill(String row1, String col1, String row2, String col2, String val){
		int r1 = Integer.parseInt(row1);
		int r2 = Integer.parseInt(row2);
		int c1 = Integer.parseInt(col1);
		int c2 = Integer.parseInt(col2);
		Value newV;
		Node n1, marker;
		if(val.charAt(0) == '\"'){
			newV = new Value(val);
		}else{
			newV = new Value(Integer.parseInt(val));
		}
		marker = getCell(r1, c1);
		int[] dis =  calculateDistance(r1, c1, r2, c2);
		
		for(int i = 0; i < dis[0] + 1; i++){//for columns
			n1 = marker;
			for(int j = 0; j < dis[1] + 1; j++){//for rows
				n1.val = newV;
				n1 = n1.right;
			}
			marker = marker.bottom;
		}
		return true;
	}
	
	//delete row
	public boolean deleteRow(String row){
		int r = Integer.parseInt(row);
		Node above, below, n;
		if(rows != 1){ //make sure there are rows to remove
		//check to see if deleting first row if not.. isn't the special case
		if(r != 0 && r != rows){
			above = getCell((r-1), 0);//node above it
			below = getCell((r+1), 0);//node below it
		} else{ //were deleting first row
			above = getCell(this.rows - 1, 0); //the last cell in the list
			below = getCell((r+1), 0);
			if(rows != 1){//not the last row in the matrix
				this.head.bottom = getCell((r+1), 0);
			}
		}
		//loop over the columns
		for(int i = 0; i < columns - 1; i++){
			n = above.bottom;
			above.bottom = n.bottom;
			n.bottom = null;
			n.right = null;		
			above = above.right;
			below = below.right;
		}
		rows--;
		return true;
		}else{
			head.bottom = null;
			return false;
		}
	}
	
	public boolean insertRow(String row){
		int r = Integer.parseInt(row);
		Node n, previous, newNode, watcher, left = null;
		if(r != 0 && r != rows){
			previous = getCell((r-1), 0);//node above it
			watcher = previous;
			n = getCell(r, 0);
		} else {
			previous = getCell(this.rows - 1, 0); //the last cell in the list
			watcher = this.head.bottom;//watch the node so head can be reset
			n = getCell(r, 0); 
		}
		
		for(int i = 0; i < columns; i++) {
			newNode = new Node();
			newNode.bottom = n;
			previous.bottom = newNode;
			previous = previous.right;//move the previous row over
			n = n.right;//move node row over
			if(i == 0){
				left = newNode;
			}
			if(i != 0){
				left.right = newNode;
				left = newNode;
			}
			if(i == (columns - 1)){
				newNode.right = watcher;
				rows++;
			}
			if(r == 0 && i == 0){
				this.head.bottom = newNode;
			}
		}
		
		return true;
	}
	
	}
