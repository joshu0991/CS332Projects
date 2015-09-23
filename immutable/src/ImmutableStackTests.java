

import static org.junit.Assert.*;

import org.junit.Test;

public class ImmutableStackTests {

	@Test(expected=IllegalStateException.class)
	public void testMutability() {
		Stack s = new Stack();
		Stack a = s.push(new Integer(1));
		Integer i = (Integer) a.top();
		assertTrue(i.intValue() == 1);
		// popable stack should be an empty stack
		Stack popable = a.pop();
		// a should still be exactly the same
		i = (Integer) a.top();
		// This will prove a wasn't modified
		assertTrue(i.intValue() == 1);
		// If this throws it means we didn't mutate our original object either.
		popable.pop();
	}

	@Test
	public void testAddingMultipleValues() {
		Stack s = new Stack();
		s = s.push(new Integer(7));
		s = s.push(new Integer(8));
		s = s.push(new Integer(9));
		s = s.push(new Integer(10));
		s = s.push(new Integer(11));
		s = s.push(new Integer(12));
		
		assertTrue(((Integer) s.top()).intValue() == 12);
		
		s = s.pop();
		assertTrue(((Integer) s.top()).intValue() == 11);
		
		s = s.pop();
		assertTrue(((Integer) s.top()).intValue() == 10);
		
		s = s.pop();
		assertTrue(((Integer) s.top()).intValue() == 9);
		
		// Lets re-add some elements
		s = s.push(new Integer(1));
		assertTrue(((Integer) s.top()).intValue() == 1);
		
		s = s.push(new Integer(2));
		assertTrue(((Integer) s.top()).intValue() == 2);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPopEmptyStack() {
		Stack s = new Stack();
		s.pop();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTopmptyStack() {
		Stack s = new Stack();
		s.top();
	}
	
	@Test
	public void testMutabilityRoundTwo() {
		Stack s = new Stack();
		Stack t = new Stack();
		Stack u = new Stack();
		Stack v = new Stack();
		Stack w = new Stack();
		Stack x = new Stack();
		
		s = s.push(new Integer(7));
		t = s.push(new Integer(8));
		u = t.push(new Integer(9));
		v = u.push(new Integer(10));
		w = v.push(new Integer(11));
		x = w.push(new Integer(12));
		
		assertTrue(((Integer) x.top()).intValue() == 12);
		x.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 12);
		x = x.pop();
		
		assertTrue(((Integer) w.top()).intValue() == 11);
		assertTrue(((Integer) w.top()).intValue() == ((Integer) x.top()).intValue());
		
		x.pop();
		w.pop();
		
		assertTrue(((Integer) w.top()).intValue() == 11);
		assertTrue(((Integer) w.top()).intValue() == ((Integer) x.top()).intValue());
		
		x = x.pop();
		w = w.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 10);
		assertTrue(((Integer) w.top()).intValue() == 10);
		assertTrue(((Integer) w.top()).intValue() == ((Integer) x.top()).intValue());
		assertTrue(((Integer) v.top()).intValue() == ((Integer) x.top()).intValue());
		
		x.pop();
		w.pop();
		v.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 10);
		assertTrue(((Integer) w.top()).intValue() == 10);
		assertTrue(((Integer) v.top()).intValue() == 10);
		
		x = x.pop();
		w = w.pop();
		v = v.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 9);
		assertTrue(((Integer) w.top()).intValue() == 9);
		assertTrue(((Integer) v.top()).intValue() == 9);
		assertTrue(((Integer) w.top()).intValue() == ((Integer) x.top()).intValue());
		assertTrue(((Integer) v.top()).intValue() == ((Integer) x.top()).intValue());
		assertTrue(((Integer) u.top()).intValue() == ((Integer) x.top()).intValue());
		
		x.pop();
		w.pop();
		v.pop();
		u.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 9);
		assertTrue(((Integer) w.top()).intValue() == 9);
		assertTrue(((Integer) v.top()).intValue() == 9);
		assertTrue(((Integer) u.top()).intValue() == 9);
		
		x = x.pop();
		w = w.pop();
		v = v.pop();
		u = u.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 8);
		assertTrue(((Integer) w.top()).intValue() == 8);
		assertTrue(((Integer) v.top()).intValue() == 8);
		assertTrue(((Integer) u.top()).intValue() == 8);
		assertTrue(((Integer) w.top()).intValue() == ((Integer) x.top()).intValue());
		assertTrue(((Integer) v.top()).intValue() == ((Integer) x.top()).intValue());
		assertTrue(((Integer) u.top()).intValue() == ((Integer) x.top()).intValue());
		assertTrue(((Integer) t.top()).intValue() == ((Integer) x.top()).intValue());
		
		x.pop();
		w.pop();
		v.pop();
		u.pop();
		t.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 8);
		assertTrue(((Integer) w.top()).intValue() == 8);
		assertTrue(((Integer) v.top()).intValue() == 8);
		assertTrue(((Integer) u.top()).intValue() == 8);
		assertTrue(((Integer) t.top()).intValue() == 8);
		
		x = x.pop();
		w = w.pop();
		v = v.pop();
		u = u.pop();
		t = t.pop();
		
		assertTrue(((Integer) x.top()).intValue() == 7);
		assertTrue(((Integer) w.top()).intValue() == 7);
		assertTrue(((Integer) v.top()).intValue() == 7);
		assertTrue(((Integer) u.top()).intValue() == 7);
		assertTrue(((Integer) t.top()).intValue() == 7);
		assertTrue(((Integer) s.top()).intValue() == 7);
		
	}
}
