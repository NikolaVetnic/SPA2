package pv07_stablo_automobila_p01;

import pv07_stablo_automobila_p01.Stablo.Cvor;

public class PosetiBST implements IPoseti {

	private boolean jeBST = true;
	
	public boolean jeBST() { return jeBST; }
	
	@Override
	public void posetiCvor(Cvor cvor) {
//		System.out.println(cvor.info);
		
		if (cvor.levo != null && cvor.info.getKilometraza() < cvor.levo.info.getKilometraza())
			jeBST = false;
		
		if (cvor.desno != null && cvor.info.getKilometraza() < cvor.desno.info.getKilometraza())
			jeBST = false;
	}

}
