import static org.junit.Assert.assertTrue;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack s = new Stack();
		Stack a = s.push(new Integer(1));
		Integer i = (Integer) a.top();
		System.out.println("i is " + i.intValue());
		assertTrue(i.intValue() == 1);
		Stack popable = a.pop();
		Integer popedValue = (Integer) popable.getPoped();
		assertTrue(popedValue.intValue() == 1);
		// If this throws it means we didn't mutate our original object.
		s.pop();

	}

}
