import static org.junit.Assert.assertTrue;


public class Main {

	public static void main(String[] args) {
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
		
		System.out.print(((Integer) s.top()).intValue());
		System.out.print(((Integer) t.top()).intValue());
		System.out.print(((Integer) u.top()).intValue());
		System.out.print(((Integer) v.top()).intValue());
		System.out.print(((Integer) w.top()).intValue());
		System.out.print(((Integer) x.top()).intValue());
System.out.println();
		s = s.pop();
		t = t.pop();
		u = u.pop();
		v = v.pop();
		w = w.pop();
		x = x.pop();
		
		System.out.print(" s");
		System.out.print(((Integer) t.top()).intValue());
		System.out.print(((Integer) u.top()).intValue());
		System.out.print(((Integer) v.top()).intValue());
		System.out.print(((Integer) w.top()).intValue());
		System.out.print(((Integer) x.top()).intValue());

	}

}
