package pv02_sort_p02;

import java.io.IOException;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Generacija g = new Generacija("studenti10");
		
		KompozitniKomparator k0 = new KompozitniKomparator(
				new KLeksi(), false);
		
		KompozitniKomparator k1 = new KompozitniKomparator(
				new KGod(), new KPrz(), false);
		
		KompozitniKomparator k2 = new KompozitniKomparator(
				new KDuzina(), true);
		
		Sort.bubble(g.studenti, k0);
		Sort.insertion(g.studenti, k1);
		Sort.selection(g.studenti, k2);
		
		g.stampaj();
		
		g.snimi("studenti10mod");
	}
}

class KompozitniKomparator implements Comparator<Student> {
	
	private final Comparator<Student> prim, sknd, tert;
	private final boolean rvrs;
	
	public KompozitniKomparator(
			Comparator<Student> prim, boolean rvrs) {
		this.prim = prim;
		this.sknd = null;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Student> prim, 
			Comparator<Student> sknd, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = null;
		this.rvrs = rvrs;
	}

	public KompozitniKomparator(
			Comparator<Student> prim, 
			Comparator<Student> sknd, 
			Comparator<Student> tert, boolean rvrs) {
		this.prim = prim;
		this.sknd = sknd;
		this.tert = tert;
		this.rvrs = rvrs;
	}

	@Override
	public int compare(Student s1, Student s2) {
		
		int out = 0;
		
			if (rvrs) 	out = prim.compare(s2, s1);
			else		out = prim.compare(s1, s2);
			
		if (sknd != null && out == 0)
			if (rvrs) 	out = sknd.compare(s2, s1);
			else		out = sknd.compare(s1, s2);
		
		if (tert != null && out == 0)
			if (rvrs) 	out = tert.compare(s2, s1);
			else		out = tert.compare(s1, s2);
		
		return out;
	}
}

class KLeksi implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s1.prz().compareTo(s2.prz()) != 0 ?
			   s1.prz().compareTo(s2.prz()) : s1.ime().compareTo(s2.ime());
	}
}

class KPrz implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s1.prz().compareTo(s2.prz());
	}
}

class KIme implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s1.prz().compareTo(s2.prz());
	}
}

class KGod implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s1.god() - s2.god();
	}
}

class KDuzina implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return (s1.prz().length() + s1.ime().length()) - 
			   (s2.prz().length() + s2.ime().length()) ;
	}
}