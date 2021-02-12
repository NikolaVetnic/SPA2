package prakticne_vezbe.pv07_stablo_osoba_p02;

import com.google.common.base.Objects;

import prakticne_vezbe.pv07_stablo_osoba_p02.StabloOsoba.Cvor;

public class PosetilacOsoba implements IPoseti {
	
	Osoba o;
	StabloOsoba rez;
	
	public PosetilacOsoba(Osoba o) {
		this.o = o;
		this.rez = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (Objects.equal(cvor.osoba, o))
			rez = new StabloOsoba(cvor);
	}
}
