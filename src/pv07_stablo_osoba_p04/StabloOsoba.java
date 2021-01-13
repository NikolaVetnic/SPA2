package pv07_stablo_osoba_p04;

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
    	return pronadji(element) != null;
    }
    
    
    private void prodjiKrozStablo(Cvor cvor, IPoseti posetilac) {
    	
    	if (cvor == null) return;
    	
    	posetilac.posetiCvor(cvor);
    	prodjiKrozStablo(cvor.levo, posetilac);
    	prodjiKrozStablo(cvor.desno, posetilac);
    }
    
    
    public void stampajPreOrder(Cvor cvor) {
    	
    	if (cvor == null) return;
    	
    	System.out.println(cvor.osoba);
    	stampajPreOrder(cvor.levo);
    	stampajPreOrder(cvor.desno);
    }


    public void stampajInOrder(Cvor cvor) {
    	
    	if (cvor == null) return;
    	
    	stampajInOrder(cvor.levo);
    	System.out.println(cvor.osoba);
    	stampajInOrder(cvor.desno);
    }

    
    public void stampajPostOrder(Cvor cvor) {
    	
    	if (cvor == null) return;
    	
    	stampajPostOrder(cvor.levo);
    	stampajPostOrder(cvor.desno);
    	System.out.println(cvor.osoba);
    }
    
    
    public void stampajListove(Cvor cvor) {
    	
    	if (cvor == null) return;
    	
    	if (cvor.levo == null && cvor.desno == null) {
    		System.out.println(cvor.osoba);
    	} else {
    		stampajListove(cvor.levo);
    		stampajListove(cvor.desno);
    	}
    }
    
    
    public StabloOsoba pronadji(Osoba element) {
    	
    	if (koren == null) return null;
    	
    	PosetilacOsoba p = new PosetilacOsoba(element);
    	prodjiKrozStablo(koren, p);
    	
    	return p.s;
    }
    
    
    public List<Osoba> stampajSveIspod(Osoba element) {
    	
    	if (koren == null) return null;
    	
    	PosetilacLista p = new PosetilacLista();
    	prodjiKrozStablo(pronadji(element).koren, p);
    	
    	return p.sviIspodKorena();
    }
    
    
    public boolean ubaci(Osoba roditelj, Osoba potomak, boolean levo) {
    	
    	if (koren == null) return false;
    	
    	StabloOsoba s = pronadji(roditelj);
    	
    	if ((levo && s.koren.levo != null) || (!levo && s.koren.desno != null))
    		return false;
    	
    	if (levo) s.koren.levo  = new Cvor(potomak, null, null);
    	else	  s.koren.desno = new Cvor(potomak, null, null);
    	
    	return true;
    }
    
    
    public Osoba roditeljOd(Osoba potomak) {
    	
    	if (koren == null || Objects.equals(koren, potomak))
    		return null;
    	
    	PosetilacRoditelj p = new PosetilacRoditelj(potomak);
    	prodjiKrozStablo(koren, p);
    	
    	return p.roditelj;
    }
    
    
    public Osoba optimalnaOsoba(Comparator<Osoba> komparator, Cvor cvor) {
    	
    	if (koren == null) return null;
    	
    	PosetilacKomparator p = new PosetilacKomparator((Osoba o1, Osoba o2) -> o1.getPlata() - o2.getPlata());
    	prodjiKrozStablo(koren, p);
    	
    	return p.max;
    }
    
    
    public StabloOsoba kopija() {
    	
    	if (koren == null) return null;
    	
    	StabloOsoba k = new StabloOsoba(
    			new Cvor(
    					new Osoba(
    							koren.osoba.getIme(), 
    							koren.osoba.getPrezime(), 
    							koren.osoba.getPlata()), 
    					null, null));
    	kopija(koren, k.koren);
    	
    	return k;
    }


	private void kopija(Cvor orig, Cvor tren) {
		
		if (orig == null) return;
		
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

    
    public StabloOsoba obrni() {
    	
    	if (koren == null) return null;
    	
    	StabloOsoba k = new StabloOsoba(
    			new Cvor(
    					new Osoba(
    							koren.osoba.getIme(), 
    							koren.osoba.getPrezime(), 
    							koren.osoba.getPlata()), 
    					null, null));
    	obrni(koren, k.koren);
    	
    	return k;
    }


	private void obrni(Cvor orig, Cvor tren) {
		
		if (orig == null) return;
		
		if (orig.desno != null) {
			tren.levo = new Cvor(
					new Osoba(
							orig.desno.osoba.getIme(), 
							orig.desno.osoba.getPrezime(), 
							orig.desno.osoba.getPlata()), 
					null, null);
			obrni(orig.desno, tren.levo);
		}
		
		if (orig.levo != null) {
			tren.desno = new Cvor(
					new Osoba(
							orig.levo.osoba.getIme(), 
							orig.levo.osoba.getPrezime(), 
							orig.levo.osoba.getPlata()), 
					null, null);
			obrni(orig.levo, tren.desno);
		}
	}
	
	
	public Osoba drugaPoReduSaNajvecomPlatom() {
		
		if (koren == null) return null;
		
		PosetilacDrugaPoReduPlate p = new PosetilacDrugaPoReduPlate();
		prodjiKrozStablo(koren, p);
		
		return p.o2;
	}
}