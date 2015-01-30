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
		int t_counter = 1;
		temp.val.dVal = t_counter;// test value
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
				temp.val.dVal = t_counter;
				top = temp;
				prevTop.right = temp; // link to the right
			}
			for (int j = 0; j < rows - 1; j++) {
				Node newNode = new Node();
				t_counter += 1;
				newNode.val.dVal = t_counter;
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
					for (int k = 0; k <= j; k++) {// find the node by traversing
													// downward in the first
													// column
						tmp = tmp.bottom;
					}
					temp.right = tmp;// set the link to the beginning
				}
			}
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ~~~~~~~~~~~~~TEST~~~~~~~~~~~CODE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void displayGrid() {
		Node t = head.bottom;

		for (int a = 0; a < rows + 1; a++) {
			System.out.println("First column " + t.val.dVal);
			t = t.bottom;

		}
		System.out.println("---------------------------------------");
		t = head.bottom.right;
		for (int b = 0; b < rows + 1; b++) {
			System.out.println("Second col " + t.val.dVal);
			t = t.bottom;
		}
		System.out.println("---------------------------------------");

		t = head.bottom.right.right;
		for (int c = 0; c < rows + 1; c++) {
			System.out.println("third col " + t.val.dVal);
			t = t.bottom;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.right.right.right;
		for (int d = 0; d < rows + 1; d++) {
			System.out.println("forth col " + t.val.dVal);
			t = t.bottom;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.right.right.right.right;
		for (int e = 0; e < rows + 1; e++) {
			System.out.println("fifth col " + t.val.dVal);
			t = t.bottom;
		}
		// check a random node
		t = head.bottom.right.bottom.bottom.right;
		System.out.println("Random " + t.val.dVal);

		System.out.println("---------------------------------------");
		t = head.bottom;
		for (int e = 0; e < columns; e++) {
			System.out.println("first row " + t.val.dVal);
			t = t.right;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.bottom;
		for (int e = 0; e < columns + 1; e++) {
			System.out.println("second row " + t.val.dVal);
			t = t.right;
		}

		System.out.println("---------------------------------------");
		t = head.bottom.bottom.bottom;
		for (int e = 0; e < columns + 1; e++) {
			System.out.println("third row " + t.val.dVal);
			t = t.right;
		}
		System.out.println("---------------------------------------");
		t = head.bottom.bottom.bottom.bottom;
		for (int e = 0; e < columns + 1; e++) {
			System.out.println("forth row " + t.val.dVal);
			t = t.right;
		}
		System.out.println("From tostring " + t.val.toString());

	}

}
