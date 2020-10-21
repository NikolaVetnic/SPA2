package tv01_automobil;

import java.io.IOException;
import java.util.Arrays;

import org.svetovid.Svetovid;

public class Automobili {

	public static void main(String[] args) throws IOException {
		
		// ucitavanje automobila
		String fajl = Svetovid.in.readLine("Unesite ime fajla sa automobilima: ");
		
		Automobil[] niz = ucitajAutomobile(fajl);
		
		// ako nismo uspesno ucitali niz, zavrsavamo program
		if (niz == null) {
			Svetovid.out.doPrintln("Dati fajl nije mogao da se procita! Proverite da li postoji.");
			return;
		}
		
		// stampanje ucitanog niza
		stampajNiz("Nesortirani niz: ", niz);
		
		// sortiranje niza
		sortirajNiz(niz);
		stampajNiz("Sortirani niz: ", niz);
		
		// sortiranje niza po drugom kriterijumu
		sortirajNizAlt(niz);
		stampajNiz("Sortirani niz po drugom kriterijumu: ", niz);
	}
	
	public static Automobil[] ucitajAutomobile(String fajl) {
		return null;
	}
	
	private static void sortirajNiz(Automobil[] niz) {
		Arrays.sort(niz);
	}
	
	private static void sortirajNizAlt(Automobil[] niz) {
		Arrays.sort(niz, new GodisteIKmKomparator());
	}
	
	public static void stampajNiz(String s, Automobil[] niz) {
		return;
	}
}
