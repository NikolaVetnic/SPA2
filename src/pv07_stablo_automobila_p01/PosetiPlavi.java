package pv07_stablo_automobila_p01;

import pv07_stablo_automobila_p01.Stablo.Cvor;

public class PosetiPlavi implements IPoseti {
	
	private double kilometrazaPlavi;
	
	public double kilometrazaPlavi() {
		return kilometrazaPlavi;
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		if (cvor.info.getBoja().equalsIgnoreCase("plava"))
			kilometrazaPlavi += cvor.info.getKilometraza();
	}
}
