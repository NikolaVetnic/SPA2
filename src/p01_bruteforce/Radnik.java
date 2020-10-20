package p01_bruteforce;

class Radnik implements Comparable<Radnik> {

	private String ime;
	private int plata;
	
	
	public Radnik(String ime, int plata) {
		this.ime = ime;
		this.plata = plata;
	}
	
	
	public String toString() {
		return ime + ", plata: " + plata;
	}
	
	
	public String getIme()	{ return ime; }
	public int getPlata()	{ return plata; }
	
	
	public int compareTo(Radnik r) {
		return ime.compareTo(r.ime);
	}
}
