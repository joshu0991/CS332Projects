import static org.junit.Assert.assertTrue;

public class Main {

	public static void main(String[] args)
	{
		MyString m = new MyString("TacocaT");
		String newString = "";
		for(String s : m) {
			newString = s + newString;
			System.out.println(newString);
		}
	}
	
	
}
