package kolokvijumi.kol1_v_z02_p03;

import org.svetovid.io.SvetovidReader;

import java.util.Arrays;

/**
 * Gadjanje mete
 * 
 * Prosiruje InfoTip samo zbog jednostavnosti u TestHash, nema razloga inace.
 */
public class Gadjanje extends InfoTip {
	private int[] rezultati;

	public Gadjanje(int brGadjanja) {
		this.rezultati = new int[brGadjanja];
	}

	public Gadjanje() {
		// prazan konstruktor potreban za test program
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Gadjanje gadjanje = (Gadjanje) o;
		return Arrays.equals(rezultati, gadjanje.rezultati);
	}

	@Override
	public int hashCode() {
		
//		if (rezultati == null)
//			return 0;
		
		int out = rezultati.length;
		for (int r : rezultati) out = (out * 11) + r;
		
		return out;
	}


	@Override
	public InfoTip ucitaj(SvetovidReader r) {
		
		int brGadjanja = r.readInt();
		Gadjanje rez = new Gadjanje(brGadjanja);
		
		for (int i = 0; i < brGadjanja; i++)
			rez.rezultati[i] = r.readInt();
		
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
		new TestHash(new Gadjanje(), "res/", "t").run();
	}
}
