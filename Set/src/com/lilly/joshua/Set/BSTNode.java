package com.lilly.joshua.Set;

public class BSTNode<T extends Comparable<T>> {
	BSTNode<T> left;
	BSTNode<T> right;
	T data;
	
	BSTNode(BSTNode<T> left, BSTNode<T> right, T data){
		this.left = left;
		this.right = right;
		this.data = data;
	}

}
