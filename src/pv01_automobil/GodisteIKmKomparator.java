package pv01_automobil;

import java.util.Comparator;

public class GodisteIKmKomparator implements Comparator<Automobil> {

	public int compare(Automobil a1, Automobil a2) {
		
		// poredimo automobile prvo po godistu
		// vece godiste je bolje u ovom slucaju
		int rezultat = a2.godiste - a1.godiste;
		
		// ako su godista ista, poredimo kilometrazu
		// manje predjenih kilometara je bolje
		if (rezultat == 0)
			rezultat = a1.getKilometraza() - a2.getKilometraza();
			
		return rezultat;
	}
}
