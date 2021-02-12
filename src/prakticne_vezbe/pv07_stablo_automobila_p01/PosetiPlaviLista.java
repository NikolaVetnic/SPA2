package prakticne_vezbe.pv07_stablo_automobila_p01;

import java.util.ArrayList;
import java.util.List;

import prakticne_vezbe.pv07_stablo_automobila_p01.Stablo.Cvor;

public class PosetiPlaviLista implements IPoseti {
	
	List<Automobil> l = new ArrayList<Automobil>();
	
	public List<Automobil> l() {
		l.sort((Automobil a0, Automobil a1) -> (int) (a0.getKilometraza() - a1.getKilometraza()));
		return l; 
	}

	@Override
	public void posetiCvor(Cvor cvor) {
		
		Cvor roditelj = cvor;
		Cvor[] sinovi = { cvor.levo, cvor.desno };
		
		for (int i = 0; i < sinovi.length; i++)
			if (sinovi[i] != null)
				if (sinovi[i].info.getBoja().equalsIgnoreCase("plava"))
					if (sinovi[i].info.getKilometraza() > roditelj.info.getKilometraza())
						if (sinovi[(i + 1) % sinovi.length] == null) {
							l.add(sinovi[i].info);
							return;
						} else
							if (sinovi[i].info.getKilometraza() > sinovi[(i + 1) % sinovi.length].info.getKilometraza()) {
								l.add(sinovi[i].info);
								return;
							}
	}
}
