package prakticne_vezbe.pv07_stablo_osoba_p03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class StabloOsoba {

	
    // Tip koji opisuje jedan cvor u stablu
    protected static class Cvor {

        // Sadrzaj cvora
        public Osoba osoba;

        // Levo i desno podstablo
        public Cvor levo;
        public Cvor desno;

        // Jedini konstruktor
        public Cvor(Osoba osoba, Cvor levo, Cvor desno) {
            this.osoba = osoba;
            this.levo = levo;
            this.desno = desno;
        }
    }

    
    // Stablo ima referencu na korenski cvor
    protected final Cvor koren;

    
    // Kreiramo prazno stablo
    public StabloOsoba() {
        koren = null;
    }

    
    // Kreiramo stablo sa jednom osobom u korenu
    // i praznim levim i desnim podstablom
    public StabloOsoba(Osoba osoba) {
        koren = new Cvor(osoba, null, null);
    }

    
    // Specijalan konstruktor koji koriste neki metodi ove klase
    protected StabloOsoba(Cvor koren) {
        this.koren = koren;
    }
    
    
    public boolean jePrazno() {
    	return koren == null;
    }
    
    
    public boolean postojiElement(Cvor cvor, Osoba element) {
    	
    	if (cvor == null)
    		return false;
    	
    	if (Objects.equals(cvor.osoba, element))
    		return true;
    	
    	boolean nadjenLevo = postojiElement(cvor.levo, element);
    	if (nadjenLevo) return nadjenLevo;
    	
    	boolean nadjenDesno = postojiElement(cvor.desno, element);
    	if (nadjenDesno) return nadjenDesno;
    	
    	return false;
    }
    
    
    public void stampajPreOrder(Cvor cvor) {
    	
    	if (cvor == null)
    		return;
    	
    	System.out.println(cvor.osoba);
    	stampajPreOrder(cvor.levo);
    	stampajPreOrder(cvor.desno);
    }

    
    public void stampajInOrder(Cvor cvor) {
    	
    	if (cvor == null)
    		return;
    	
    	stampajInOrder(cvor.levo);
    	System.out.println(cvor.osoba);
    	stampajInOrder(cvor.desno);
    }

    
    public void stampajPostOrder(Cvor cvor) {
    	
    	if (cvor == null)
    		return;
    	
    	stampajPostOrder(cvor.levo);
    	stampajPostOrder(cvor.desno);
    	System.out.println(cvor.osoba);
    }
    
    
    public void stampajListove(Cvor cvor) {
    	
    	if (cvor == null || cvor.levo != null || cvor.desno != null)
    		return;
    	
    	System.out.println(cvor.osoba);
    	stampajListove(cvor.levo);
    	stampajListove(cvor.desno);
    }
    
    
    public StabloOsoba pronadji(Osoba element) {
    	
    	if (koren == null)
    		return null;
    	
    	PosetilacOsoba po = new PosetilacOsoba(element);
    	prodjiKrozStablo(koren, po);
    	
    	return po.s;
    }


	private void prodjiKrozStablo(Cvor cvor, IPoseti p) {
		
		if (cvor == null)
			return;
		
		p.posetiCvor(cvor);
		prodjiKrozStablo(cvor.levo, p);
		prodjiKrozStablo(cvor.desno, p);
	}
	
	
	public List<Osoba> stampajSveIspod(Osoba element) {
		
		if (koren == null)
			return null;
		
		StabloOsoba s = this.pronadji(element);
		
		PosetilacLista pl = new PosetilacLista();
		prodjiKrozStablo(s.koren, pl);
		
		return pl.sviIspodKorena();
	}
	
	
	public boolean ubaci(Osoba roditelj, Osoba potomak, boolean levo) {
		
		if (koren == null)
			return false;
		
		StabloOsoba s = pronadji(roditelj);
		
		if (levo && s.koren.levo == null) {
			s.koren.levo = new Cvor(potomak, null, null);
			return true;
		} else if (!levo && s.koren.desno == null) {
			s.koren.desno = new Cvor(potomak, null, null);
			return true;
		}
		
		return false;
	}
	
	
	public Osoba roditeljOd(Osoba potomak) {
		
		if (koren == null)
			return null;
		
		PosetilacRoditelj pr = new PosetilacRoditelj(potomak);
		prodjiKrozStablo(koren, pr);
		
		return pr.roditelj;
	}
	
	
	public Osoba optimalnaOsoba(Comparator<Osoba> komparator, Cvor cvor) {
		
		if (koren == null)
			return null;
		
		PosetilacKomparator pk = new PosetilacKomparator(komparator);
		prodjiKrozStablo(koren, pk);
		
		return pk.o;
	}
	
	
	public StabloOsoba kopija() {
		
		if (koren == null)
			return null;
		
		StabloOsoba s = new StabloOsoba(
				new Cvor(
						new Osoba(
								koren.osoba.getIme(), 
								koren.osoba.getPrezime(), 
								koren.osoba.getPlata()), 
						null, null));
		kopija(koren, s.koren);
		
		return s;
	}


	private void kopija(Cvor orig, Cvor tren) {
		
		if (orig == null)
			return;
		
		if (orig.levo != null) {
			tren.levo = new Cvor(
					new Osoba(
							orig.levo.osoba.getIme(), 
							orig.levo.osoba.getPrezime(), 
							orig.levo.osoba.getPlata()), 
					null, null);
			kopija(orig.levo, tren.levo);
		}
		
		if (orig.desno != null) {
			tren.desno = new Cvor(
					new Osoba(
							orig.desno.osoba.getIme(), 
							orig.desno.osoba.getPrezime(), 
							orig.desno.osoba.getPlata()), 
					null, null);
			kopija(orig.desno, tren.desno);
		}
	}
	
	
	public StabloOsoba obrnuto() {
		
		if (koren == null)
			return null;
		
		StabloOsoba s = new StabloOsoba(
				new Cvor(
						new Osoba(
								koren.osoba.getIme(), 
								koren.osoba.getPrezime(), 
								koren.osoba.getPlata()), 
						null, null));
		obrnuto(koren, s.koren);
		
		return s;
	}


	private void obrnuto(Cvor orig, Cvor tren) {
		
		if (orig == null)
			return;
		
		if (orig.levo != null) {
			tren.desno = new Cvor(
					new Osoba(
							orig.levo.osoba.getIme(), 
							orig.levo.osoba.getPrezime(), 
							orig.levo.osoba.getPlata()), 
					null, null);
			kopija(orig.levo, tren.desno);
		}
		
		if (orig.desno != null) {
			tren.levo = new Cvor(
					new Osoba(
							orig.desno.osoba.getIme(), 
							orig.desno.osoba.getPrezime(), 
							orig.desno.osoba.getPlata()), 
					null, null);
			kopija(orig.desno, tren.levo);
		}
	}
	
	
	public Osoba drugaPoReduSaNajvecomPlatom() {
		
		if (koren == null)
			return null;
		
		PosetilacDrugaPoReduPlate pd = new PosetilacDrugaPoReduPlate();
		prodjiKrozStablo(koren, pd);
		
		return pd.o2;
	}
	
	
	public boolean jeBST() {
		return jeBST(koren, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}


	private boolean jeBST(Cvor cvor, int min, int max) {
		
		if (cvor == null)
			return true;
		
		if (cvor.osoba.getPlata() < min || cvor.osoba.getPlata() > max)
			return false;
		
		return jeBST(cvor.levo, min, cvor.osoba.getPlata() - 1) && 
			   jeBST(cvor.desno, cvor.osoba.getPlata() + 1, max) ;
	}
}