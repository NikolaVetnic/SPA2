package kolokvijumi.kol2_grupa1;

import java.util.Comparator;

import kolokvijumi.kol2_grupa1.Stablo.Cvor;

public class PosetilacProizvod implements IPoseti {
	
	Proizvod p1, p2;
	
	public PosetilacProizvod() {
		this.p1 = null;
		this.p2 = null;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		
		if (p1 == null) {
			p1 = cvor.info;
		} else if (p1 != null && p2 == null) {
			if (p1.getCena() - cvor.info.getCena() < 0) {
				p2 = p1;
				p1 = cvor.info;
			} else {
				p2 = cvor.info;
			}
		} else {
			if (p1.getCena() - cvor.info.getCena() < 0) {
				p2 = p1;
				p1 = cvor.info;
			} else {
				if (p2.getCena() - cvor.info.getCena() < 0) {
					p2 = cvor.info;
				}
			}
		}
	}

}
