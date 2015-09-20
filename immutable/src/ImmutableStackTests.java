

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
		Integer popedValue = (Integer) popable.getPoped();
		assertTrue(popedValue.intValue() == 1);
		// a should still be exactly the same
		i = (Integer) a.top();
		// This will prove a wasn't modified
		assertTrue(i.intValue() == 1);
		// If this throws it means we didn't mutate our original object either.
		popable.pop();
	}

}
