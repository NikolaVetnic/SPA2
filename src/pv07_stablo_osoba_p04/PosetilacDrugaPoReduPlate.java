package pv07_stablo_osoba_p04;

import java.util.Comparator;

import pv07_stablo_osoba_p04.StabloOsoba.Cvor;

public class PosetilacDrugaPoReduPlate implements IPoseti {
	
	Osoba o1, o2;
	Comparator<Osoba> c = (Osoba o1, Osoba o2) -> o1.getPlata() - o2.getPlata();;
	
	public PosetilacDrugaPoReduPlate() {
		this.o1 = null;
		this.o2 = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		
		if (o1 == null) {
			o1 = cvor.osoba;
		} else if (o1 != null && o2 == null) {
			if (c.compare(o1, cvor.osoba) < 0) {
				o2 = o1;
				o1 = cvor.osoba;
			} else {
				o2 = cvor.osoba;
			}
		} else {
			if (c.compare(o1, cvor.osoba) < 0) {
				o2 = o1;
				o1 = cvor.osoba;
			} else {
				if (c.compare(o2, cvor.osoba) < 0) {
					o2 = cvor.osoba;
				}
			}
		}
	}
}
