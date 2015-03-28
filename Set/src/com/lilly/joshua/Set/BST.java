package com.lilly.joshua.Set;

import java.util.Iterator;
import java.util.Stack;
/**
 * Class that represents a Binary tree.
 * @author Joshua Lilly
 * @param <T> generic type argument
 */
public class BST<T extends Comparable<T> > implements Iterable<T> {

	//root inits to null.
	/**
	 * root of the tree should initialize to zero.
	 */
	BSTNode<T> root = null;
	
	/**
	 * keep track of the size.
	 */
	private int size = 0;

	/**
	 * tree iterator class representing an iterator over the bst.
	 */
	private class TreeIterator<T> implements Iterator<T> {
		Stack<T> stack = new Stack<T>();//generic stack for temporarily storing elements.
		BSTNode<?> cursor = BST.this.root; // the root inits.to root of the tree

		/**
		 * @return returns true if this tree has another element false otherwise.
		 */
		@Override
		public boolean hasNext() {
			//false via short circuit evaluation if stack is empty OR cursor is null.
			return (!stack.empty() || cursor != null);
		}// has next

		/**
		 * @return returns the next element from the set. These are default
		 *  lexicographical order due to the fact that elements are constrained 
		 *  to be comparable.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			T savedCursor = null;
			// go left until we hit the left most
			// when we hit left most cursor will = null
			while (!stack.isEmpty() || cursor != null) {
				if (cursor != null) {
					stack.push((T) cursor);
					cursor = cursor.left;
				} else {
					if (!stack.isEmpty()) {//if not empty
						cursor = (BSTNode<T>) stack.pop();//pop the stack
						savedCursor = (T) cursor.data;//save the data.
						cursor = cursor.right;//move the cursor.
						return savedCursor;//return the data.
					}
				}
			}
			return savedCursor;
		}// next

	}// treeiterator class
	
	/**
	 * Generic class representing a binary tree node.
	 */
	private class BSTNode<T> {
		BSTNode<T> left;
		BSTNode<T> right;
		T data;
		/**
		 * 
		 * @return true if this class has a left element false otherwise.
		 */
		public boolean hasLeft(){
			if(left == null){
				return false;
			} else { 
				return true;
			}
		}
		
		/**
		 * 
		 * @return true if this class has a right element false otherwise.
		 */
		public boolean hasRight(){
			if(right == null){
				return false;
			} else {
				return true;
			}
		}
		
		/**
		 * Build a new BSTNode with the given argument.
		 * @param data the data to add to this node in the tree.
		 */
		BSTNode(T data){
			this.data = data;
			left = null;
			right  = null;
		}//BSTNode constructor
		
		/**
		 * @return formated string representing a bst node
		 * primarily for testing.
		 */
		public String toString(){
			if(this.data != null){
				return (String) data;
			} else {
				return null;
			}
		}
		
	}//BSTNode class

	/**
	 * @return iterator returns an iterator representing the private tree class.
	 */
	@Override
	public Iterator<T> iterator() {
		return new TreeIterator<T>();
	}//iterator

	
	/**
	 * non-recursive function for inserting data
	 * @param data the generic data to insert.
	 * @return return the recursive insert, true on success, false on failure.
	 */
	public boolean insert(T data){
		if(root == null){//if the root is null we found the location
			root = new BSTNode<T>(data);
			++size;
			return true;
		} else { //else enter the recursive insert
			return insert(root, data);
		}
	}//insert
	
	/**
	 * Private version called by public insert which inserts a value into a bst recursively.
	 * @param p binary search tree node should init to reference to this root.
	 * @param value the value to insert into the tree
	 * @return return true on success false on failue.
	 */ 
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
			//if 0 returns they are equal
			if (root.data.compareTo(val) == 0) {
					return root;
			} else if (root.data.compareTo(val) > 0) { // traverse left
				return search(val, root.left);
			} else if (root.data.compareTo(val) < 0) { //traverse right
				return search(val, root.right);
			} else {// we are here by mistake return null for debugging.
				System.out.println("Failed");
				return null;
			}
		} else {
			return null;
		}
	}//search
	
	/**
	 * Public facing non-recursive find. Just calls the recursive version.
	 * @param val the value to find.
	 * @return true on success and false on failure.
	 */
	public BSTNode<T> find(T val){
		if(root == null){
			return root;
		}else{
			return search(val, root);//start recursing.
		}
	}//find
	
	/**
	 * Find out if a value exist in a tree.
	 * @param data the data to search for
	 * @return true if this tree contains an element false if it doesn't.
	 */
	public boolean hasElement(T data){
		if(root != null){
		if(find(data) != null || root == null){
			return true;
		}else{
			return false;
		}
		} else {
			return false;
		}
	}//hasElement
	
	/**
	 * Delete a value from a bst.
	 * @param val the value to remove from a tree.
	 * @return true if the tree contained the value and it was deleted false otherwise.
	 */
	public boolean delete(T val){
		boolean removed = false;
		// the value isn't in the tree.
		if (!hasElement(val)) {
			return false;
		}
		
		BSTNode<T> parent = findParent(val, root);
		BSTNode<T> target = find(val);
		int count = countChildren(target);
		
		//case where the root = parent.
		if(parent == target){
			//one element in the tree that we will be removing
			if(count == 0){
				if(root.hasLeft() ){
					size--;
					root = root.left;
				} else if(root.hasRight()){
					size--;
					root = root.right;
				} else {
					size--;
					root = null;
				}
				return true;
			} else if(count == 1){
				if(root.hasLeft() ){
					size--;
					root = root.left;
					return true;
				}else{
					size--;
					root = root.right;
					return true;
				}
			}else if(count == 2){
				//let it continue so do nothing.
				//this is just here to reduce ambiguity
				//and increase readability.
			}
		}
		//switch for the count of children
		switch (count) {
		
		// this node has no children so it's a leaf hack it off!
		case (0):
			//switch to check where the node is.
			switch (leftOrRight(parent, val)) {
			
			case LEFT:
				parent.left = null;
				removed = true;
				size--;
				break;//case left
				
			case RIGHT:
				parent.right = null;
				removed = true;
				size--;
				break;//case right
			
			}
		break;//zero children
		
		//one child case need to go around target node.
		case (1):
			//find where the target is relative to the parent.
			switch (leftOrRight(parent, val)) {
			//target lies to the left of the parent.
			case LEFT:
				//find out if the target as a left or right child.
				switch(hasLeftOrRight(target)){
				//the target has a child on the left.
				case LEFT:
					parent.left = target.left;
					removed = true;
					cleanUp(target);//set the node to point to nothing.
					size--;
					break;//case left
					
				//the target has a child on the right.
				case RIGHT:
					parent.left = target.right;
					removed = true;
					cleanUp(target);
					size--;
					break;//case right
					
				//some way made it here without children we are now in a errored state.
				default:
					//hold breath and pray to god we aren't deleting half our tree :)... jk
					parent.left = null;
					size--;
					break;//case default.	
				}
				break;//case left.
				
			//target lies to the right of parent.
			case RIGHT:
				switch(hasLeftOrRight(target)){
				//target has a child on the left.
				case LEFT:
					size--;
					parent.right = target.left;
					removed = true;
					cleanUp(target);
					break;//case left.
					
				//target has a child on the right.	
				case RIGHT:
					size--;
					parent.right = target.right;
					removed = true;
					cleanUp(target);
					break;//case right 
					
				default:
					size--;
					parent.right = null;
					break;//default case
				}
				break;//case right
			}
		break;//case 1
		
		//two children need to do a replacement.
		case(2):
			//parent of smallest first target to swap second.
			BSTNode[] dataArray = findSmallestInRight(target);
			//swap the data
			BSTNode<T> p = dataArray[0];
			BSTNode<T> smallest = dataArray[1];
			target.data = smallest.data;
			//the smallest node only has a single child in it's rst
			if(p == smallest){
				p = target;
				p.right = null;
			}
			//check to see if the smallest node has any right children.
			//can't have any left since it wouldn't be the smallest if it did.
			else if(smallest.hasRight()){
				removed = true;
				size--;
				p.left = smallest.right;
			} else {
				size--;
				removed = true;
				p.left = null;
			}
		break;//case 2
		
		}
		return removed;
	}//delete
	
	/**
	 * Find the parent of a given node.
	 * @param val the value to find the parent of
	 * @param root a bstnode representing at first the root of this tree.
	 * @return a reference to a bst node.
	 */
	private BSTNode<T> findParent(T val, BSTNode<T> root){
		//if this is the trees root we have a special case.
		//parent = target
		if(root.data.compareTo(val) == 0){
			return root;
		} else {
			return findParent(root, val,  null);
		}
	}//findParent
	
	/**
	 * Find the parent of a node. This is the private recursive version
	 * @param root a reference to a bst node.
	 * @param val the value representing the value to find the parent of.
	 * @param parent the parent of the node that is root.
	 * @return return a reference to a bst node.
	 */
	private BSTNode<T> findParent(BSTNode<T> root, T val, BSTNode<T> parent){
			if (root != null) {
				//this is the value we are looking for.
				if (root.data.compareTo(val) == 0) {
						return parent;
				} else if (root.data.compareTo(val) > 0) {
					return findParent(root.left, val, root);
				} else if (root.data.compareTo(val) < 0) {
					return findParent(root.right, val, root);
				} else {// we are here by mistake return null for debugging.
					System.out.println("Failed");
					return null;
				}
			} else {
				return null;
			}
	}	
	
	/**
	 * Decide if the node is on the right or left.
	 * @param node the node to check if it has a left or right node.
	 * @param val the value we are looking for on the left or right.
	 * @return an enum type that represents the left or right value on a node.
	 */
	private leftRight leftOrRight(BSTNode<T> node, T val){
		if(node.left != null){
			if(node.left.data.compareTo(val) == 0){
				return leftRight.LEFT;
			}
		}
		if(node.right != null) {
			if(node.right.data.compareTo(val) == 0){
				return leftRight.RIGHT;
			}
		}
		//Should only end up here when the root is the parent and the target
		//since we previously guaranteed that the data passed to this function
		//was a parent node.
		return null;
	}
	
	/**
	 * used to quickly determine if there exist a value on the side.
	 * should be cautious when using since this was designed with using it
	 * in cases where a node has one child.
	 * @param node the node to check if it has right or left children.
	 * @return return an enum type that represents the right or left element of the node
	 * doesn't matter which we return first since the way this function is used doesn' care.
	 * If this fails it returns null.
	 */
	private leftRight hasLeftOrRight(BSTNode<T> node){
		if(node.hasLeft()){
			return leftRight.LEFT;
		} else if(node.hasRight()) {
			return leftRight.RIGHT;
		} else {
			return null;
		}
	}//has left or right.
	
	/**
	 * Count the children on a given node.
	 * @param node the node to count the children of.
	 * @return the number of children the tree contains.
	 */
	private int countChildren(BSTNode<T> node){
		int counter = 0;
		if(node.hasLeft()){
			++counter;
		}
		if(node.hasRight()){
			++counter;
		}
		return counter;
	}//countchildren
	
	/**
	 * enum containing the left and right values.
	 */
	private enum leftRight{
		LEFT, RIGHT;
	}//left or right enum
	
	//make sure removed node gets slotted for garbage collection and doesn't
	//hang out where it isn't wanted.
	private void cleanUp(BSTNode<T> node){
		node.left = null;
		node.right = null;
		node.data = null;
	}

	/**
	 * Get the smallest value in a given nodes right subtree for use 
	 * when deleting a node with two children.
	 * @param node the node to find the smallest value in right subtree.
	 * @return A generic array containing the smallest node in the right subtree
	 * at r[1] and the parent of that node in r[0]
	 */
	private BSTNode<T>[] findSmallestInRight(BSTNode<T> node){
		boolean con = true;
		BSTNode<T> rVal = null;
		BSTNode<T> parent = null;
		//get a pointer to the right subtree
		if(node.hasRight()){
			rVal = node.right;
			parent = rVal;
		} else {//something went horribly wrong.
			return null;
		}
		while(con == true){
			if(rVal.hasLeft()){
				parent = rVal;
				rVal = rVal.left;//if it has a val go to that val
			} else {
				con = false;//else we are at the smallest break and return.
			}
		}
		//make an array of the raw class
		BSTNode<T>[] r = new BSTNode[2];
		r[0] = parent;
		r[1] =  rVal;
		return r;
	}//findSmallestInRight
	
	/**
	 * @return size of this tree.
	 */
	public int getSize(){
		return size;
	}
	
}//BST.java
