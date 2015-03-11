package com.lilly.joshua.Set;

import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T> > implements Iterable<T> {

	private class TreeIterator<T> implements Iterator<T>{
		Stack<T> s = new Stack<T>();
		BSTNode<T> cursor;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class BSTNode<T> {
		BSTNode<T> left;
		BSTNode<T> right;
		T data;
		
		BSTNode(BSTNode<T> left, BSTNode<T> right, T data){
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new TreeIterator<T>();
	}


}
