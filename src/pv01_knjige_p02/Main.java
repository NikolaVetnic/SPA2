package pv01_knjige_p02;

import java.io.IOException;
import java.util.Comparator;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Biblioteka b = new Biblioteka("knjige146");
		
		KompozitniKomparator k0 = new KompozitniKomparator(
				new KomparatorID(), false);
		
		KompozitniKomparator k1 = new KompozitniKomparator(
				new KomparatorPisac(), 
				new KomparatorNaslov(), true);
		
		KompozitniKomparator k2 = new KompozitniKomparator(
				new KomparatorPisac(), 
				new KomparatorID(), false);
		
		Sort s = new Sort();
		
		s.bubble(b.knjige, k0);
		b.stampaj();
		
		s.insertion(b.knjige, k1);
		b.stampaj();
		
		s.selection(b.knjige, k2);
		b.stampaj();
	}
}

class KompozitniKomparator implements Comparator<Knjiga> {
	
	private final Comparator<Knjiga> prim, sknd, tert;
    private final boolean rvrs;
	
	public KompozitniKomparator(
			Comparator<Knjiga> prim, boolean rvrs) {
		this.prim = prim;
		this.sknd = null;
		this.tert = null;
		
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Knjiga> prim,
			Comparator<Knjiga> sknd, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = null;

		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Knjiga> prim,
			Comparator<Knjiga> sknd,
			Comparator<Knjiga> tert, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = tert;

		this.rvrs = rvrs;
	}
	
	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		
		int out = 0;
		
		if (rvrs)	out = prim.compare(k2, k1);
		else		out = prim.compare(k1, k2);
		
		if (sknd != null && out == 0)
			if (rvrs)	out = sknd.compare(k2, k1);
			else		out = sknd.compare(k1, k2);
		
		if (tert != null && out == 0)
			if (rvrs)	out = tert.compare(k2, k1);
			else		out = tert.compare(k1, k2);
		
		return out;
	}
}

class KomparatorID implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.id() - k2.id();
	}
}

class KomparatorNaslov implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.naslov().compareTo(k2.naslov());
	}
}

class KomparatorPisac implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.pisac().compareTo(k2.pisac());
	}
}