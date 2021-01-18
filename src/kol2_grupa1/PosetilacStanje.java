package kol2_grupa1;

import java.util.ArrayList;
import java.util.List;

import kol2_grupa1.Stablo.Cvor;

public class PosetilacStanje implements IPoseti {
	
	List<Proizvod> nemaNaStanju; 
	
	public PosetilacStanje() {
		this.nemaNaStanju = new ArrayList<Proizvod>();
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (cvor.info.getKolicina() == 0) {
			if (!nemaNaStanju.contains(cvor.info))
				nemaNaStanju.add(cvor.info);
		}
	}

}
