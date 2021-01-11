package pv07_stablo_osoba_p03;

import java.util.Objects;

import pv07_stablo_osoba_p03.StabloOsoba.Cvor;

public class PosetilacOsoba implements IPoseti {
	
	Osoba o;
	StabloOsoba s;
	
	public PosetilacOsoba(Osoba o) {
		this.o = o;
		this.s = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (Objects.equals(o, cvor.osoba))
			this.s = new StabloOsoba(cvor);
	}
}
