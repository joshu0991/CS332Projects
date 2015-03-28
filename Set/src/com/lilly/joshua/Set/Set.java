package com.lilly.joshua.Set;

import java.util.Iterator;
/**
 * @author Joshua Lilly
 * Class that represents mathematical sets.
 */
public class Set<T extends Comparable<T> > implements Iterable<T>, Comparable <Set<T> > {

	/**
	 * each set has a binary tree
	 */
	BST<T> tree = new BST<T>();
	
	/**
	 * creates an empty set.
	 */
	public Set(){}
	
	/**
	 * Builds a set from a given array.
	 * @param setElements a generic array of elements to be constructed
	 * in this set.
	 */
	public Set(T[] setElements){
		for(int i = 0; i < setElements.length; i++){
			tree.insert(setElements[i]);
		}
	}//set cstor
	
	/**
	 * insert a value into the binary search tree that represents this set.
	 * @param value a generic type of data
	 * @return true on success false on failure.
	 */
	public boolean insert(T value){
		if(tree != null){
			return tree.insert(value);
			//return true;
		} else {
			return false;
		}
	}//insert
	
	/**
	 * delete a target value from this set.
	 * @param target the generic type argument to remove.
	 * @return true on success false on failure.
	 */
	public boolean delete(T target){
		if(tree != null){
			return tree.delete(target);
			//return true;
		} else {
			return false;
		}
	}//delete
	
	/**
	 * @return Formated string representing this set in lexicographical order.
	 */
	public String toString(){
		String ret = "{}";
		if(tree.getSize() != 0){
			ret = "";
			int i =0;
			Iterator<T> it = tree.iterator();
			while(it.hasNext()){
				T temp = it.next();
				if(temp != null){
				if(i != tree.getSize() && i != 0 ){
					ret += ", " + temp;
				} else if(i == 0) {
					ret = "{ " + temp;
				} else {
					ret += temp;
				}
				}
				i++;
			}
				ret += " }";
		} else {
			ret = "{}";
		}
		return ret;
	}//toString
	
	/**
	 * @param other, a set representing a set contained outside of this class.
	 * @return return a new set representing the mathematical union of this set
	 * and the set passed to this function.
	 */
	public Set<T> union(Set<T> other){
		Iterator<T> a = this.iterator();
		Iterator<T> b = other.iterator();
		Set<T> newSet = new Set<T>();
		//get all of the values from this set and put
		//in a new set.
		while(a.hasNext()){
			newSet.insert(a.next());
		}
		//get all of the values from a passed in set and add them
		//to the new set.
		while(b.hasNext()){
			newSet.insert(b.next());
		}
		return newSet;
	}//union
	
	/**
	 * @param other, a set representing a set contained outside of this class.
	 * @return a new set representing the intersection of this class and the class passed
	 * to this function.
	 */
	public Set<T> intersection(Set<T> other){
		Set<T> newSet = new Set<T>();
		Iterator<T> a = this.iterator();
		while(a.hasNext()){
			T member = a.next();
			if(other.elementOf(member)){
				//while this set has a next element and that element is a member of this class
				//add this member to the set.
				newSet.insert(member);
			}
		}
		return newSet;
	}//intersection
	
	/**
	 * @param t an element to check if this class contain.
	 * @return true if this set contains the value t.
	 */
	public boolean elementOf(T t){
		if(tree.hasElement(t)){
			return true;
		} else {
			return false;
		}
	}//elementOf
	
	@Override
	/**
	 * @param other t the element to compare to the element calling this function.
	 * @return 	if this class is larger returns 1 if they are equal
	 *          return 0 if the other class is larger returns - 1
	 */
	public int compareTo(Set<T> other) {
		//a is this sets iterator
		Iterator<T> a = this.iterator();
		//b is the other sets iterator
		Iterator<T> b = other.iterator();
		while(a.hasNext() || b.hasNext()){
			//a is longer length wise.
			if(a.hasNext() && !b.hasNext()){
				return 1;
			} else if(!a.hasNext() && b.hasNext()) {//b is longer length wise.
				return -1;
			} else if (!a.hasNext() && !b.hasNext()){ // they are the exact same sets
				return 0;							  //so don't insert.
			} else {//else they both have elements so compare them.
				T aVal = a.next();
				T bVal = b.next();
				//if the values are the same need to compare the next values.
				if(aVal.compareTo(bVal) == 0){
					continue;
				}else{
					return aVal.compareTo(bVal);
				}
			}
		}
		return 0;
	}//compareTo

	/**
	 * 	Determines if this class is a subset of the passed set.
	 * @param set, the set to determine if the set is a subset of this class. 
	 * @return true if the set is a subset false if it isn't.
	 */
	public boolean subsetOf(Set<T> set){
		Iterator<T> a = this.iterator();
		boolean posSubSet = true;
		while(a.hasNext()){
			//this will evaluate to false a break if the element isn't in the tree.
			posSubSet = set.tree.hasElement(a.next());
			if(posSubSet == false){
				return posSubSet;
			}
		}
		return posSubSet;
	}//subsetOf
	
	/**
	 * @return return a new set that represents a deep copy of this
	 * set.
	 */
	public Set<T> copy(){
		Iterator<T> it = iterator();
		Set<T> newSet = new Set<T>();
		//get all of the values in the set and insert  them
		//into the new set aka deep copy.
		while(it.hasNext()){
			newSet.insert(it.next());
		}
		return newSet;
	}//copy
	
	/**
	 * @return the iterator from the bst class.
	 */
	@Override
	public Iterator<T> iterator() {
		return tree.iterator();
	}//iterator

}
