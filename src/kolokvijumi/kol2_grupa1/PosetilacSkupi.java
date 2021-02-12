package kolokvijumi.kol2_grupa1;

import kolokvijumi.kol2_grupa1.Stablo.Cvor;

public class PosetilacSkupi implements IPoseti {
	
	private static final int MAX_CENA = 1000;

	@Override
	public void posetiCvor(Cvor cvor) {
		if (cvor.info.getCena() > MAX_CENA)
			System.out.println(cvor.info);
	}

}
