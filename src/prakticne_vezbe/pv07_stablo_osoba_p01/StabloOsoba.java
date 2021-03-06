package prakticne_vezbe.pv07_stablo_osoba_p01;

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
        
        
        //////////////////
        // ZADATAK #1&2 //
        //////////////////
        
        
        public Cvor kopiraj() {
        	return new Cvor(osoba, levo, desno);
        }
        
        public Cvor kopirajBezPodstabala() {
        	return new Cvor(osoba, null, null);
        }
        
        
        //////////////////
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

    public boolean jePrazno(){
        if (koren == null)
            return true;
        return false;
    }

    public boolean postojiElement(Cvor cvor, Osoba osoba){
        if (cvor == null)
            return false;

        if (Objects.equals(cvor.osoba, osoba))
            return true;

        boolean nadjenLevo = postojiElement(cvor.levo, osoba);
        if (nadjenLevo)
            return nadjenLevo;

        boolean nadjenDesno = postojiElement(cvor.desno, osoba);
        if (nadjenDesno)
            return nadjenDesno;

        return false;

    }

    public void stampajPreorder(Cvor cvor){
        if (cvor == null)
            return;

        System.out.println(cvor.osoba);
        stampajPreorder(cvor.levo);
        stampajPreorder(cvor.desno);
    }

    public void stampajInorder(Cvor cvor){
        if (cvor == null)
            return;

        stampajPreorder(cvor.levo);
        System.out.println(cvor.osoba);
        stampajPreorder(cvor.desno);
    }

    public void stampajPostorder(Cvor cvor){
        if (cvor == null)
            return;

        stampajPreorder(cvor.levo);
        stampajPreorder(cvor.desno);
        System.out.println(cvor.osoba);
    }

    public void stampajListove(Cvor cvor){
        if (cvor == null)
            return;

        if (cvor.levo == null && cvor.desno == null)
            System.out.println(cvor.osoba);
        else{
            stampajListove(cvor.levo);
            stampajListove(cvor.desno);
        }

    }

    public StabloOsoba pronadjiOsobu(Osoba osoba){
        Cvor cvor = pronadjiOsobu(koren, osoba);
        if (cvor == null)
            return null;
        return new StabloOsoba(cvor);

    }

    private Cvor pronadjiOsobu(Cvor cvor, Osoba osoba){
        if (cvor == null)
            return null;

        if (Objects.equals(cvor.osoba, osoba))
            return cvor;

        Cvor nadjenLevo = pronadjiOsobu(cvor.levo, osoba);
        if (nadjenLevo != null)
            return nadjenLevo;

        Cvor nadjenDesno = pronadjiOsobu(cvor.desno, osoba);
        if (nadjenDesno != null)
            return nadjenDesno;

        return null;

    }

    public List<Osoba> stampajSveIspod(Osoba sef){
        List<Osoba> osobe = new ArrayList<Osoba>();
        Cvor cvor = pronadjiOsobu(koren, sef);
        if (cvor != null)
            sveOsobe(cvor, osobe);
        return osobe;
    }

    private void sveOsobe(Cvor cvor, List<Osoba> lista){
        if (cvor == null)
            return;

        sveOsobe(cvor.levo, lista);
        lista.add(cvor.osoba);
        sveOsobe(cvor.desno, lista);
    }

    public boolean ubaci(Osoba roditelj, Osoba potomak, boolean levo){
        Cvor cvor = pronadjiOsobu(koren, roditelj);
        if (levo && cvor.levo == null) {
            cvor.levo = new Cvor(potomak, null, null);
            return true;
        }

        if (!levo && cvor.desno == null) {
            cvor.desno = new Cvor(potomak, null, null);
            return true;
        }

        return false;
    }

    public Osoba roditeljOd(Osoba potomak){
        Cvor cvor = roditeljOd(koren, null, potomak);
        if (cvor == null)
            return null;
        return cvor.osoba;

    }

    private Cvor roditeljOd(Cvor tekuci, Cvor roditelj, Osoba potomak){
        if (tekuci == null)
            return null;
        if (Objects.equals(tekuci.osoba, potomak))
            return roditelj;

        Cvor roditeljLevo = roditeljOd(tekuci.levo, tekuci, potomak);
        if (roditeljLevo != null)
            return roditeljLevo;

        Cvor roditeljDesno = roditeljOd(tekuci.desno, tekuci, potomak);
        if (roditeljDesno != null)
            return roditeljDesno;

        return null;
    }

    public Osoba optimalnaOsoba(Comparator<Osoba> komparator, Cvor cvor){
        if (cvor == null)
            return null;

        Osoba optimalnaLevo, optimalnaDesno;
        Osoba optimalna = cvor.osoba;

        if (cvor.levo != null) {
            optimalnaLevo = optimalnaOsoba(komparator, cvor.levo);
            if (komparator.compare(optimalna, optimalnaLevo) <0)
                optimalna = optimalnaLevo;
        }

        if (cvor.desno != null) {
            optimalnaDesno = optimalnaOsoba(komparator, cvor.desno);
            if (komparator.compare(optimalna, optimalnaDesno) <0)
                optimalna = optimalnaDesno;
        }

        return optimalna;

    }
    
    
    //////////////////
    //	ZADATAK #1  //
    //////////////////


    public StabloOsoba kopija(){
    	
    	if (koren == null) return null;
    	
    	StabloOsoba kopija = new StabloOsoba(koren.kopiraj());
    	kopiraj(kopija.koren, koren);
    	
    	return kopija;
    }

    private void kopiraj(Cvor kopijaTek, Cvor originTek) {
    	
    	if (originTek == null) return;
    	
    	if (originTek.levo != null) {
    		
    		Cvor leviCvor = originTek.levo.kopirajBezPodstabala();
    		
    		kopijaTek.levo = leviCvor;
    		kopiraj(kopijaTek.levo, originTek.levo);
    	}
    	
    	if (originTek.desno != null) {
    		
    		Cvor desniCvor = originTek.desno.kopirajBezPodstabala();
    		
    		kopijaTek.desno = desniCvor;
    		kopiraj(kopijaTek.desno, originTek.desno);
    	}
    }
    
    
    //////////////////
    //	ZADATAK #2  //
    //////////////////
    
    
    public StabloOsoba obrnuto(){
    	
    	if (koren == null) return null;
    	
    	StabloOsoba obrnuto = new StabloOsoba(koren.kopiraj());
    	obrnuto(obrnuto.koren, koren);
    	
    	return obrnuto;
    }
    

    private void obrnuto(Cvor obrnutoTek, Cvor originTek) {
    	
    	if (originTek == null) return;
    	
    	if (originTek.levo != null) {
    		
    		obrnutoTek.desno = originTek.levo.kopirajBezPodstabala();
    		obrnuto(obrnutoTek.desno, originTek.levo);
    	}
    	
    	if (originTek.desno != null) {
    		
    		obrnutoTek.levo = originTek.desno.kopirajBezPodstabala();;
    		obrnuto(obrnutoTek.levo, originTek.desno);
    	}
    }
    
    
	//////////////////
	//	ZADATAK #3  //
	//////////////////
    
    
    public double prosecnaPlata() {
    	
    	PosetiProsek posetilac = new PosetiProsek();
    	prodjiKrozStablo(koren, posetilac);
    	
    	System.out.println("Prosecna plata		: " + posetilac.prosecnaPlata());
    	System.out.println("Najveca plata		: " + posetilac.osobaSaNajvecomPlatom());
    	System.out.println("Druga najveca plata	: " + posetilac.osobaSaDrugomPoReduPlatom());
    	
    	return posetilac.prosecnaPlata();
    }
    
    
    public void drugaPoReduSaNajvecomPlatom() {
    	
    	PosetiProsek posetilac = new PosetiProsek();
    	prodjiKrozStablo(koren, posetilac);
    	
    	System.out.println("Druga po redu sa najvecom platom: " + posetilac.osobaSaDrugomPoReduPlatom());
    }
    
    // Bezvezna metoda za isprobavanje fore sa interfejsima.
    public double sumaPlata() {
    	
    	PosetiSuma posetilac = new PosetiSuma();
    	prodjiKrozStablo(koren, posetilac);
    	
    	System.out.println("Suma svih plata: " + posetilac.sumaPlata());
    	
    	return posetilac.sumaPlata();
    }
    
    
    private void prodjiKrozStablo(Cvor cvor, IPoseti posetliac) {
    	
    	if (cvor == null) return;
    	
    	posetliac.posetiCvor(cvor);
    	prodjiKrozStablo(cvor.desno, posetliac);
    	prodjiKrozStablo(cvor.levo,  posetliac);
    }
}