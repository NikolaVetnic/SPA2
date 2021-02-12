package kolokvijumi.kol1_v_z01_p01;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	
	public static void sortiraj(Predmet[] predmeti, Comparator<Predmet> c) {
		
		for (int j = predmeti.length - 1; j > 0; j--) {
			for (int i = 0; i < j; i++) {
				if (c.compare(predmeti[i], predmeti[j]) > 0) {
					Predmet tmp = predmeti[i];
					predmeti[i] = predmeti[j];
					predmeti[j] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		Raspored r = Raspored.ucitaj("kol1_v_z01_raspored");
		r.stampaj();
		
		System.out.println();
		
		int op = 0;
		
		switch (op) {
		
			case 0:
				System.out.println("Sortiranje po prirodnom poretku (po danima, satima, salama): ");
				Arrays.sort(r.predmeti()); 
				break;
				
			case 1:
				System.out.println("Sortiranje po godini studija, danu u nedelji, pocetku predavanja: ");
				sortiraj(r.predmeti(), 
						new KompozitniKomparator(
								new KomparatorPoGodini(),
								new KomparatorPoDanu(),
								new KomparatorPoPocetku(), false));
				break;
				
			case 2:
				System.out.println("Sortiranje po salama, danu u nedelji, pocetku predavanja (opadajuce): ");
				sortiraj(r.predmeti(), 
						new KompozitniKomparator(
								new KomparatorPoSalama(),
								new KomparatorPoDanu(),
								new KomparatorPoPocetku(), true));
				break;
				
			case 3:
				System.out.println("Sortiranje po duzini predavanja: ");
				sortiraj(r.predmeti(),
						new KomparatorPoDuziniPredavanja());
				break;
				
			default:
				System.out.println("Pogresan unos.");
				break;
		}
		
		if (0 <= op && op <= 3) {
			
			r.stampaj();
			r.snimi("kol1_v_z01_raspored_mod");
		}
	}
}

class KompozitniKomparator implements Comparator<Predmet> {
	
	private final Comparator<Predmet> prim, sknd, tert;
	private boolean rvrs;
	
	public KompozitniKomparator(
			Comparator<Predmet> prim, 
			Comparator<Predmet> sknd, 
			Comparator<Predmet> tert, boolean rvrs) {
		
		this.prim = prim;
		this.sknd = sknd;
		this.tert = tert;
		this.rvrs = rvrs;
	}

	@Override
	public int compare(Predmet p1, Predmet p2) {
		
		int out = rvrs ? prim.compare(p2, p1) : prim.compare(p1, p2);
		
		if (out == 0)
			out = rvrs ? sknd.compare(p2, p1) : sknd.compare(p1, p2);
			
		if (out == 0)
			out = rvrs ? tert.compare(p2, p1) : tert.compare(p1, p2);
			
		return out;
	}
}

class KomparatorPoDanu implements Comparator<Predmet> {
	@Override
	public int compare(Predmet p1, Predmet p2) { return p1.dan() - p2.dan(); 			}
}

class KomparatorPoPocetku implements Comparator<Predmet> {
	@Override
	public int compare(Predmet p1, Predmet p2) { return p1.hPocetak() - p2.hPocetak(); 	}
}

class KomparatorPoDuziniPredavanja implements Comparator<Predmet> {
	@Override
	public int compare(Predmet p1, Predmet p2) { 
		return (p1.hKraj() - p1.hPocetak()) - (p2.hKraj() - p2.hPocetak()); 
	}
}

class KomparatorPoSalama implements Comparator<Predmet> {
	@Override
	public int compare(Predmet p1, Predmet p2) { return p1.sala().compareTo(p2.sala()); }
}

class KomparatorPoGodini implements Comparator<Predmet> {
	@Override
	public int compare(Predmet p1, Predmet p2) { return p1.god() - p2.god(); 			}
}

class KomparatorPoImenu implements Comparator<Predmet> {
	@Override
	public int compare(Predmet p1, Predmet p2) { return p1.ime().compareTo(p2.ime()); 	}
}
