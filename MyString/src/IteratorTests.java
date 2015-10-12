import static org.junit.Assert.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.Test;


public class IteratorTests {

	@Test
	public void testMutability() {
		MyString s = new MyString("Hello new york!!");
		java.util.Iterator<String> i = s.iterator();
		assertTrue(i.hasNext());
		assertTrue((i.next()).equals("H"));
		assertTrue((i.next()).equals("e"));

		// Sequential calls to the same object have the same effect
		i = s.iterator();
		assertTrue((i.next()).equals("H"));
		assertTrue((i.next()).equals("e"));
	}
	
	@Test
	public void palendromeTest() {
		MyString m = new MyString("TacocaT");
		String newString = "";
		for(String s : m) {
			newString = s + newString;
		}
		
		assertTrue(newString.equals("TacocaT"));
	}

}
