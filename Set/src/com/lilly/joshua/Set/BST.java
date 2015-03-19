package com.lilly.joshua.Set;

import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T> > implements Iterable<T> {

	BSTNode<T> root = null;
	private int size = 0;

	private class TreeIterator<T> implements Iterator<T> {
		Stack<T> stack = new Stack<T>();
		BSTNode<?> cursor = BST.this.root;

		@Override
		public boolean hasNext() {
			System.out.println("Cursor is " + cursor.data);
			return (!stack.empty() || cursor != null);
		}// has next

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			T savedCursor = null;
			// go left until we hit the left most
			// when we hit left most cursor will = null
			while (cursor != null) {
				stack.push((T) cursor);
			}
			// if the stack isn't emtpy pop the top element
			if (!stack.isEmpty()) {
				savedCursor = stack.pop();
				cursor = cursor.right;
			}
			return savedCursor;
		}// next
	}// treeiterator class
	
	private class BSTNode<T> {
		BSTNode<T> left;
		BSTNode<T> right;
		T data;
		
		BSTNode(T data){
			this.data = data;
			left = null;
			right  = null;
		}//BSTNode constructor
	}//BSTNode class

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new TreeIterator<T>();
	}//iterator

	public boolean insert(T data){
		if(root == null){//if the root is null we found the location
			root = new BSTNode<T>(data);
			++size;
			return true;
		} else { //else enter the recursive insert
			return insert(root, data);
		}
	}//insert
	
	//insert a value into a bst recursivly. 
	private boolean insert(BSTNode<T> p, T value){
		int comp = p.data.compareTo(value);
		//if it's equal to zero the data is already in the  list return false 
		//no duplicates in a bst
		if(comp == 0){
			return false;
		}
		//if comp is less than zero we need to insert the data to the left
		//if the location is empty or traverse to the left.
		else if(comp < 0){
			//if left is null we have found the location.
			if(p.left == null){
				++size;
				p.left = new BSTNode<T>(value);
			} else {
				insert(p.left, value);//else we need to recurse left.
			}
		//if comp is greater than zero we need to insert the data to the right
		//if the location is empty or traverse right.
		} else if(comp > 0) {
			//if right is null we have the location
			if(p.right == null){
				++size;
				p.right = new BSTNode<T>(value);
			} else {
				insert(p.right, value);//else we need to recurse right
			}
		}
		//some unforeseen thing happened if we're here.
		return false;
	}
	

}//BST.java
