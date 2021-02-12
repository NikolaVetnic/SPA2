package prakticne_vezbe.pv06_lavirint_komplikovano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * Klasa Put koristi se za pamcenje puta kroz mapu. Implementacija nas-
 * tavnika sa predmeta.
 */
public class Put {

	// Pamtimo sva polja na putu.
	private ArrayList<Polje> polja;
	
	/*
	 * Dodato da se omoguci pristup poljima puta "od spolja" (za prove-
	 * ru kvaliteta puta na primer), ali tako da ne moze da se utice n-
	 * a sam sadrzaj te liste.
	 */
	private List<Polje> nepromenljivaListaPolja;
	
	// Potrebno za graficki prikaz.
	private String naziv;
	
	// Potrebno za graficki prikaz.
	private Put(String naziv, Color boja) {
		polja = new ArrayList<Polje>();
		nepromenljivaListaPolja = Collections.unmodifiableList(polja);
		this.naziv = naziv;
		/*
		 * The double colon (::) operator, also known as method refere-
		 * nce operator in Java, is used to call a method by making re-
		 * ference to it with the help of its class directly. 
		 */
		Prikaz.put(naziv, polja, Polje::getX, Polje::getY, this::toString, boja);
	}
	
	// Kreira novo prazno resenje.
	public Put() {
		this("Trenutni", Prikaz.TIRKIZNA);
	}
	
	// Kreira novo resenje sa istim sadrzajem kao original.
	public Put(Put original) {
		this("Optimalan", Prikaz.LJUBICASTA);
		polja.addAll(original.polja);
	}
	
	// Dodaje polje u resenje.
	public void dodaj(int x, int y, int v) {
		polja.add(new Polje(x, y, v));
		Prikaz.osveziPut(naziv);
	}
	
	// Izbacuje poslednje polje iz puta.
	public void izbaciKraj() {
		if (getLength() > 0) {
			polja.remove(getLength() - 1);
			Prikaz.osveziPut(naziv);
		} else {
			throw new IllegalStateException("Resenje je vec prazno");
		}
	}
	
	// Stampa put.
	public void stampaj() {
		System.out.println();
		System.out.println("Put duzine " + getLength() + " i vrednosti " + getVrednost());
		for (int i = 0; i < getLength(); i++) {
			System.out.println(polja.get(i));
		}
		Prikaz.put("Trenutni", null, null, null);
		Prikaz.put("Optimalan", null, null, null);
	}
	
	public String toString() {
		return getVrednost() + "\u20ac " + getLength() + "m";
	}
	
	// Vraca duzinu puta.
	public int getLength() {
		return polja.size();
	}

	// Vraca i-to polje na putu. Ne koristi se u ovoj verziji zada-
	// tka. Moze se koristiti za proveru kvaliteta resenja.
	public Polje getPolje(int i) {
		return polja.get(i);
	}
	
	// Vraca sva polja na putu. Ne koristi se u ovoj verziji zadat-
	// ka. Moze se koristiti za proveru kvaliteta resenja.
	public List<Polje> getPolja() {
		return nepromenljivaListaPolja;
	}
	
	// Vraca vrednost puta. Vrednost se definise kao zbir svih vre-
	// dnosti polja na putu.
	public int getVrednost() {
		int rez = 0;
		for (Polje p : polja) {
			rez = rez + p.getV();
		}
		return rez;
	}
}
