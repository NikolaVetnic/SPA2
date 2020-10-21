package pv01;

import java.util.Arrays;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidWriter;

public class Biblioteka {
	
	public static void main(String[] args) {
		
		String imeFajla = Svetovid.in.readLine("Unesite ime fajla: ");
		Knjiga[] knjige = ucitajKnjige(imeFajla);
		
		stampajNiz(knjige);
		System.out.println();
		
		Arrays.sort(knjige);
		
		stampajNiz(knjige);
		System.out.println();
		
		imeFajla = Svetovid.in.readLine("Unesite ime za cuvanje fajla: ");
		snimiKnjige(knjige, imeFajla);
	}
	
	public static Knjiga[] ucitajKnjige(String imeFajla) {
		
		if (!Svetovid.testIn(imeFajla))
			return null;
		
		int n = Svetovid.in(imeFajla).readInt();
		
		Knjiga[] knjige = new Knjiga[n];
		
		for (int i = 0; i < n; i++) {
			
			int id = Svetovid.in(imeFajla).readInt();
			String naslov = Svetovid.in(imeFajla).readLine();
			String autor = Svetovid.in(imeFajla).readLine();
			
			knjige[i] = new Knjiga(id, naslov, autor);
		}
		
		Svetovid.closeIn(imeFajla);
		
		return knjige;
	}
	
	public static void snimiKnjige(Knjiga[] niz, String imeFajla) {
		
		if (!Svetovid.testOut(imeFajla)) {
			
			System.out.println("Nemoguce je kreirati fajl.");
			return;
		}
		
		SvetovidWriter out = Svetovid.out(imeFajla);
		
		out.println(niz.length);
		
		for (int i = 0; i < niz.length; i++) {
			
			out.println(niz[i].getId());
			out.println(niz[i].getNaslov());
			out.println(niz[i].getAutor());
		}
		
		out.close();
	}
	
	public static void stampajNiz(Knjiga[] niz) {
		
		for (Knjiga k: niz)
			System.out.println(k);
	}
}
