package pv07_stablo_osoba_p03;

import java.util.ArrayList;
import java.util.List;

import pv07_stablo_osoba_p03.StabloOsoba.Cvor;

public class PosetilacLista implements IPoseti {
	
	Osoba o;
	List<Osoba> l;
	
	public PosetilacLista() {
		this.l = new ArrayList<>();
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		l.add(cvor.osoba);
	}
	
	public List<Osoba> sviIspodKorena() {
		l.remove(0);
		return l;
	}
}
