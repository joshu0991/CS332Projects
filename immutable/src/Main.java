import static org.junit.Assert.assertTrue;


public class Main {

	public static void main(String[] args) {
		Stack s = new Stack();
		s = s.push(new Integer(7));
		s = s.push(new Integer(8));
		s = s.push(new Integer(9));
		s = s.push(new Integer(10));
		s = s.push(new Integer(11));
		s = s.push(new Integer(12));
		
		System.out.print(((Integer) s.top()).intValue());

	}

}
