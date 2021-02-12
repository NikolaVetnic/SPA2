package prakticne_vezbe.pv07_stablo_osoba_p02;

import java.util.Comparator;

import prakticne_vezbe.pv07_stablo_osoba_p02.StabloOsoba.Cvor;

public class PosetilacKomparator implements IPoseti {
	
	Comparator<Osoba> c;
	Osoba o;
	
	public PosetilacKomparator(Comparator<Osoba> c) {
		this.c = c;
		this.o = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		
		if (this.o == null)
			this.o = cvor.osoba;
		else 
			if (c.compare(cvor.osoba, o) >= 0)
				this.o = cvor.osoba;
	}
}
