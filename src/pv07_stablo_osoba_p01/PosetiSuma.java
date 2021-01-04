package pv07_stablo_osoba_p01;

import pv07_stablo_osoba_p01.StabloOsoba.Cvor;

public class PosetiSuma implements IPoseti {

	private double suma;
		
	public double sumaPlata() {
		return suma;
	}
	
	public void posetiCvor(Cvor cvor) {
		suma += cvor.osoba.getPlata();
	}
}
