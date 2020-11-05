package pv02_knjige;

public class Knjiga implements Comparable<Knjiga> {
	
	final private int id;
	final private String naslov;
	final private String autor;
	
	public Knjiga(int id, String naslov, String autor) {
		this.id = id;
		this.naslov = naslov;
		this.autor = autor;
	}

	@Override
	public int compareTo(Knjiga that) {
		
		int rezultat = 0;
		
//		if (this.id > that.id) rezultat =  1;
//		if (this.id < that.id) rezultat = -1;
		
		rezultat = this.autor.compareTo(that.autor);
		
		if (rezultat == 0)
			rezultat = this.naslov.compareTo(that.naslov);
		
		return rezultat;
	}

	public String toString() {
		return id + " " + autor + ": " + naslov;
	}

	public int getId() {
		return id;
	}

	public String getNaslov() {
		return naslov;
	}

	public String getAutor() {
		return autor;
	}
}
