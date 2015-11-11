
public class ColorPoint{

	public enum Color {
		RED, BLUE, GREEN,
		ORANGE, YELLOW
	}

	private final Color color;
	private Point p;
	
	public ColorPoint(int x, int y, Color color) {
		p = new Point(x, y);
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ColorPoint))
			return false;
		ColorPoint cp = (ColorPoint)o;
		return cp.p.equals(p) && cp.color == color; 
	}

	public Point asPoint() {
		return p;
	}
	
	public int hashCode() {
		return p.hashCode() + color.ordinal();
	}
}
