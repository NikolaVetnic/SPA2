package pv07_stablo_automobila_p02;

import java.util.ArrayList;
import java.util.List;

import pv07_stablo_automobila_p02.Stablo.Cvor;

public class PosetiNS implements IPoseti {

	List<Automobil> l = new ArrayList<Automobil>();
	
	@Override
	public void posetiCvor(Cvor cvor) {
		String grad = cvor.info.getRegistracija().substring(0, 2);
		if (grad.equalsIgnoreCase("ns"))
			l.add(cvor.info);
	}
}
