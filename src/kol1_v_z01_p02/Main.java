package kol1_v_z01_p02;

import java.io.IOException;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Raspored r = new Raspored("kol1_v_z01_raspored");
		
		KompozitniKomparator k0 = new KompozitniKomparator(
				new KIme(), false);
		
		KompozitniKomparator k1 = new KompozitniKomparator(
				new KGod(), new KDan(), new KHPoc(), false);
		
		KompozitniKomparator k2 = new KompozitniKomparator(
				new KSala(), new KDan(), new KHPoc(), true);
		
		KompozitniKomparator k3 = new KompozitniKomparator(
				new KDuzina(), false);
		
		Sort.bubble(r.predmeti, k0);
		r.snimi("kol1_v_z01_raspored_k0");
		
		Sort.insertion(r.predmeti, k1);
		r.snimi("kol1_v_z01_raspored_k1");
		
		Sort.selection(r.predmeti, k2);
		r.snimi("kol1_v_z01_raspored_k2");
		
		Sort.bubble(r.predmeti, k3);
		r.snimi("kol1_v_z01_raspored_k3");
		
		r.stampaj();
	}
}

class KompozitniKomparator implements Comparator<Predmet> {
	
	private final Comparator<Predmet> prim, sknd, tert;
	private final boolean rvrs;
	
	public KompozitniKomparator(
			Comparator<Predmet> prim, boolean rvrs) {
		this.prim = prim;
		this.sknd = null;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Predmet> prim, 
			Comparator<Predmet> sknd, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = null;
		this.rvrs = rvrs;
	}

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
		
		int out = 0;
		
			if (rvrs) 	out = prim.compare(p2, p1);
			else		out = prim.compare(p1, p2);
			
		if (sknd != null && out == 0)
			if (rvrs) 	out = sknd.compare(p2, p1);
			else		out = sknd.compare(p1, p2);
		
		if (tert != null && out == 0)
			if (rvrs) 	out = tert.compare(p2, p1);
			else		out = tert.compare(p1, p2);
		
		return out;
	}
}

class KDan implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.dan() - p2.dan();
	}
}

class KHPoc implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.hPoc() - p2.hPoc();
	}
}

class KSala implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.sala().compareTo(p2.sala());
	}
}

class KGod implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.god() - p2.god();
	}
}

class KIme implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.ime().compareTo(p2.ime());
	}
}

class KDuzina implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return (p1.hKrj() - p1.hPoc()) - (p2.hKrj() - p2.hPoc());
	}
}