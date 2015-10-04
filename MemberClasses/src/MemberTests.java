import static org.junit.Assert.*;

import org.junit.Test;


public class MemberTests {

	@Test
	public void testBreakRep1Members() {
		Members m = new Members();
		m.join("James");
		assertTrue(m.isMember("James"));
		assertTrue(m.join("James"));
		// This breaks our rep invariant since duplicates are not allowed
		// this should return false but it returns true so this fails
		assertTrue(m.repOk1());
	}

	@Test
	public void testBreakContractMembers() {
		Members m = new Members();
		assertTrue(m.join("James"));
		assertTrue(m.join("James"));
		assertTrue(m.leave("James"));
		// This breaks our contract. After a member leaves
		// they shouldn't be in the group anymore.
		assertFalse(m.isMember("James"));
	}
	
	@Test
	public void testRep1Members1() {
		Members1 m = new Members1();
		m.join("James");
		assertTrue(m.isMember("James"));
		assertTrue(m.join("James"));
		assertTrue(m.repOk1());
		assertTrue(m.repOk2());
	}

	@Test
	public void testTryBreakContractMembers1() {
		Members1 m = new Members1();
		assertTrue(m.join("James"));
		assertTrue(m.join("James"));
		assertTrue(m.leave("James"));
		// This is a ok here.
		assertFalse(m.isMember("James"));
	}

		@Test
		public void testRep1Members2() {
			Members2 m = new Members2();
			m.join("James");
			assertTrue(m.isMember("James"));
			assertFalse(m.join("James"));
			assertTrue(m.repOk1());
			assertTrue(m.repOk2());
		}

		@Test 
		public void testBreakContractMembers2() {
			Members2 m = new Members2();
			assertTrue(m.join("James"));
			// James just doesn't get added per te contract
			assertFalse(m.join("James"));
			assertTrue(m.leave("James"));
			assertFalse(m.isMember("James"));
		}
}
