package com.lilly.joshua.Set;

import java.util.Iterator;

public class Main {
	public static void main(String [] args){
	BST<String> s = new BST<String>();
	//s.iterator().hasNext();
	s.insert("Hello");
	s.insert("rld");
	s.insert("Helo");
	s.insert("Word");
	s.insert("llo");
	s.insert("Wod");
	s.insert("Hllo");
	s.insert("or");
	s.insert("ello");
	s.insert("orld");
	s.insert("H");
	s.insert("W");
	System.out.println(s.iterator().next());
	}
}
