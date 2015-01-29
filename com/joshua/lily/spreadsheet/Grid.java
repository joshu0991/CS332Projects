package com.joshua.lily.spreadsheet;

public class Grid {
	
	private int rows = 9, columns = 5; //track number of rows and columns
	//node class for handling pointers to other nodes and values
	private class Node{
		Node bottom = null;
		Node right = null;
		Value val = new Value();
	}
	
	
	private Node head;
	public Grid(){ 
		head = new Node();
		setUpEmptyGrid();
	}
	
	//set up an empty 9x5 grid
	private void setUpEmptyGrid(){
		Node temp = new Node();//create a temp node init with with new node to start off
		head.bottom = temp;//set entrance point
		temp.val.sVal="start";
		Node top = temp;
		Node prevTop = null;
		
		for(int i = 0; i < columns; i++){
			if(i == 0){
				//do nothing
			} else {
				prevTop = top;
				temp = new Node();
				temp.val.dVal = 10 + i;
				top = temp;
				prevTop.right = temp;
			}
			for(int j = 0; j < rows -1; j++){
				Node newNode = new Node();
				newNode.val.dVal = 10 + j;
				temp.bottom = newNode;
				temp = newNode;
				
				if(j == 7){
					newNode.bottom = top;
				}
				
				if(prevTop != null){
					prevTop = prevTop.bottom;
					prevTop.right = newNode;
				}
			}
		}
		
	}
	
	public void displayGrid(){
		Node t = head.bottom;
		for(int i = 0; i < 10; i++){
			System.out.println("Dval " + t.val.dVal + " sval " + t.val.sVal);
			t = t.bottom;
		}
		t = head.bottom;
		t = t.right;
		for(int j = 0; j < 10; j++){
			System.out.println("to the right Dval " + t.val.dVal + " sval " + t.val.sVal);
			t = t.bottom;
		}
	}
	
}
