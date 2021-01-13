package kol2_grupa1;

import org.svetovid.Svetovid;

class Stablo {
    protected static class Cvor {
    	Proizvod info;
    	Cvor levo;
    	Cvor desno;
    }

    private Cvor koren;

    // ------ dodati opisane metode ------------
    // ------ po potrebi i pomocne metode, konstruktore, klase i sl
    // ------ ne dodavati polja u klasu!
    
    /***************************
     * NIKOLA VETNIC 438/19 IT *
     ***************************/
    
    private void prodjiKrozStablo(Cvor cvor, IPoseti p) {
    	
    	if (cvor == null) return;
    	
    	p.posetiCvor(cvor);
    	prodjiKrozStablo(cvor.levo, p);
    	prodjiKrozStablo(cvor.desno, p);
    }
    
    public void ispisiSkupe() {
    	
    	if (koren == null) return;
    	
    	PosetilacSkupi p = new PosetilacSkupi();
    	prodjiKrozStablo(koren, p);
    }
    
    public int nemaNaStanju() {
    	
    	if (koren == null) return -1;
    	
    	PosetilacStanje p = new PosetilacStanje();
    	prodjiKrozStablo(koren, p);
    	
    	return p.nemaNaStanju;
    }
    
    public Stablo snizenje() {
    	
    	if (koren == null) return null;
    	
    	Stablo s = new Stablo();
    	s.koren = new Cvor();
    	s.koren.info = new Proizvod(
    			koren.info.getNaziv(), 
    			koren.info.getCena() * 0.9 - (koren.info.getCena() * 0.9) % 0.01, 
    			koren.info.isAkcija(), 
    			koren.info.getKolicina());
    	
    	snizenje(koren, s.koren);
    	
    	return s;
    }

	private void snizenje(Cvor orig, Cvor tren) {
		
		if (orig == null) return;
		
		if (orig.levo != null) {
			tren.levo = new Cvor();
			tren.levo.info = new Proizvod(
					orig.levo.info.getNaziv(), 
					orig.levo.info.getCena() * 0.9 - (orig.levo.info.getCena() * 0.9) % 0.01, 
					orig.levo.info.isAkcija(), 
					orig.levo.info.getKolicina());
			
			snizenje(orig.levo, tren.levo);
		}
		
		if (orig.desno != null) {
			tren.desno = new Cvor();
			tren.desno.info = new Proizvod(
					orig.desno.info.getNaziv(), 
					orig.desno.info.getCena() * 0.9 - (orig.desno.info.getCena() * 0.9) % 0.01, 
					orig.desno.info.isAkcija(), 
					orig.desno.info.getKolicina());
			
			snizenje(orig.desno, tren.desno);
		}
	}
	
	public Proizvod drugiNajjeftiniji() {
		
		if (koren == null) return null;
		
		PosetilacProizvod p = new PosetilacProizvod();
		prodjiKrozStablo(koren, p);
		
		return p.p2;
	}
	
	public boolean jeSimetricno() {
		
		if (koren == null) return true;
		
		return jeSimetricno(koren, koren);
	}

	private boolean jeSimetricno(Cvor cvor1, Cvor cvor2) {
		
		if (cvor1 == null && cvor2 == null)
			return true;
		
		if (cvor1 != null && cvor2 != null && cvor1.info.isAkcija() == cvor2.info.isAkcija())
			return jeSimetricno(cvor1.levo, cvor2.desno) && jeSimetricno(cvor1.desno, cvor2.levo);
		
		return false;
	}
}

// Glavna klasa
public class StabloProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<Stablo> io = new TreeIO<>(Stablo.class);

		// Procitamo stablo iz fajla
		Stablo stablo = io.read(Svetovid.in("res//Malo.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		//Stablo stablo = io.read(Svetovid.in("Veliko.txt"));
		//Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);
		System.out.println();

		// -------- ovde dodati pozive metoda ----------
		
		System.out.println("Proizvodi skuplji od 1,000 RSD:");
		stablo.ispisiSkupe();
		System.out.println();
		
		System.out.println("Trenutno na stanju nema proizvoda: " + stablo.nemaNaStanju());
		System.out.println();
		
		Stablo snizenje = stablo.snizenje();
		io.print(Svetovid.out, snizenje);
		System.out.println();
		
		System.out.println("Drugi najskuplji proizvod: " + stablo.drugiNajjeftiniji());
		System.out.println();
		
		if (stablo.jeSimetricno()) System.out.println("Stablo JESTE simetricno.");
		else System.out.println("Stablo NIJE simetricno.");
	}
}
