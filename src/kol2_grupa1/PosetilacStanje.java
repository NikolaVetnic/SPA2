package kol2_grupa1;

import kol2_grupa1.Stablo.Cvor;

public class PosetilacStanje implements IPoseti {
	
	int nemaNaStanju;
	
	public PosetilacStanje() {
		this.nemaNaStanju = 0;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (cvor.info.getKolicina() == 0)
			nemaNaStanju++;
	}

}
