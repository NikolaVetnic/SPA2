package kol1_v_z02;

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
		
		int out = 0;
				
		for (int i = 0; i < rezultati.length; i++)
			out += (rezultati[i] + 1) * (int) Math.pow(16, i);
		
		return out;
	}


	@Override
	public InfoTip ucitaj(SvetovidReader read) {
		
		int brGadjanja = read.readInt();
		Gadjanje rez = new Gadjanje(brGadjanja);
		
		String[] tokens = read.readLine().split(" ");
		
		for (int i = 0; i < tokens.length; i++) {
			rez.rezultati[i] = Integer.parseInt(tokens[i].trim());
		}
		
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
