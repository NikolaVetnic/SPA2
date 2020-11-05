package pv02_knjige;

import java.util.Comparator;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidWriter;

public class Biblioteka {
	
	public static void main(String[] args) {
		
		String imeFajla;
		int opcija = 0;
		
		imeFajla = Svetovid.in.readLine("Unesite ime fajla: ");
		Knjiga[] knjige = ucitajKnjige(imeFajla);
		System.out.println();
		
		System.out.println("Nesortiran niz: ");
		stampajNiz(knjige);
		System.out.println();
		
		System.out.println("Odaberite opciju za sortiranje: ");
		System.out.println("[0] ID (Comparator)");
		System.out.println("[1] ID (lambda f-ja)");
		System.out.println("[2] Naslov");
		System.out.println("[3] Autor");
		System.out.println("[4] Autor/Naslov");
		opcija = Svetovid.in.readInt("Unesite izbor: ");
		System.out.println();
		
		switch (opcija) {
			
			case 0:		sort(knjige, new PrirodniKomparator());		break;
			case 1:		Comparator<Knjiga> PrirKompLambda = 
						(k1, k2) -> k1.getId() - k2.getId();
						sort(knjige, PrirKompLambda);				break;
			case 2:		sort(knjige, new KomparatorPoNaslovu());	break;
			case 3:		sort(knjige, new KomparatorPoAutoru());		break;
			case 4:		sort(knjige, new KompozitniKomparator(
								new KomparatorPoAutoru(), 
								new KomparatorPoNaslovu()));		break;
			default: 	System.out.println("Pogresna opcija.");		break;
		}
		
		stampajNiz(knjige);
		System.out.println();
		
		imeFajla = Svetovid.in.readLine("Unesite ime za cuvanje fajla: ");
		snimiKnjige(knjige, imeFajla);
	}
	
	public static Knjiga[] ucitajKnjige(String input) {
		
		String imeFajla = "res//" + input + ".txt";
		
		if (!Svetovid.testIn(imeFajla))
			return null;
		
		int n = Svetovid.in(imeFajla).readInt();
		
		Knjiga[] knjige = new Knjiga[n];
		
		for (int i = 0; i < n; i++) {
			
			int id = Svetovid.in(imeFajla).readInt();
			String naslov = Svetovid.in(imeFajla).readLine();
			String autor = Svetovid.in(imeFajla).readLine();
			
			knjige[i] = new Knjiga(id, naslov, autor);
		}
		
		Svetovid.closeIn(imeFajla);
		
		return knjige;
	}
	
	public static void snimiKnjige(Knjiga[] niz, String input) {
		
		String imeFajla = "res//" + input + ".txt";
		
		if (!Svetovid.testOut(imeFajla)) {
			
			System.out.println("Nemoguce je kreirati fajl.");
			return;
		}
		
		SvetovidWriter out = Svetovid.out(imeFajla);
		
		out.println(niz.length);
		
		for (int i = 0; i < niz.length; i++) {
			
			out.println(niz[i].getId());
			out.println(niz[i].getNaslov());
			out.println(niz[i].getAutor());
		}
		
		out.close();
	}
	
	public static void stampajNiz(Knjiga[] niz) {
		
		for (Knjiga k: niz)
			System.out.println(k);
	}
	
	public static void sort(Knjiga[] niz, Comparator<Knjiga> c) {
		
		Knjiga tmp;
		
		for(int i = niz.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (c.compare(niz[j], niz[i]) > 0) {
					tmp = niz[j];
					niz[j] = niz[i];
					niz[i] = tmp;
				}
			}
		}
	}
}

class PrirodniKomparator implements Comparator<Knjiga> {
	
	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.getId() - k2.getId();
	}
}

class KomparatorPoNaslovu implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.getNaslov().compareTo(k2.getNaslov());
	}
}

class KomparatorPoAutoru implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.getAutor().compareTo(k2.getAutor());
	}
}

class KompozitniKomparator implements Comparator<Knjiga> {

	private final Comparator<Knjiga> prim;
	private final Comparator<Knjiga> sknd;
	
	public KompozitniKomparator(Comparator<Knjiga> prim, Comparator<Knjiga> sknd) {
		this.prim = prim;
		this.sknd = sknd;
	}

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		
		return prim.compare(k1, k2) != 0 ? prim.compare(k1, k2) : sknd.compare(k1, k2);
	}
}