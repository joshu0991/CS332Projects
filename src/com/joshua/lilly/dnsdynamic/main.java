package com.joshua.lilly.dnsdynamic;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer>a = new ArrayList<Integer>();
		List<Integer>b = new ArrayList<Integer>();
		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));
		
		b.add(new Integer(5));
		b.add(new Integer(5));
		b.add(new Integer(3));
		b.add(new Integer(2));
		
		int i = 0;
		i = FindPrimeFactor.findPrimeFactor(a, b);
		
		System.out.println("I is " + i);
	}

}
