

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
	}
}
