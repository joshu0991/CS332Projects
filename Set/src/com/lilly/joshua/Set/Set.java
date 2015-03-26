package com.lilly.joshua.Set;

import java.util.Iterator;

public class Set<T extends Comparable<T> > implements Iterable<T>, Comparable <Set<T> > {

	BST<T> tree = new BST<T>();
	
	//creates an empty set
	public Set(){}
	
	public Set(T[] setElements){
		for(int i = 0; i < setElements.length; i++){
			tree.insert(setElements[i]);
		}
	}
	
	//insert a value into the binary search tree
	public boolean insert(T value){
		if(tree != null){
			tree.insert(value);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delete(T target){
		if(tree != null){
			tree.delete(target);
			return true;
		} else {
			return false;
		}
	}
	
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
	}
	
	public Set union(Set other){
		Iterator<T> a = this.iterator();
		Iterator<T> b = other.iterator();
		Set<T> newSet = new Set<T>();
		while(a.hasNext()){
			newSet.insert(a.next());
		}
		while(b.hasNext()){
			newSet.insert(b.next());
		}
		return newSet;
	}
	
	public Set intersection(Set other){
		Set<T> newSet = new Set<T>();
		Iterator<T> a = this.iterator();
		while(a.hasNext()){
			T member = a.next();
			if(other.elementOf(member)){
				newSet.insert(member);
			}
		}
		return newSet;
	}
	
	public boolean elementOf(T t){
		if(tree.hasElement(t)){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	//if this class is larger returns 1
	//if they are equal return 0
	//if the other class is larger returns - 1
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
				if(aVal.compareTo(bVal) == 0){
					continue;
				}else{
					return aVal.compareTo(bVal);
				}
			}
		}
		return 0;
	}

	public boolean subsetOf(Set<T> a){
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Set<T> copy(){
		Iterator<T> it = iterator();
		Set<T> newSet = new Set<T>();
		//get all of the values in the set and insert  them
		//into the new set aka deep copy.
		while(it.hasNext()){
			newSet.insert(it.next());
		}
		return newSet;
	}
	
	@Override
	public Iterator<T> iterator() {
		return tree.iterator();
	}

}
