package pv07_stablo_automobila_p02;

import java.util.Comparator;

import pv07_stablo_automobila_p02.Stablo.Cvor;

public class PosetilacBST implements IPoseti {

	boolean jeBST;
	Comparator<Automobil> c;
	
	public PosetilacBST() {
		this.jeBST = true;
		this.c = (Automobil a1, Automobil a2) -> (int) (a1.getKilometraza() - a2.getKilometraza());
	}
	
	@Override
	public void posetiCvor(Cvor cvor) {
		
		int cvorVeciOdLevog = -1, cvorManjiOdDesnog = -1;

		if (cvor.levo != null)
			if (c.compare(cvor.info, cvor.levo.info) >= 0)
				cvorVeciOdLevog = 1;
			else
				cvorVeciOdLevog = 0;
		
		if (cvor.desno != null) 
			if (c.compare(cvor.info, cvor.desno.info) < 0)
				cvorManjiOdDesnog = 1;
			else
				cvorManjiOdDesnog = 0;
		
		if (cvorVeciOdLevog == 0 || cvorManjiOdDesnog == 0)
			this.jeBST = false;
	}
}
