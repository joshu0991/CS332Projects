import java.util.Arrays;
import java.util.Iterator;


public class MyString implements Iterable<String>{

	MyString(String p_input) {
		// Cache the original input and make copies of it for the iterator
		m_data = p_input;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return new PrivateString(m_data);
	}

	private static class PrivateString implements Iterator<String> {

		public PrivateString(String s) {
			innerString = s;
			i = 0;
		}

		@Override
		public boolean hasNext() {
			if (i < innerString.length())
				return true;
			return false;
		}

		@Override
		public String next() {
			String s =  "" + innerString.charAt(i);
			i++;
			return s;
		}
		
		private String innerString;
		
		private int i = 0;
	}

	public String getString() {
		return m_data;
	}
	private String m_data;

}
