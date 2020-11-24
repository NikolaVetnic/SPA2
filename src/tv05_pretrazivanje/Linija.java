package tv05_pretrazivanje;

import java.util.Objects;

//Tip podataka koji predstavlja jednu autobusku liniju
class Linija {

	private final String odGrada;
	private final String doGrada;
	private final double cenaKarte;

	public Linija(String odGrada, String doGrada, double cenaKarte) {

		// Ne dozvoljavamo null za grad
		if (odGrada == null) {
			throw new IllegalArgumentException("odGrada");
		}
		this.odGrada = odGrada;

		// Ne dozvoljavamo null za grad
		if (doGrada == null) {
			throw new IllegalArgumentException("doGrada");
		}
		this.doGrada = doGrada;

		// Ne dozvoljavamo negativne cene
		if (cenaKarte < 0.0) {
			throw new IllegalArgumentException("cena");
		}
		this.cenaKarte = cenaKarte;

	}

	public String getOdGrada() {
		return odGrada;
	}

	public String getDoGrada() {
		return doGrada;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	@Override
	public int hashCode() {
		final int prostBroj = 29;
		int rezultat = 1;
		long temp = Double.doubleToLongBits(cenaKarte);
		rezultat = prostBroj * rezultat + (int) (temp ^ (temp >>> 32));
		rezultat = prostBroj * rezultat + doGrada.hashCode();
		rezultat = prostBroj * rezultat + odGrada.hashCode();
		return rezultat;
	}

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
		Linija that = (Linija) obj;
		if (!Objects.equals(this.doGrada, that.doGrada)) {
			return false;
		}
		if (!Objects.equals(this.odGrada, that.odGrada)) {
			return false;
		}
		if (Double.compare(this.cenaKarte, that.cenaKarte) != 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return odGrada + " - " + doGrada + " (" + cenaKarte + ")";
	}
}
