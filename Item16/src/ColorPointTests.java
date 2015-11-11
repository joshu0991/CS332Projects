import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ColorPointTests {

	@DataPoints
	public static Point[] point_vals() {
		return new Point[] { new Point(1, 2), new Point(1, 2), new Point(1, 2)};
	}
	
	@DataPoints
	public static ColorPoint[] color_point_vals() {
		return new ColorPoint[] { new ColorPoint(1, 2, ColorPoint.Color.RED), 
								  new ColorPoint(1, 2, ColorPoint.Color.RED),
								  new ColorPoint(1, 2, ColorPoint.Color.RED)};
	}
  

	@Theory
	public void testSymmetry(ColorPoint cp1, ColorPoint cp2, Point p1, Point p2) {
		assertTrue(cp1.equals(cp2));
		assertTrue(cp2.equals(cp1));
		
		assertTrue(p1.equals(p2));
		assertTrue(p2.equals(p1));
	}

	@Theory
	public void testTransativity(ColorPoint cp1, ColorPoint cp2, ColorPoint cp3, 
			Point p1, Point p2, Point p3) {
		assertTrue(cp1.equals(cp2));
		assertTrue(cp2.equals(cp3));
		assertTrue(cp1.equals(cp3));

		assertTrue(p1.equals(p2));
		assertTrue(p2.equals(p3));
		assertTrue(p1.equals(p3));

	}
	
	@Theory
	public void testHascode(ColorPoint cp1, ColorPoint cp2, ColorPoint cp3, 
			Point p1, Point p2, Point p3) {
		Set<Point> h1 = new HashSet<Point>();
		h1.add(p1);
		h1.add(p2);
		h1.add(p3);

		Set<ColorPoint> h2 = new HashSet<ColorPoint>();
		h2.add(cp1);
		h2.add(cp2);
		h2.add(cp3);
		
		assertTrue(h1.contains(new Point(1, 2)));
		// Point doesn't get added multiple times
		assertTrue(h1.size() == 1);
		
		h1.add(new Point(3, 4));
		h1.add(new Point(4, 3));
		
		assertTrue(h1.contains(new Point(3, 4)));
		assertTrue(h1.contains(new Point(4, 3)));
		
		assertTrue(h1.size() == 3);
		
		assertTrue(h2.contains(new ColorPoint(1, 2, ColorPoint.Color.RED)));
		// Point doesn't get added multiple times
		assertTrue(h2.size() == 1);
		
		h2.add(new ColorPoint(3, 4, ColorPoint.Color.BLUE));
		h2.add(new ColorPoint(3, 4, ColorPoint.Color.GREEN));

		assertTrue(h2.contains(new ColorPoint(3, 4, ColorPoint.Color.BLUE)));
		assertTrue(h2.contains(new ColorPoint(3, 4, ColorPoint.Color.GREEN)));
		
		assertTrue(h2.size() == 3);
	}
}
