package prakticne_vezbe.pv07_stablo_osoba_p02;

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
		System.out.println();
		
		stablo.stampajPreOrder(stablo.koren);
		System.out.println();
		
		stablo.stampajInOrder(stablo.koren);
		System.out.println();
		
		stablo.stampajListove(stablo.koren);
		System.out.println();
		
		StabloOsoba s0 = stablo.pronadji(new Osoba("Raja", "Rajkovic", 31985));
		if (s0 != null) io.print(Svetovid.out, s0);
		System.out.println();
		
		if (s0 != null) stablo.stampajSveIspod(new Osoba("Raja", "Rajkovic", 31985));
		System.out.println();
		
		if (stablo.pronadji(new Osoba("Toma", "Nikolic", 100)) != null) {
			stablo.ubaci(new Osoba("Toma", "Nikolic", 100), new Osoba("Radomir", "Nikolic", 300), false);
			io.print(Svetovid.out, stablo);
			System.out.println();
		}
		
		System.out.println(stablo.roditeljOd(new Osoba("Raja", "Rajkovic", 31985)));
		System.out.println();
		
		System.out.println(stablo.optimalnaOsoba((Osoba o1, Osoba o2) -> o1.getPlata() - o2.getPlata()));
		System.out.println();
		
		StabloOsoba k = stablo.kopija();
		io.print(Svetovid.out, k);
		System.out.println();
		
		StabloOsoba o = stablo.obrnuto();
		io.print(Svetovid.out, o);
		System.out.println();
		
		System.out.println(stablo.drugaPoReduSaNajvecomPlatom());
		System.out.println();
	}
}