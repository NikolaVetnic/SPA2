package kol1_v_z02;

import org.svetovid.io.SvetovidReader;

import java.util.Arrays;

/**
 * Gadjanje mete
 * 
 * Prosiruje InfoTip samo zbog jednostavnosti u TestHash, nema razloga inace.
 */
public class GadjanjePrazna extends InfoTip {
	private int[] rezultati;

	public GadjanjePrazna() {
		// prazan konstruktor potreban za test program
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GadjanjePrazna gadjanje = (GadjanjePrazna) o;
		return Arrays.equals(rezultati, gadjanje.rezultati);
	}

	@Override
	public int hashCode() {
		return 1;
	}


	@Override
	public InfoTip ucitaj(SvetovidReader r) {
		GadjanjePrazna rez = new GadjanjePrazna();
		return rez;
	}

	public String toString() {
		String str = "Gadjanje (" + rezultati.length + "):";
		for (int i : rezultati) {
			str += " " + i;
		}
		return str;
	}

	// pomocni metod za lakse testiranje
	public static void main(String[] args) {
		new TestHash(new GadjanjePrazna(), "res/", "t").run();
	}
	
}
