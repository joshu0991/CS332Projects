package com.lilly.joshua.Set;

import java.util.Iterator;

public class Main {
	public static void main(String [] args){
	BST<String> s = new BST<String>();
	
	s.insert("Our");
	s.insert("fathers");
	s.insert("brought");
	s.insert("forth");
	Iterator<String> o = s.iterator();
	o.next();
	o.next();
	o.next();
	o.next();
	/*
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
	Iterator<String> o = s.iterator();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	o.next();
	/*System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	System.out.println(o.next());
	*/
	}
}
