package prakticne_vezbe.pv01_knjige_p03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	
	public static void bubble(Knjiga[] arr, Comparator<Knjiga> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exch = false;
			
			for (int j = 0; j < i; j++)
				if (c.compare(arr[j], arr[j+1]) > 0) {
					Knjiga tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					exch = true;
				}
			
			if (!exch) break;
		}
	}
	
	public static void insertion(Knjiga[] arr, Comparator<Knjiga> c) {
		
		int idx = 0;
		for (int i = 1; i < arr.length; i++)
			idx = c.compare(arr[i], arr[idx]) < 0 ? i : idx;
		
		if (idx != 0) {
			Knjiga tmp = arr[idx];
			arr[idx] = arr[0];
			arr[0] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			
			Knjiga current = arr[i];
			int j = i - 1;
			
			while (c.compare(arr[j], current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}

	public static void selection(Knjiga[] arr, Comparator<Knjiga> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			for (int j = 1; j <= i; j++)
				idx = c.compare(arr[j], arr[idx]) > 0 ? j : idx;
				
			if (idx != i) {
				Knjiga tmp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = tmp;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		Biblioteka b = new Biblioteka("knjige146");
		
		Arrays.sort(b.knjige);
		b.stampaj();
		
		KompozitniKomparator k = new KompozitniKomparator(
				new KPisac(), new KNaslov(), new KID(), false);
		
		bubble(b.knjige, k);
		insertion(b.knjige, k);
		selection(b.knjige, k);
		
		b.stampaj();
	}
}

class KompozitniKomparator implements Comparator<Knjiga> {
	
	private final Comparator<Knjiga> prim, sknd, tert;
	private final boolean rvrs;
	
	public KompozitniKomparator(
			Comparator<Knjiga> prim, boolean rvrs) {
		this.prim = prim;
		this.sknd = null;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Knjiga> prim, 
			Comparator<Knjiga> sknd, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Knjiga> prim, 
			Comparator<Knjiga> sknd, 
			Comparator<Knjiga> tert, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = tert;
		this.rvrs = rvrs;
	}

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		
		int out = 0;
		
			if (rvrs)	out = prim.compare(k2, k1);
			else		out = prim.compare(k1, k2);
			
		if (sknd != null && out == 0)
			if (rvrs)	out = sknd.compare(k2, k1);
			else		out = sknd.compare(k1, k2);
		
		if (tert != null && out == 0)
			if (rvrs)	out = tert.compare(k2, k1);
			else		out = tert.compare(k1, k2);
		
		return out;
	}
}

class KID implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.id() - k2.id();
	}
}

class KNaslov implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.naslov().compareTo(k2.naslov());
	}
}

class KPisac implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga k1, Knjiga k2) {
		return k1.pisac().compareTo(k2.pisac());
	}
}