package prakticne_vezbe.pv07_stablo_automobila_p01;

import java.util.List;

import org.svetovid.Svetovid;

class Stablo {
    static class Cvor {
    	Automobil info;
    	Cvor levo;
    	Cvor desno;
    	
    	public Cvor() {
    		this(null, null, null);
    	}
    	
		public Cvor(Automobil info, Cvor levo, Cvor desno) {
			this.info = info;
			this.levo = levo;
			this.desno = desno;
		}

		public Cvor kopiraj() {
			return new Cvor(info, levo, desno);
		}

		public Cvor kopirajBezPodstabla() {
			return new Cvor(info, null, null);
		}
		
		public Cvor kopirajBezPodstablaSaDodatkomKM(long km) {
			return new Cvor(
					new Automobil(
							info.getModel(), 
							info.getBoja(), 
							info.getRegistracija(), 
							info.getKilometraza() + km), null, null);
		}
    }

    private Cvor koren;

    // ------ dodati opisane metode ------------
    // ------ po potrebi i pomocne metode, konstruktore, klase i sl
    // ------ ne dodavati polja u klasu!
    
    public void ispisiXY(String xy) {
    	
    	if (koren == null)
    		System.out.println("Stablo je prazno.");
    	else
    		ispisiXY(xy, koren);
    }

	private void ispisiXY(String xy, Cvor tekuci) {
		
		if (tekuci == null)
			return;
		
		if (tekuci.info.getRegistracija().substring(0, 2).equalsIgnoreCase(xy))
			System.out.println(tekuci.info.getRegistracija());
		
		if (tekuci.levo != null)
			ispisiXY(xy, tekuci.levo);
		
		if (tekuci.levo != null)
			ispisiXY(xy, tekuci.desno);
	}

	
	public void presliPlavi() {
		
		if (koren == null)
			System.out.println("Stablo je prazno.");
		
		PosetiPlavi posetilac = new PosetiPlavi();
		prodjiKrozStablo(koren, posetilac);
		
		System.out.println("Ukupna kilometraza plavih automobila: " + posetilac.kilometrazaPlavi());
	}

	private void prodjiKrozStablo(Cvor cvor, IPoseti posetilac) {
		
		if (cvor == null) return;
		
		posetilac.posetiCvor(cvor);
		prodjiKrozStablo(cvor.desno, posetilac);
		prodjiKrozStablo(cvor.levo,  posetilac);
	}


	public Stablo presaoJos(String registracija, long km) {
		
		if (koren == null)
			return null;
		
		Stablo kopija = new Stablo();
		
		if (koren.info.getRegistracija().equals(registracija)) {
			
			kopija.koren = koren.kopirajBezPodstablaSaDodatkomKM(km);
			kopiraj(kopija.koren, koren);
		} else {
			
			kopija.koren = koren.kopiraj();
			kopirajSaKM(kopija.koren, koren, registracija, km);
		}
		
		return kopija;
	}
		
	private void kopirajSaKM(Cvor kopijaTek, Cvor originTek, String registracija, long km) {
		
		if (originTek == null)
			return;
		
		if (originTek.levo != null) {
			
			long dodatak = originTek.levo.info.getRegistracija().equals(registracija) ? km : 0;
			
			kopijaTek.levo = originTek.levo.kopirajBezPodstablaSaDodatkomKM(dodatak);
			kopirajSaKM(kopijaTek.levo, originTek.levo, registracija, km);
		}
		
		if (originTek.desno != null) {

			long dodatak = originTek.desno.info.getRegistracija().equals(registracija) ? km : 0;
			
			kopijaTek.desno = originTek.desno.kopirajBezPodstablaSaDodatkomKM(dodatak);
			kopirajSaKM(kopijaTek.desno, originTek.desno, registracija, km);
		}
	}

	private void kopiraj(Cvor kopijaTek, Cvor originTek) {
		
		if (originTek == null)
			return;
		
		if (originTek.levo != null) {
			kopijaTek.levo = originTek.levo.kopirajBezPodstabla();
			kopiraj(kopijaTek.levo, originTek.levo);
		}
		
		if (originTek.desno != null) {
			kopijaTek.desno = originTek.desno.kopirajBezPodstabla();
			kopiraj(kopijaTek.desno, originTek.desno);
		}
	}


	public List<Automobil> sortiraniPlavi() {
		
		if (koren == null)
			return null;
		
		PosetiPlaviLista posetilac = new PosetiPlaviLista();
		prodjiKrozStablo(koren, posetilac);
		
		return posetilac.l();
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
		Stablo stablo = io.read(Svetovid.in("res//pv07_stablo_automobila_StabloM.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		//Stablo stablo = io.read(Svetovid.in("Veliko.txt"));
		//Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);
		System.out.println();
		
		// -------- ovde dodati pozive metoda ----------
		
		if (stablo.jeBST()) System.out.println("Stablo jeste binarno stablo pretrage.");
		else				System.out.println("Stablo nije binarno stablo pretrage.");
		
		stablo.ispisiXY("ns");
		System.out.println();
		
		stablo.presliPlavi();
		System.out.println();
		
		Stablo kopija = stablo.presaoJos("LE 810-ZO", 116342);
		io.print(Svetovid.out, kopija);
		System.out.println();
		
		List<Automobil> sortiraniPlavi = kopija.sortiraniPlavi();
		System.out.println(sortiraniPlavi);
		System.out.println();
		
		if (stablo.jeBST()) System.out.println("Stablo jeste binarno stablo pretrage.");
		else				System.out.println("Stablo nije binarno stablo pretrage.");
	}
}
