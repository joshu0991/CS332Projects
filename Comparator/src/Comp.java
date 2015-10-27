import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Comp implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		Integer i1 = Math.abs(o1);
		Integer i2 = Math.abs(o2);
		return i1.compareTo(i2);
	}

	public static void main(String args[]) {
		Comp c = new Comp();
		
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		l.add(new Integer(45));
		l.add(new Integer(35));
		l.add(new Integer(-37));
		l.add(new Integer(2));
		
		System.out.println("List before sort: " + l.toString());
		
		Collections.sort(l, c);
		
		System.out.println("List sorted: " + l.toString());

	}
}