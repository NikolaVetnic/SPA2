package prakticne_vezbe.pv01_knjige_p02;

public class Knjiga implements Comparable<Knjiga> {

	private int id;
	private String naslov, pisac;
	
	public Knjiga(int id, String naslov, String pisac) {
		this.id = id;
		this.naslov = naslov;
		this.pisac = pisac;
	}
	
	@Override
	public String toString() {
		return id + " " + naslov + ", " + pisac;
	}
	
	public int id() 		{ return id; 		}
	public String naslov() 	{ return naslov; 	}
	public String pisac() 	{ return pisac; 	}

	@Override
	public int compareTo(Knjiga that) {
		return id - that.id;
	}
}
