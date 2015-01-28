package com.joshua.lily.spreadsheet;

public class Grid {
	
	//node class for handling pointers to other nodes and values
	private class node{
		node bottom = null;
		node right = null;
		Value val = new Value();
	}
	
	
	private node head;
	public Grid(){ 
		head = new node();
		setUpEmptyGrid();
	}
	
	//set up an empty 5x9 grid
	private void setUpEmptyGrid(){
		node temp = new node();//create a temp node init with with new node to start off
		head.bottom = temp;//set entrance point
		node top = head; //reference to head of a column
		node previousTop = null;
		/*hmmmm
		node row0, row1, row2, row3, row4, column0, column1, column2, 
		column3, column4, column5, column6, column7, column8;
		*/
		
		for(int i = 0; i < 5; i++){
			if(i == 0){//don't make a node if this is the first go though
				//do nothing
			} else {
				temp = new node();
				temp.val.dVal = i*20;
				top = temp;
				previousTop.right = temp;
			}
			for(int j = 0; j < 9; j++) { 
				node newNode = new node();//create the new node 
				newNode.val.dVal = 10 + j;
				temp.bottom = newNode; // insert new node below the temp
				temp = newNode;//advance temp node
				
				//System.out.println("Current node value " + temp.val.dVal + 
				//		" right value " + temp.right + " bottomValue " + temp.bottom);
				
				if(previousTop != null){//this means we are adding at least the second column
					previousTop = previousTop.bottom; //go down 1 node assign
					//System.out.println("Current node value " + previousTop.val.dVal + 
					//		" right value " + previousTop.right.val.dVal + " bottomValue " + previousTop.bottom.val.dVal);
					previousTop.right = newNode;//make new node the right node
				}
				
				if(j == 8){//if j == 8 this is the last node in the column
					temp.bottom=top; //so set pointer to the top of column
					previousTop = top;
				}
			}
			
		}
		
	}
	
	public void testColumn(){
		node temp = head;
		while(temp.bottom != head){
			System.out.println(temp.bottom.val.dVal);
		}
	}
	
}
