package kolokvijumi.kol1_v_z01_p03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	
	public static void bubble(Predmet[] arr, Comparator<Predmet> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exch = false;
			
			for (int j = 0; j < i; j++)
				if (c.compare(arr[j], arr[j+1]) > 0) {
					Predmet tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					exch = true;
				}
			
			if (!exch) break;
		}
	}
	
	public static void insertion(Predmet[] arr, Comparator<Predmet> c) {
		
		int idx = 0;
		for (int i = 1; i < arr.length; i++)
			idx = c.compare(arr[i], arr[idx]) < 0 ? i : idx;
		
		if (idx != 0) {
			Predmet tmp = arr[idx];
			arr[idx] = arr[0];
			arr[0] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			
			Predmet current = arr[i];
			int j = i - 1;
			
			while (c.compare(arr[j], current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
	
	public static void selection(Predmet[] arr, Comparator<Predmet> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			for (int j = 0; j <= i; j++)
				idx = c.compare(arr[j], arr[idx]) > 0 ? j : idx;
				
			if (idx != i) {
				Predmet tmp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = tmp;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		Raspored r = new Raspored("kol1_v_z01_raspored");
		
		Arrays.sort(r.predmeti);	// prirodno uredjenje
		
		KompozitniKomparator[] k = new KompozitniKomparator[4];
		
		k[0] = new KompozitniKomparator(
				new KIme(), false);	// po imenu (rastuce)
		
		k[1] = new KompozitniKomparator(
				new KGod(), new KDan(), 
				new KPoc(), false);	// po godini, danu, pocetku (rastuce)
		
		k[2] = new KompozitniKomparator(
				new KMst(), new KDan(), 
				new KPoc(), true);	// po godini, danu, pocetku (opadajuce)
		
		k[3] = new KompozitniKomparator(
				new KDuz(), false);	// po duzini predavanja (rastuce)
		
		for (int i = 0; i < k.length; i++) {
			bubble(r.predmeti, k[i]);
			r.snimi("kol_v_z01_raspored_bubble_k" + i);
		}

		for (int i = 0; i < k.length; i++) {
			insertion(r.predmeti, k[i]);
			r.snimi("kol_v_z01_raspored_insertion_k" + i);
		}

		for (int i = 0; i < k.length; i++) {
			selection(r.predmeti, k[i]);
			r.snimi("kol_v_z01_raspored_selection_k" + i);
		}
	}
}

class KompozitniKomparator implements Comparator<Predmet> {
	
	private final Comparator<Predmet> prim, sknd, tert;
	private final boolean rvrs;
	
	public KompozitniKomparator(
			Comparator<Predmet> prim, boolean rvrs) {
		this.prim = prim;
		this.sknd = null;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Predmet> prim, 
			Comparator<Predmet> sknd, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Predmet> prim, 
			Comparator<Predmet> sknd, 
			Comparator<Predmet> tert, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = tert;
		this.rvrs = rvrs;
	}

	@Override
	public int compare(Predmet p1, Predmet p2) {
		
		int out = 0;
		
			if (rvrs)	out = prim.compare(p2, p1);
			else		out = prim.compare(p1, p2);
				
		if (sknd != null && out == 0)
			if (rvrs)	out = sknd.compare(p2, p1);
			else		out = sknd.compare(p1, p2);
		
		if (tert != null && out == 0)
			if (rvrs)	out = tert.compare(p2, p1);
			else		out = tert.compare(p1, p2);
		
		return out;
	}
}

class KDan implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.dan() - p2.dan();
	}
}

class KPoc implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.poc() - p2.poc();
	}
}

class KMst implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.mst().compareTo(p2.mst());
	}
}

class KGod implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.god() - p2.god();
	}
}

class KIme implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return p1.ime().compareTo(p2.ime());
	}
}

class KDuz implements Comparator<Predmet> {

	@Override
	public int compare(Predmet p1, Predmet p2) {
		return (p1.krj() - p1.poc()) - (p2.krj() - p2.poc()); 
	}
}