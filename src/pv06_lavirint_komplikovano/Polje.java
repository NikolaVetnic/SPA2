package pv06_lavirint_komplikovano;

/**
 * Klasa Polje se koristi za pamcenje x i y koordinata nekog polja, ka-
 * o i vrednosti na toj poziciji u mapi. Program napisano od strane na-
 * stavnika sa predmeta. 
 */
public class Polje {

	private final int x, y, v;
	
	public Polje (int x, int y, int v) {
		this.x = x;
		this.y = y;
		this.v = v;
	}
	
	public int getX()	{ return x; }
	public int getY()	{ return y; }
	public int getV()	{ return v; }
	
	public String toString() {
		return "[(" + x + "," + y + "), " + v + "]";
	}
}
