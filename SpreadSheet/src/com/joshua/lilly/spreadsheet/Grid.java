/**
 * @author Joshua Lilly
 */
package com.joshua.lilly.spreadsheet;

/**
 * Grid class for controlling all things spreadsheet related
 * @see #head
 * @see #rows
 * @see #rows
 * @see #Grid
 * 
 */
public class Grid {

	private int rows = 10, columns = 6; // track number of rows and columns

	// node class for handling pointers to other nodes and values
	private class Node {
		Node bottom = null;
		Node right = null;
		Value val = new Value();
	}

	//always points to beginning of list
	private Node head;

	//grid constructor
	//set up a new grid
	public Grid() {
		head = new Node();
		setUpEmptyGrid();
	}
	
	/**
	 * sets up an empty list without using an array.
	 * grid size is 9x5
	 * @return void
	 */
	private void setUpEmptyGrid() {
		Node temp = new Node();// create a temp node init with with new node to
								// start off
		head.bottom = temp;// set entrance point
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
				top = temp;
				prevTop.right = temp; // link to the right
			}
			for (int j = 0; j < rows - 1; j++) {
				Node newNode = new Node();
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

	/**
	 * Displays a list and the headers for columns and rows.
	 * Traverses list and prints it in the grid format.
	 * @return void
	 */
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
	
	}//end display
	
	/**
	 * Gets a cell and returns it's pointer.
	 * @param row target row
	 * @param col target column
	 * @return Node
	 */
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
	} //end get cell
	
	/**
	 * Uses the value classes add function to add two cells.
	 * @param r1 first nodes row
	 * @param c1 first nodes column
	 * @param r2 second nodes row
	 * @param c2 second nodes column
	 * @param dRow destination row
	 * @param dCol destination column
	 * @return true on success false on failure.
	 */
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
	}//end add cells
	
	/**
	 * Uses value classes subtract function to subtract two cells.
	 * @param r1 first nodes row
	 * @param c1 first nodes column
	 * @param r2 second nodes row
	 * @param c2 second nodes column
	 * @param dRow destination row
	 * @param dCol destination column
	 * @return True on success false on failure.
	 */
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
		
		//get second cell reference
		n2 = getCell(row2, col2);
		
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
		
	}//end subtract cells
	
	/**
	 * Multiplies two cells using the value classes multiply function.
	 * @param r1 first nodes row
	 * @param c1 first nodes column
	 * @param r2 second nodes row
	 * @param c2 second nodes column
	 * @param dRow destination row
	 * @param dCol destination column
	 * @return true on success false on failure.
	 */
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
	}//end multiply cells
	
	/**
	 * Divide two cells using the value classes divide function.
	 * @param r1 first nodes row
	 * @param c1 first nodes column
	 * @param r2 second nodes row
	 * @param c2 second nodes column
	 * @param dRow destination nodes row
	 * @param dCol destination nodes column
	 * @return True on success false on failure.
	 */
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
	}//end divide cells
	
	/**
	 * Function to populate a sub grid with an input value. Can be a String if prefixed
	 * with " or is assumed double if no prefix.
	 * @param r1 first nodes row
	 * @param c1 first nodes column
	 * @param r2 second nodes row
	 * @param c2 second nodes column
	 * @return True on success and false on failure.
	 */
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
		
	}//end number
	
	/**
	 * Takes in the rows and columns of two cells and returns the distance
	 * one cell is from another.
	 * @param row1 first nodes row
	 * @param col1 first nodes column
	 * @param row2 second nodes row
	 * @param col2 second nodes column
	 * @return Integer array consisting of row and column distance.
	 */
	int [] calculateDistance(int row1, int col1, int row2, int col2){
		int[] rVal = new int[2];
		rVal[0] = Math.abs(row2 - row1);//return absolute value since row2 or col2 can be < row 1 or col2
		rVal[1] = Math.abs(col2 - col1);
		return rVal;
	}//end calculateDistance
	
 	/**
 	 * Takes in two rows and multiplies them using the value classes multiply function stores 
 	 * result in a target row.
 	 * @param r1 first row
 	 * @param r2 second row
 	 * @param t target row
 	 * @return True on success and false on failure.
 	 */
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
			if(temp.getTag() == "dbl"){//check the tag 
				tar.val = temp;//if it's double store it
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {//else continue
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}//end add rows
	
	/**
	 * Multiply two rows using the value classes multiply function. Store result in a target.
	 * @param r1 first row
	 * @param r2 second row
	 * @param t target row
	 * @return True on success false on failure.
	 */
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
		
		//loop over 
		for(int i = 0; i <= columns; i++){
			temp = n1.val.multiply(n2.val);//multiply the values
			if(temp.getTag() == "dbl"){//if the tag is double store it
				tar.val = temp;
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {//else increment pointers
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}//end multiply rows
	
	/**
	 * Divides two rows using the value classes divide function.
	 * If denominator is zero does not do operation and creates a value
	 * object with an invalid tag.
	 * @param r1 first row
	 * @param r2 second row
	 * @param t target row
	 * @return True on success false on failure.
	 */
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
			if(temp.getTag() == "dbl"){//check the tag
				tar.val = temp;
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {//else increment pointers
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}//end divide rows
	
	/**
	 * Subtract two rows using the value classes subtract function store the result in
	 * a target row.
	 * @param r1 first row
	 * @param r2 second row
	 * @param t target row
	 * @return True on success false on failure.
	 */
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
			if(temp.getTag() == "dbl"){//check the tag
				tar.val = temp;
				tar = tar.right;
				n1 = n1.right;
				n2 = n2.right;
			} else {//if not double just increment pointer
				n1 = n1.right;
				n2 = n2.right;
				tar = tar.right;
				rVal = false;
			}
			
		}
		return rVal;
	}//end subtract columns
	
	/**
	 * Add two columns using the value classes add function and store in a target column.
	 * @param c1 first column
	 * @param c2 second column
	 * @param t target column
	 * @return True on success false on failure.
	 */
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
			if(temp.getTag() == "dbl"){//check the tag 
				tar.val = temp;//if double store
				tar = tar.bottom;
				n1 = n1.bottom;
				n2 = n2.bottom;
			} else {//else increase pointer
				n1 = n1.bottom;
				n2 = n2.bottom;
				tar = tar.bottom;
				rVal = false;
			}
			
		}
		return rVal;
	}//end add columns
	
	//subtract columns
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
	}//end subtract columns
	
	//multiply columns
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
			if(temp.getTag() == "dbl"){//check the tag
				tar.val = temp;
				tar = tar.bottom;
				n1 = n1.bottom;
				n2 = n2.bottom;
			} else {//else advance pointer
				n1 = n1.bottom;
				n2 = n2.bottom;
				tar = tar.bottom;
				rVal = false;
			}
			
		}
		return rVal;
	}//end multiply columns
	
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
	}//end divide columns
	
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
	}//end assign cell
	
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
	}//end fill
	
	//delete row
	public boolean deleteRow(String row){
		int r = Integer.parseInt(row);
		Node above, below, n;
		if(rows != 1){ //make sure there are rows to remove
		//check to see if deleting first row if not.. isn't the special case
		if(r != 0 && r != rows && r % rows != 0){
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
	}//end delete row
	
	//insert a row
	public boolean insertRow(String row){
		int r = Integer.parseInt(row);
		Node n = null, previous = null, newNode = null, watcher = null, left = null;
		
		//case for an empty list
		if(head.bottom == null){
			if(columns == 1){//there are no columns to make so a new row count just has to be incremendted
				rows++;
				return true;
			} 
			head.bottom = new Node();
			n = head.bottom;
			for(int c = 0; c < columns; c++){
				newNode = new Node();
				n.right = newNode;
				n = n.right;
				if(c == (columns - 1)){
					newNode.right = this.head.bottom;
					return true;
				}
			}
		}
		
		if(r != 0 && r != rows && r < rows){
			previous = getCell((r-1), 0);//node above it
			watcher = previous;
			n = getCell(r, 0);
		} else if(r == 0 && r < rows){
			previous = getCell(this.rows - 1, 0); //the last cell in the list
			watcher = this.head.bottom;//watch the node so head can be reset
			n = getCell(r, 0); 
		} else if(r >= rows){
			previous = getCell(rows - 1, 0);
			watcher = null;
			n = getCell(0, 0);
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
	}//end insert row
	
	//delete a column
	public boolean deleteColumns(String col) {
		int c = Integer.parseInt(col);
		Node leftOf, rightOf, n;
		if(columns != 1){
			if(c != 0 && c != columns && c % columns != 0){
				leftOf = getCell(0, (c-1));//node left
				rightOf = getCell(0, (c+1));//node right
			} else {
				leftOf = getCell(0, this.columns - 1); //the last cell in the list
				rightOf = getCell(0, (c+1));
				if(columns != 1){//not the last row in the matrix
					this.head.bottom = getCell(0, (c+1));//reassign the head to the cell over one
				}
			}
			//loop over the columns
			for(int i = 0; i < rows; i++){
				n = leftOf.right;
				leftOf.right = n.right;
				n.bottom = null;//set the node to delete pointers to null so it can be garbage collected
				n.right = null;		
				leftOf = leftOf.bottom;
				rightOf = rightOf.bottom;
			}
			columns--;//now one less column
			return true;
			}else{//this will happen if this is the last column in the sheet
				head.bottom = null;
				return false;
		}
	}//end delete columns
	
	//insert a column
	public boolean insertColumn(String col){
		int c = Integer.parseInt(col);
		Node n = null, previous = null, newNode = null, watcher = null, top = null;
		
				//case for an empty list
				if(this.head.bottom == null){//list empty if head is null
					if(rows == 1){//there are no rows to make so a new column, count just has to be incremendted
						columns++;
						return true;
					}
					this.head.bottom = new Node();//init head
					n = this.head.bottom;
					//walk column and assign
					for(int m = 0; m < rows; m++){
						newNode = new Node();
						n.bottom = newNode;
						n = n.bottom;
						if(m == (rows - 1)){
							newNode.bottom = this.head.bottom;
							return true;
						}
					}
				}
		//case for non empty list
		if(c != 0 && c != rows && c < columns){
			previous = getCell(0, (c-1));//node left of it
			watcher = previous;//watch the node
			if(columns == 1){
				n = previous;//there is only one column need to assign n to previous 
			}else {
				n = getCell(0, c);
			}
		} else if(c == 0 && c < columns){
			previous = getCell(0, this.columns - 1); //the last cell in the list
			watcher = null;//watch the node so head can be reset
			n = getCell(0, c); 
		} else if(c >= columns){//we're inserting on the end
			previous = getCell(0, columns - 1);
			watcher = null;
			n = getCell(0, 0);
		}
		
		for(int i = 0; i < rows; i++) {
			newNode = new Node();
			newNode.right = n;
			previous.right = newNode;
			if(i == 0){
				top = newNode;
			}
			if(i != 0){
				top.bottom = newNode;
				top = newNode;
			}
			if(i == (rows - 1)){
				newNode.bottom = watcher;
				columns++;
			}
			if(c == 0 && i == 0){
				this.head.bottom = newNode;
				watcher = newNode;
			}
			previous = previous.bottom;//move the previous column over
			n = n.bottom;//move node column over
		}
		
		return true;
	}//end insert column
	
}//end grid class
