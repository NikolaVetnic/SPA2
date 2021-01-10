package pv07_stablo_osoba_p02;

import com.google.common.base.Objects;

import pv07_stablo_osoba_p02.StabloOsoba.Cvor;

public class PosetilacRoditelj implements IPoseti {
	
	Osoba o;
	Osoba roditelj;
	
	public PosetilacRoditelj(Osoba o) {
		this.o = o;
		this.roditelj = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		
		if (cvor.levo != null && Objects.equal(cvor.levo.osoba, o))
			this.roditelj = cvor.osoba;
		
		if (cvor.desno != null && Objects.equal(cvor.desno.osoba, o))
			this.roditelj = cvor.osoba;
	}
}
