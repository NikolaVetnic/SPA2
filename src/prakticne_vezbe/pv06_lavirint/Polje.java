package prakticne_vezbe.pv06_lavirint;

public class Polje {

	private int x, y, v;

	public Polje(int x, int y, int v) {
		super();
		this.x = x;
		this.y = y;
		this.v = v;
	}

	public int x() { return x; }
	public int y() { return y; }
	public int v() { return v; }

	public String toString() {
		return "[(" + y + "," + x + "), " + v + "]";
	}
}
