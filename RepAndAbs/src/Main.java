
public class Main {

	public static void main(String[] args) {
        Stack s = new Stack();

        s.push(new Integer(2));
        
        Stack b = new Stack();
        b.push(new String("1"));
        String a = s.toString();
        String c = b.toString();
        
        System.out.println(c);
        System.out.println(a);
	}

}
