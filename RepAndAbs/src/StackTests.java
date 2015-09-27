import static org.junit.Assert.*;

import org.junit.Test;


public class StackTests {

	///////////////////////////////////////////////////
	// Rep invariant is not okay here
	//////////////////////////////////////////////////
	@Test
	public void repInvariantBroken() {
		Stack s = new Stack();
		s.push(new Integer(3));
		s.push(new Integer(4));
		s.push(null);
		// The rep invariant is incorrect here!
		assertFalse(s.repOk());
	}

}
