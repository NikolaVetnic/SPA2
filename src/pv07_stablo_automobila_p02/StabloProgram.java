package pv07_stablo_automobila_p02;

import java.util.Comparator;
import java.util.List;

import org.svetovid.Svetovid;

class Stablo {
    static class Cvor {
    	Automobil info;
    	Cvor levo;
    	Cvor desno;
    }

    private Cvor koren;

    // ------ dodati opisane metode ------------
    // ------ po potrebi i pomocne metode, konstruktore, klase i sl
    // ------ ne dodavati polja u klasu!
    
    public void ispisiNS() {
    	
    	if (koren == null) return;
    	
    	PosetiNS pn = new PosetiNS();
    	prodjiKrozStablo(koren, pn);
    	
    	System.out.println("Automobili sa NS tablicama: " + pn.l);
    }

	private void prodjiKrozStablo(Cvor cvor, IPoseti p) {
		
		if (cvor == null) return;
		
		p.posetiCvor(cvor);
		prodjiKrozStablo(cvor.levo, p);
		prodjiKrozStablo(cvor.desno, p);
	}
	
	public long presliPlavi() {
		
		if (koren == null) return -1;
		
		PosetilacPlavi pp = new PosetilacPlavi();
		prodjiKrozStablo(koren, pp);
		
		System.out.println("Plavi automobili su ukupno presli: " + pp.sum);
		return pp.sum;
	}
	
	public Stablo presaoJos(String registracija, long km) {
		
		if (koren == null) return null;
		
		Stablo s = new Stablo();
		s.koren = new Cvor();
		
		String model = koren.info.getModel(),
			   boja  = koren.info.getBoja(),
			   reg   = koren.info.getRegistracija();
		long   kilo	 = koren.info.getKilometraza();
		
		if (registracija.equalsIgnoreCase(reg))
			s.koren.info = new Automobil(model, boja, reg, kilo + km);
		else
			s.koren.info = new Automobil(model, boja, reg, kilo);
		
		kopijaJos(koren, s.koren, registracija, km);
		
		return s;
	}

	private void kopijaJos(Cvor orig, Cvor tren, String registracija, long km) {
		
		if (orig == null)
			return;
		
		long levoKM = -1, desnoKM = -1;
		
		if (orig.levo != null) {
			if (orig.levo.info.getRegistracija().equalsIgnoreCase(registracija))
				levoKM = orig.levo.info.getKilometraza() + km;
			else
				levoKM = orig.levo.info.getKilometraza();
			
			tren.levo = new Cvor();
			tren.levo.info = new Automobil(
					orig.levo.info.getModel(), 
					orig.levo.info.getBoja(), 
					orig.levo.info.getRegistracija(), 
					levoKM);
			
			kopijaJos(orig.levo, tren.levo, registracija, km);
		}
		
		if (orig.desno != null) {
			if (orig.desno.info.getRegistracija().equalsIgnoreCase(registracija))
				desnoKM = orig.desno.info.getKilometraza() + km;
			else
				desnoKM = orig.desno.info.getKilometraza();
			
			tren.desno = new Cvor();
			tren.desno.info = new Automobil(
					orig.desno.info.getModel(), 
					orig.desno.info.getBoja(), 
					orig.desno.info.getRegistracija(), 
					desnoKM);
			
			kopijaJos(orig.desno, tren.desno, registracija, km);
		}
	}
	
	public List<Automobil> sortiraniPlavi() {
		
		if (koren == null) return null;
		
		PosetilacListaPlavih plp = new PosetilacListaPlavih();
		prodjiKrozStablo(koren, plp);
		
		List<Automobil> out = plp.l;
		
		out.sort((Automobil a1, Automobil a2) -> (int) (a1.getKilometraza() - a2.getKilometraza()));
		return out;
	}
	
	public boolean jeBST() {
		return isBSTUtil(koren, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean isBSTUtil(Cvor cvor, long min, long max) {
		
		if (cvor == null)
			return true;
		
		if (cvor.info.getKilometraza() < min || cvor.info.getKilometraza() > max)
			return false;
		
		return isBSTUtil(cvor.levo, min, cvor.info.getKilometraza() - 1) && 
			   isBSTUtil(cvor.desno, cvor.info.getKilometraza() + 1, max) ;
	}
}

// Glavna klasa
public class StabloProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<Stablo> io = new TreeIO<>(Stablo.class);

		// Procitamo stablo iz fajla
		Stablo stablo = io.read(Svetovid.in("res//pv07_stablo_automobila_StabloS.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		//Stablo stablo = io.read(Svetovid.in("Veliko.txt"));
		//Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);
		System.out.println();

		// -------- ovde dodati pozive metoda ----------
		
		stablo.ispisiNS();
		System.out.println();
		
		stablo.presliPlavi();
		System.out.println();
		
		Stablo s = stablo.presaoJos("NI 761-VZ", 100000L);
		io.print(Svetovid.out, s);
		System.out.println();
		
		System.out.println(stablo.sortiraniPlavi());
		
		if (stablo.jeBST()) System.out.println("Jeste BST.");
		else System.out.println("Nije BST.");
	}
}
