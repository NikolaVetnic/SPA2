package prakticne_vezbe.pv07_stablo_automobila_p02;

import prakticne_vezbe.pv07_stablo_automobila_p02.Stablo.Cvor;

public class PosetilacPlavi implements IPoseti {
	
	long sum;
	
	public PosetilacPlavi() {
		this.sum = 0;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (cvor.info.getBoja().equalsIgnoreCase("plava"))
			sum += cvor.info.getKilometraza();
	}
}
