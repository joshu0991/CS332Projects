package com.joshua.lilly.dnsdynamic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestPrimes {
	
	@Test
	public void testEmptyList() {
		List<Integer> a, b;
		
		a = new ArrayList();
		b = new ArrayList();
		
		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), -1);
	}

	@Test
	public void testOneEmptyList() {
		List<Integer> a, b;
		a = new ArrayList<Integer>();
		b = new ArrayList<Integer>();
		
		a.add(new Integer(12));
		a.add(new Integer(25));
		a.add(new Integer(18));
		a.add(new Integer(8));
		
		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), -1);
	}

	@Test
	public void testNoPrimesSameSize() {
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

		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), -1);
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

		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 2);
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

		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 2);
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
		
		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 2);
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

		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 0);
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

		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 3);
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

		assertEquals(FindPrimeFactor.findPrimeFactor(a, b), 5);
	}	
}
