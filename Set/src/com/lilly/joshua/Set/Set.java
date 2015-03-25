package com.lilly.joshua.Set;

import java.util.Iterator;

public class Set<T extends Comparable<T> > implements Iterable<T>, Comparable <Set<T> > {

	BST<T> tree = new BST<T>();
	
	//creates an empty set
	public Set(){
		
	}
	
	public Set(T[] setElements){
		for(int i = 0; i < setElements.length; i++){
			tree.insert(setElements[i]);
		}
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
		boolean has = false;
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
	public int compareTo(Set<T> other) {
		Iterator<T> a = this.tree.iterator();
		Iterator<T> b = other.tree.iterator();
		while(a.hasNext() || b.hasNext()){
			
		}
		
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return tree.iterator();
	}

}
