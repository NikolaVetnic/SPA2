package prakticne_vezbe.pv07_stablo_osoba_p03;

import java.util.Objects;

import prakticne_vezbe.pv07_stablo_osoba_p03.StabloOsoba.Cvor;

public class PosetilacRoditelj implements IPoseti {
	
	Osoba potomak;
	Osoba roditelj;
	
	public PosetilacRoditelj(Osoba potomak) {
		this.potomak = potomak;
		this.roditelj = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (Objects.equals(cvor.levo.osoba, potomak) || Objects.equals(cvor.desno.osoba, potomak))
			this.roditelj = cvor.osoba;
	}
}
