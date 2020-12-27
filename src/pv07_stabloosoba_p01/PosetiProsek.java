package pv07_stabloosoba_p01;

import pv07_stabloosoba_p01.StabloOsoba.Cvor;

public class PosetiProsek implements IPoseti {

	private double suma;
	private int brCvorova;
	
	private Osoba o1, o2;
	
	public double prosecnaPlata() {
		
		if (brCvorova != 0)
			return suma / brCvorova;
		else
			return -1.0;
	}
	
	public void posetiCvor(Cvor cvor) {
		
		suma += cvor.osoba.getPlata();
		brCvorova++;

		pomocnaMetoda(cvor);
	}
	
	private void pomocnaMetoda(Cvor cvor) {

		if (o1 == null) { 
			o1 = cvor.osoba.kopiraj();
			return;
		}
		
		if (cvor.osoba.getPlata() > o1.getPlata()) {
			o2 = o1.kopiraj();
			o1 = cvor.osoba.kopiraj();
			return;
		} 
		
		if (o2 == null || cvor.osoba.getPlata() > o2.getPlata()) {
			o2 = cvor.osoba.kopiraj();
			return;
		}
	}

	public Osoba osobaSaNajvecomPlatom() 		{ return o1; }
	public Osoba osobaSaDrugomPoReduPlatom() 	{ return o2; }
}
