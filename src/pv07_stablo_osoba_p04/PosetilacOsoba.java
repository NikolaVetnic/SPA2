package pv07_stablo_osoba_p04;

import java.util.Objects;

import pv07_stablo_osoba_p04.StabloOsoba.Cvor;

public class PosetilacOsoba implements IPoseti {
	
	Osoba o;
	StabloOsoba s;
	
	public PosetilacOsoba(Osoba osoba) {
		this.o = osoba;
		this.s = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (Objects.equals(o, cvor.osoba))
			this.s = new StabloOsoba(cvor);
	}
}
