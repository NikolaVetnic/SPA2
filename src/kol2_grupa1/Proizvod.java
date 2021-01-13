package kol2_grupa1;

import java.util.Objects;

// Tip podataka koji predstavlja jednu vrstu proizvoda u supermarketu
// Jednom napravljen objekat ovog tipa nije moguce menjati
public final class Proizvod {

	// Naziv proizvida, npr. "Cajna kobasica"
	private final String naziv;

	// Koliko kosta jedinica mere datog proizvoda, npr. 349,99
	private final double cena;

	// Da li je proizvod trenutno na akciji, npr. true
	private final boolean akcija;

	// Koliko jedinica mere tog proizvoda ima trenutno na lageru, npr. 7
	private final int kolicina;

	// Jedini konstruktor pomocu kojeg je moguce napraviti patuljka
	public Proizvod(String naziv, double cena, boolean akcija, int kolicina) {
		if (naziv == null) {
			throw new IllegalArgumentException("naziv");
		}
		this.naziv = naziv;
		this.cena = cena;
		this.akcija = akcija;
		this.kolicina = kolicina;
	}

	public String getNaziv() {
		return naziv;
	}

	public double getCena() {
		return cena;
	}

	public boolean isAkcija() {
		return akcija;
	}

	public int getKolicina() {
		return kolicina;
	}

	@Override
	public int hashCode() {
		final int prost = 31;
		int rezultat = 1;
		rezultat = prost * rezultat + naziv.hashCode();
		long temp = Double.doubleToLongBits(cena);
		rezultat = prost * rezultat + (int) (temp ^ (temp >>> 32));
		rezultat = prost * rezultat + (akcija ? 1231 : 1237);
		rezultat = prost * rezultat + kolicina;
		return rezultat;
	}

	// Dva proizvoda su jednaka ako su im jednaka sva polja
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Proizvod that = (Proizvod) obj;
		if (!Objects.equals(this.naziv,  that.naziv)) {
			return false;
		}
		if (this.cena != that.cena) {
			return false;
		}
		if (this.akcija != that.akcija) {
			return false;
		}
		if (this.kolicina != that.kolicina) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return naziv + ";" + cena + ";" + akcija + ";" + kolicina;
	}

	public static Proizvod fromString(String string) {
		if (string == null) {
			return null;
		}
		String[] delovi = string.split(";");
		return new Proizvod(delovi[0], Double.parseDouble(delovi[1]), "true".equals(delovi[2]), Integer.parseInt(delovi[3]));
	}
}
