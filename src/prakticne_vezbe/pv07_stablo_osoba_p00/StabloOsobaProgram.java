package prakticne_vezbe.pv07_stablo_osoba_p00;

import org.svetovid.Svetovid;

import java.util.Comparator;
import java.util.List;

// Konkretno stablo koje sadrzi ocene

// Glavna klasa
public class StabloOsobaProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<StabloOsoba> io = new TreeIO<>(StabloOsoba.class);

		// Procitamo stablo iz fajla
		StabloOsoba stablo = io.read(Svetovid.in("res/OsobeM.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);
	}
}