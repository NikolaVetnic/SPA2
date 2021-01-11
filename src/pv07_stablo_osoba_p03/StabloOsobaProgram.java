package pv07_stablo_osoba_p03;

import java.util.List;

import org.svetovid.Svetovid;

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

		stablo.stampajPostOrder(stablo.koren);
		System.out.println();
		
		stablo.stampajListove(stablo.koren);
		System.out.println();
		
		StabloOsoba s0 = stablo.pronadji(new Osoba("Raja", "Rajkovic", 31985));
		System.out.println("Podstablo sa pocetkom od osobe: " + new Osoba("Raja", "Rajkovic", 31985));
		if (s0 != null) io.print(Svetovid.out, s0);
		System.out.println();
		
		List<Osoba> l = null;
		if (s0 != null) l = stablo.stampajSveIspod(new Osoba("Raja", "Rajkovic", 31985));
		System.out.println("Stampa svih cvorova ispod osobe: " + new Osoba("Raja", "Rajkovic", 31985));
		System.out.println(l + "\n");
		
		if (stablo.pronadji(new Osoba("Toma", "Nikolic", 100)) != null) {
			System.out.println("Ubacujemo osobu: " + new Osoba("Radomir", "Nikolic", 300));
			stablo.ubaci(new Osoba("Toma", "Nikolic", 100), new Osoba("Radomir", "Nikolic", 300), false);
			io.print(Svetovid.out, stablo);
			System.out.println();
		}
		
		System.out.println("Roditelj osobe '" + new Osoba("Raja", "Rajkovic", 31985) + "' : " + stablo.roditeljOd(new Osoba("Raja", "Rajkovic", 31985)));
		System.out.println();
		
		System.out.println("Optimalna osoba: " + 
				stablo.optimalnaOsoba((Osoba o1, Osoba o2) -> o1.getPlata() - o2.getPlata(), stablo.koren));
		System.out.println();
		
		System.out.println("Kopija stabla: ");
		StabloOsoba k = stablo.kopija();
		io.print(Svetovid.out, k);
		System.out.println();
		
		System.out.println("Inverzija stabla: ");
		StabloOsoba o = stablo.obrnuto();
		io.print(Svetovid.out, o);
		System.out.println();
		
		System.out.println("Druga po redu sa najvecom platom: " + stablo.drugaPoReduSaNajvecomPlatom());
		System.out.println();
	}
}