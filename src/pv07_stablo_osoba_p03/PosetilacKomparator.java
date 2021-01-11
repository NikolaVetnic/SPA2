package pv07_stablo_osoba_p03;

import java.util.Comparator;

import pv07_stablo_osoba_p03.StabloOsoba.Cvor;

public class PosetilacKomparator implements IPoseti {
	
	Comparator c;
	Osoba o;

	public PosetilacKomparator(Comparator<Osoba> komparator) {
		this.c = komparator;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (o == null)
			o = cvor.osoba;
		else
			if (c.compare(o, cvor.osoba) < 0)
				o = cvor.osoba;
	}
}
