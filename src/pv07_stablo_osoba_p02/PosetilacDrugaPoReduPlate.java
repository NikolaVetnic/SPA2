package pv07_stablo_osoba_p02;

import pv07_stablo_osoba_p02.StabloOsoba.Cvor;

public class PosetilacDrugaPoReduPlate implements IPoseti {
	
	Osoba o1, o2;

	public PosetilacDrugaPoReduPlate() {
		this.o1 = null;
		this.o2 = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		
		if (o1 == null) {
			o1 = cvor.osoba;
		} else if (o1 != null && o2 == null) {
			if (o1.getPlata() - cvor.osoba.getPlata() >= 0) {
				o2 = cvor.osoba;
			} else {
				o2 = o1;
				o1 = cvor.osoba;
			}
		} else {
			if (cvor.osoba.getPlata() - o1.getPlata() >= 0) {
				o2 = o1;
				o1 = cvor.osoba;
			} else if (cvor.osoba.getPlata() - o2.getPlata() >= 0) {
				o2 = cvor.osoba;
			}
		}
	}
}
