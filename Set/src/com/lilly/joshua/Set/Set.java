package com.lilly.joshua.Set;

import java.util.Iterator;

public class Set<T extends Comparable<T> > implements Iterable<T>, Comparable <Set<T> > {

	BST<T> tree = new BST<T>();
	
	//creates an empty set
	public Set(){
		
	}
	
	public Set(T[] setElements){
		
	}
	
	//insert a value into the bianary search tree
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
			Iterator<T> it = tree.iterator();
			for(int i = 0; i < tree.getSize(); i++){
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
			}
				ret += " }";
		} else {
			ret = "{}";
		}
		return ret;
	}
	
	
	@Override
	public int compareTo(Set<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
