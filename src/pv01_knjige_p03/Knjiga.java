package pv01_knjige_p03;

public class Knjiga implements Comparable<Knjiga> {

	private int id;
	private String naslov, pisac; 

	public Knjiga(int id, String naslov, String pisac) {
		this.id = id;
		this.naslov = naslov;
		this.pisac = pisac;
	}
	
	public String toString() {
		return String.format("%-5d %-65s %-20s", id, naslov, pisac);
	}

	public int 		id() 		{ return id; 	 }
	public String 	naslov() 	{ return naslov; }
	public String 	pisac() 	{ return pisac;  }

	@Override
	public int compareTo(Knjiga that) {
		return id - that.id;
	}
}
