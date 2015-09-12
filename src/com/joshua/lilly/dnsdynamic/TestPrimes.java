package com.joshua.lilly.dnsdynamic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestPrimes {
	
	@Test(expected=NoNumberFoundException.class)
	public void testEmptyList() throws NoNumberFoundException {
		List<Integer> a, b;
		
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();
		
		FindPrimeFactor.findPrimeFactor(a, b);
	}

	@Test(expected=NoNumberFoundException.class)
	public void testOneEmptyList() throws NoNumberFoundException {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();
		
		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));
		
		FindPrimeFactor.findPrimeFactor(a, b);
	}

	@Test(expected=NoNumberFoundException.class)
	public void testNoPrimesSameSize() throws NoNumberFoundException {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));

		b.add(new Integer(6));
		b.add(new Integer(14));
		b.add(new Integer(12));
		b.add(new Integer(6));

		FindPrimeFactor.findPrimeFactor(a, b);
	}

	@Test
	public void testAllGoodSameSize() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));

		b.add(new Integer(6));
		b.add(new Integer(2));
		b.add(new Integer(3));
		b.add(new Integer(2));

		try {
			assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 2);
		} catch (NoNumberFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAllGoodALarger() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));
		a.add(new Integer(8));
		a.add(new Integer(8));
		a.add(new Integer(8));
		a.add(new Integer(8));
		
		b.add(new Integer(6));
		b.add(new Integer(2));
		b.add(new Integer(3));
		b.add(new Integer(2));

		try {
			assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 2);
		} catch (NoNumberFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testAllGoodBLarger() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));

		
		b.add(new Integer(6));
		b.add(new Integer(2));
		b.add(new Integer(3));
		b.add(new Integer(2));
		b.add(new Integer(2));
		b.add(new Integer(2));
		b.add(new Integer(2));
		b.add(new Integer(2));
		b.add(new Integer(2));
		b.add(new Integer(2));
		
		try {
			assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 2);
		} catch (NoNumberFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testfirstIndexIsPrimeSameSize() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));
		
		b.add(new Integer(2));
		b.add(new Integer(2));
		b.add(new Integer(3));
		b.add(new Integer(2));
		
		try {
			FindPrimeFactor.findPrimeFactor(a, b);
		} catch (NoNumberFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testLastIndexIsPrimeSameSize() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));

		
		b.add(new Integer(5));
		b.add(new Integer(2));
		b.add(new Integer(4));
		b.add(new Integer(2));

		try {
			assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 3);
		} catch (NoNumberFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testLastIndexPrimeALarger() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));
		a.add(new Integer(18));
		a.add(new Integer(25));
		
		b.add(new Integer(5));
		b.add(new Integer(2));
		b.add(new Integer(4));
		// B has to contain a prime factor so it has to be here the last two indices of a are no tested
		b.add(new Integer(2));
	}
	
	@Test
	public void testRandomIndexIsPrime() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));
		a.add(new Integer(8));
		a.add(new Integer(8));
		a.add(new Integer(8));
		a.add(new Integer(8));
		a.add(new Integer(8));

		
		b.add(new Integer(5));
		b.add(new Integer(2));
		b.add(new Integer(4));
		b.add(new Integer(8));
		b.add(new Integer(28));
		b.add(new Integer(2));
		b.add(new Integer(3));
		b.add(new Integer(2));
		b.add(new Integer(2));

		try {
			assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 5);
		} catch (NoNumberFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegativeList() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();

		a.add(new Integer(-4));
		a.add(new Integer(15));
		
		b.add(new Integer(-2));
		b.add(new Integer(3));
		b.add(new Integer(0));

		try {
			assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 1);
		} catch (NoNumberFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@Test(expected=NullPointerException.class)
	public void testNullPointers() throws NoNumberFoundException {
		List<Integer> a = null, b = null;
		
		FindPrimeFactor.findPrimeFactor(a, b);
	}
}
