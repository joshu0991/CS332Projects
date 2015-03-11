package com.lilly.joshua.Set;

import java.util.Iterator;

public class Set<T extends Comparable<T> > implements Iterable<T>, Comparable <Set<T> > {

	//creates an empty set
	public Set(){
		
	}
	
	public Set(T[] setElements){
		
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
