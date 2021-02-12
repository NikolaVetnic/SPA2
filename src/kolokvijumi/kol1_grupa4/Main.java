package kolokvijumi.kol1_grupa4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		String input;
		int op = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Fajl za ucitavanje: ");
		input = sc.nextLine();
		
		Spisak s = new Spisak(input);
		
		System.out.println("Odaberite sortiranje: ");
		System.out.println("[1] prirodno uredjenje ");
		System.out.println("[2] po valuti i srednjem kursu (opadajuce)");
		System.out.println("[3] po apsolutnoj razlici izmedju prodajnog i kupovnog kursa");
		System.out.println("[4] po apsolutnoj vrednosti povecanja/smanjenja (u procentima)");
		System.out.print("Izbor: ");
		op = Integer.parseInt(sc.nextLine());
		
//		KompozitniKomparator k1 = new KompozitniKomparator(
//				new KKup(), false);						// prirodno uredjenje, kao kompozitni komparator
		
		KompozitniKomparator k2 = new KompozitniKomparator(
				new KOzn(), new KSrdOpd(), false);		// po valuti (rastuce) i srednjem kursu (opadajuce)
		
		KompozitniKomparator k3 = new KompozitniKomparator(
				new KRaz(), false);						// po apsolutnoj razlici prodajnog i kupovnog
		
		KompozitniKomparator k4 = new KompozitniKomparator(
				new KPov(), false);						// po apsolutnoj vrednosti povecanja/smanjenja
		
		// prema zahtevima zadatka odabrao sam selection sort
		switch (op) {
		
			case 1: Arrays.sort(s.liste);  		 break;
			case 2: Sort.selection(s.liste, k2); break;
			case 3: Sort.selection(s.liste, k3); break;
			case 4: Sort.selection(s.liste, k4); break;
			default: System.out.println("Pogresan izbor. Kraj rada."); break;
		}
	
		if (1 <= op && op <= 4) {
				
			s.stampaj();
			
			System.out.print("Fajl za snimanje: ");
			input = sc.nextLine();
			
			s.snimi(input);			
		}
		
		sc.close();
	}
}

class KompozitniKomparator implements Comparator<KursnaLista> {
	
	// opsti kompozitni komparator za do tri komparatora
	private final Comparator<KursnaLista> prim, sknd, tert;
	private final boolean rvrs;	// da li je obrnut (opadajuci) poredak
	
	public KompozitniKomparator(
			Comparator<KursnaLista> prim, boolean rvrs) {
		this.prim = prim;
		this.sknd = null;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<KursnaLista> prim, 
			Comparator<KursnaLista> sknd, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<KursnaLista> prim, 
			Comparator<KursnaLista> sknd, 
			Comparator<KursnaLista> tert, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = tert;
		this.rvrs = rvrs;
	}

	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		
		int out = 0;
		
			if (rvrs)	out = prim.compare(l2, l1);
			else		out = prim.compare(l1, l2);
			
		if (sknd != null && out == 0)
			if (rvrs)	out = sknd.compare(l2, l1);
			else		out = sknd.compare(l1, l2);
		
		if (tert != null && out == 0)
			if (rvrs)	out = tert.compare(l2, l1);
			else		out = tert.compare(l1, l2);
		
		return out;
	}
}

class KOzn implements Comparator<KursnaLista> {

	// poredjenje po oznaci valute
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		return l1.ozn().compareTo(l2.ozn());
	}	
}

class KKup implements Comparator<KursnaLista> {

	// poredjenje po kupovnom kursu
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		return l1.kup() - l2.kup();
	}	
}

class KSrd implements Comparator<KursnaLista> {

	// poredjenje po srednjem kursu
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		return l1.srd() - l2.srd();
	}	
}

class KSrdOpd implements Comparator<KursnaLista> {

	// poredjenje po srednjem kursu
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		return l2.srd() - l1.srd();
	}	
}

class KPro implements Comparator<KursnaLista> {

	// poredjenje po prodajnom kursu
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		return l1.pro() - l2.pro();
	}	
}

class KDan implements Comparator<KursnaLista> {

	// poredjenje po danu
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		return l1.dan() - l2.dan();
	}	
}

class KRaz implements Comparator<KursnaLista> {

	// poredjenje po apsolutnoj razlici izmedju prodajnog i kupovnog kursa
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		return Math.abs(l1.pro() - l1.kup()) - Math.abs(l2.pro() - l2.kup());
	}	
}

class KPov implements Comparator<KursnaLista> {

	// poredjenje po apsolutnoj vrednosti povecanja/smanjenja (u procentima)
	@Override
	public int compare(KursnaLista l1, KursnaLista l2) {
		
		double v1 = Math.abs(1.0 * l1.pro() - l1.kup()) / l1.pro() * 100;
		double v2 = Math.abs(1.0 * l2.pro() - l2.kup()) / l2.pro() * 100;
		
		int out = 0;
			out = v1 > v2 ? 1 : -1;
		
		return out;
	}	
}