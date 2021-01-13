package pv07_stablo_osoba_p04;

import java.util.Comparator;

import pv07_stablo_osoba_p04.StabloOsoba.Cvor;

public class PosetilacKomparator implements IPoseti {
	
	Comparator<Osoba> c;
	Osoba max;
	
	public PosetilacKomparator(Comparator<Osoba> c) {
		this.c = c;
		this.max = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (this.max == null || c.compare(cvor.osoba, max) >= 0)
			max = cvor.osoba;
	}
}
