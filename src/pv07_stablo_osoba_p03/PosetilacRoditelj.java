package pv07_stablo_osoba_p03;

import java.util.Objects;

import pv07_stablo_osoba_p03.StabloOsoba.Cvor;

public class PosetilacRoditelj implements IPoseti {
	
	Osoba potomak;
	Osoba roditelj;
	
	public PosetilacRoditelj(Osoba potomak) {
		this.potomak = potomak;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if ((cvor.levo != null && Objects.equals(cvor.levo.osoba, potomak)) || (cvor.desno != null && Objects.equals(cvor.desno.osoba, potomak)))
			roditelj = cvor.osoba;
	}

}
