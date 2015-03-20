package com.lilly.joshua.Set;

import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T> > implements Iterable<T> {

	//root inits to null.
	BSTNode<T> root = null;
	
	//keep track of the size for debugging purposes.
	private int size = 0;

	//tree iterator class representing an iterator over the bst.
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
		//return the next element from the set. These are default lexographical order due
		//to the fact that elements are constrained to be comparable.
		public T next() {
			T savedCursor = null;
			// go left until we hit the left most
			// when we hit left most cursor will = null
			while (!stack.isEmpty() || cursor != null) {
				if(cursor != null){
					stack.push((T) cursor);
					cursor = cursor.left;
				} else {
					if(!stack.isEmpty()){
						cursor = (BSTNode<T>)stack.pop();
						savedCursor = (T)cursor;
						cursor = cursor.right;
						if(savedCursor != null){
							System.out.println("Saved cursor " + savedCursor.toString());
						}
					}
			}
			}
			return savedCursor;
		}// next
		
	}// treeiterator class
	
	private class BSTNode<T> {
		BSTNode<T> left;
		BSTNode<T> right;
		T data;
		
		//return true if this class has a left element.
		public boolean hasLeft(){
			if(left == null){
				return false;
			} else { 
				return true;
			}
		}
		
		//return true if this class has a right element.
		public boolean hasRight(){
			if(right == null){
				return false;
			} else {
				return true;
			}
		}
		
		//constructor insert the data.
		BSTNode(T data){
			this.data = data;
			left = null;
			right  = null;
		}//BSTNode constructor
		
		//to string for bstclass. for testing only...
		public String toString(){
			if(this.data != null){
				return (String) data;
			} else {
				return null;
			}
		}
		
	}//BSTNode class

	//iterator for retrunign an iterator to this tree.
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new TreeIterator<T>();
	}//iterator

	
	//non-recursive function for inserting data
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
		else if(comp > 0){
			//if left is null we have found the location.
			if(p.left == null){
				++size;
				p.left = new BSTNode<T>(value);
				return true;
			} else {
				return insert(p.left, value);//else we need to recurse left.
			}
		//if comp is greater than zero we need to insert the data to the right
		//if the location is empty or traverse right.
		} else if(comp < 0) {
			//if right is null we have the location
			if(p.right == null){
				++size;
				p.right = new BSTNode<T>(value);
				return true;
			} else {
				return insert(p.right, value);//else we need to recurse right
			}
		}
		//some unforeseen thing happened if we're here.
		return false;
	}//insert
	
	//private recursive version called by find.
	private BSTNode<T> search(T val, BSTNode<T> root){
		if (root != null) {
			if (root.data.compareTo(val) == 0) {
				//if (root != null)
				//	System.out.println("Found " + root.data);
					return root;
			} else if (root.data.compareTo(val) > 0) {
				return search(val, root.left);
			} else if (root.data.compareTo(val) < 0) {
				return search(val, root.right);
			} else {// we are here by mistake return null for debugging.
				System.out.println("Failed");
				return null;
			}
		} else {
			//System.out.println("Failed");
			return null;
		}
	}//search
	
	//public facing non-recursive find. Just calls the recursive version.
	public BSTNode<T> find(T val){
		if(root == null){
			return root;
		}else{
			return search(val, root);
		}
	}//find
	
	//find out if a value exist in a tree.
	public boolean hasElement(T data){
		if(find(data) != null){
			return true;
		}else{
			return false;
		}
	}//hasElement
	

}//BST.java
