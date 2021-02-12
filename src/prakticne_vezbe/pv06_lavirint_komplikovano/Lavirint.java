package prakticne_vezbe.pv06_lavirint_komplikovano;

import java.util.Comparator;

/**
 * Klasa Lavirint sadrzi 3 javne i tri privatne metode za trazenje put-
 * eva.
 * 
 * Klase KomparatorPoDuzini i KomparatorPoVrednosti predstavljaju komp-
 * aratore koji se korste pri trazenju najkraceg i najvrednijeg puta.
 */
public class Lavirint {

	// Polje m sadrzi kompletnu mapu.
	private Mapa m;
	
	// Polje optResenje sluzi za pamcenje optimalnog resenja.
	private Put optResenje;
	
	// Ucitava mapu iz datog fajla i stampa je na ekran.
	public Lavirint(String imeFajla) {
		m = new Mapa(imeFajla);
		m.stampaj();
	}
	
	// Proverava da li postoji put do izlaza i vraca true ako post-
	// oji put ili false ako ne postoji.
	public boolean postojiPut(int x, int y) {
		
		if (x < 0 || x >= m.getSirina() || y < 0 || y >= m.getVisina())
			return false;
		
		if (m.getPos(x, y) == true) 		return false;
		if (m.getMat(x, y) == Mapa.ZID) 	return false; 
		if (m.getMat(x, y) == Mapa.IZLAZ) 	return true;
		
		m.setPos(x, y, true);
		
		if (postojiPut(x + 1, y))			return true;
		if (postojiPut(x - 1, y))			return true;
		if (postojiPut(x, y + 1))			return true;
		if (postojiPut(x, y - 1))			return true;
		
		m.setPos(x, y, false);
		return false;
	}

	// Poziva metodu rput da pronadje i ispise put, ako postoji. U-
	// koliko put ne postoji, ispisuje poruku o gresci.
	public void nadjiPut(int x, int y) {
		if (!rput(x, y))
			System.out.println("Ne postoji put");
	}

	// Proverava da li postoji put korsiteci pretrazivanje sa vrac-
	// anjem. Ukoliko se pronadje izlaz iz lavirinta, stampa se put
	// u obrnutom redosledu. Put se stampa pri povratku iz rekurzi-
	// je.
	private boolean rput(int x, int y) {
		
		if (x < 0 || x >= m.getSirina() || y < 0 || y >= m.getVisina())
			return false;
		
		if (m.getPos(x, y) == true) 		return false;
		if (m.getMat(x, y) == Mapa.ZID) 	return false;
		
		if (m.getMat(x, y) == Mapa.IZLAZ) {
			System.out.println(x + " " + y);
			return true;
		}
		
		m.setPos(x, y, true);
		if (rput(x + 1, y) || 
			rput(x, y + 1) || 
			rput(x, y - 1) || 
			rput(x - 1, y)) {
            System.out.println(x + " " + y);
            return true;
        }
		
		m.setPos(x, y, false);
		return false;
	}
	
	// Kreira optimalno resenje za put, pri cemu se za procenu opt-
	// imalnosti resenja koristi komparator po duzini resenja, odn-
	// osno trazi se najkrace resenje. Samo resenje kreira se u me-
	// todi optPut.
	public Put najkraciPut(int x, int y) {
		Put r = new Put();
		optPut(x, y, r, new KomparatorPoDuzini());
		return optResenje;
	}
	
	// Kreira optimalno resenje za put, pri cemu se za procenu opt-
	// imalnosti resenja koristi komparator po vrednosti, tj. trazi
	// se najvrednije resenje. Samo resenje kreira se u metodi opt-
	// Put.
	public Put najvrednijiPut(int x, int y) {
		Put trenutni = new Put();
		optPut(x, y, trenutni, new KomparatorPoVrednosti());
		return optResenje;
	}
	
	// Kreira optimalno resenje za put, pri cemu se za procenu opt-
	// Proverava da li postoji put korsiteci pretrazivanje sa vrac-
	// anjem. Ukoliko se pronadje na prvi ili optimalniji put, pam-
	// ti se taj put u optResenje. Optimalnost puta se proverava k-
	// omparatorom.
	private void optPut(int x, int y, Put trenutni, Comparator<Put> c) {
		
		if (x < 0 || x >= m.getSirina() || y < 0 || y >= m.getVisina())
			return;
		
		if (m.getPos(x, y)) 				return;
		if (m.getMat(x, y) == Mapa.ZID) 	return;
		
		if (m.getMat(x, y) == Mapa.IZLAZ) {
			trenutni.dodaj(x, y, 0);
			if (optResenje == null || c.compare(trenutni, optResenje) < 0)
				optResenje = new Put(trenutni);
			
			trenutni.izbaciKraj();
			return;
		}
		
		// Pokusavamo da trazimo dalje put.
		m.setPos(x, y, true);
		trenutni.dodaj(x,  y,  m.getMat(x, y));
		optPut(x + 1, y, trenutni, c);
		optPut(x - 1, y, trenutni, c);
		optPut(x, y + 1, trenutni, c);
		optPut(x, y - 1, trenutni, c);
		m.setPos(x, y, false);
		trenutni.izbaciKraj();
	}
}

//Komparator za puteve  po duzini resenja
class KomparatorPoDuzini implements Comparator<Put> {
	public int compare(Put r1, Put r2) {
		return r1.getLength() - r2.getLength();
	}
}

//Komparator za puteve po vrednosti resenja
class KomparatorPoVrednosti implements Comparator<Put> {
	public int compare(Put r1, Put r2) {
		return r2.getVrednost() - r1.getVrednost();
	}
}