package pv07_stablo_automobila_p02;

import java.util.ArrayList;
import java.util.List;

import pv07_stablo_automobila_p02.Stablo.Cvor;

public class PosetilacListaPlavih implements IPoseti {
	
	List<Automobil> l = new ArrayList<Automobil>();

	@Override
	public void posetiCvor(Cvor cvor) {
		
		long kmRoditelja = cvor.info.getKilometraza();
		long kmLevog = -1, kmDesnog = -1;
		
		if (cvor.levo != null)
			kmLevog = cvor.levo.info.getKilometraza();
		
		if (cvor.desno != null)
			kmDesnog = cvor.desno.info.getKilometraza();
		
		if (cvor.levo != null && cvor.levo.info.getBoja().equalsIgnoreCase("plava"))
			if (kmLevog > kmRoditelja && kmLevog > kmDesnog)
				l.add(cvor.levo.info);
		
		if (cvor.desno != null && cvor.desno.info.getBoja().equalsIgnoreCase("plava"))
			if (kmDesnog > kmRoditelja && kmDesnog > kmLevog)
				l.add(cvor.desno.info);
	}
}
