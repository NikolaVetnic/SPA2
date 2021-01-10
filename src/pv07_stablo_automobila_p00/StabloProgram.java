package pv07_stablo_automobila_p00;

import org.svetovid.Svetovid;

class Stablo {
    private static class Cvor {
    	Automobil info;
    	Cvor levo;
    	Cvor desno;
    }

    private Cvor koren;

    // ------ dodati opisane metode ------------
    // ------ po potrebi i pomocne metode, konstruktore, klase i sl
    // ------ ne dodavati polja u klasu!
}

// Glavna klasa
public class StabloProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<Stablo> io = new TreeIO<>(Stablo.class);

		// Procitamo stablo iz fajla
		Stablo stablo = io.read(Svetovid.in("Malo.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		//Stablo stablo = io.read(Svetovid.in("Veliko.txt"));
		//Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);

		// -------- ovde dodati pozive metoda ----------
	}
}
