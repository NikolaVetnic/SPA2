package pv06_lavirint_komplikovano;

import org.svetovid.Svetovid;

/**
 * Klasa Mapa koristi se za reprezentaciju lavirint i svih njegovih po-
 * lja.
 */
public class Mapa {

	// Vrednosti polja i njihova znacenja
	public final static int IZLAZ = -5;
    public final static int ZID = -1;
    public final static int GRESKA = Integer.MIN_VALUE;
    
    // Dimenzije mape.
    private int visina, sirina;
    
    // Polja mape.
    private int[][] mat;
    
    // Prilikom pretrazivanja ovde mozemo pamtiti koja polja smo p-
    // osetili a koja nismo.
    private boolean[][] pos;
    
    public int getSirina() {
    	return sirina;
    }
    
    public int getVisina() {
    	return visina;
    }
    
    public void setPos(int x, int y, boolean b) {
    	if (0 <= x && x < sirina && 0 <= y && y < visina) {
    		pos[x][y] = b;
    	}
    }
    
    public boolean getPos(int x, int y) {
    	if (0 <= x && x < sirina && 0 <= y && y < visina) {
    		return pos[x][y];
    	} else {
    		return true;
    	}
    }
    
    public int getMat(int x, int y) {
    	if (0 <= x && x < sirina && 0 <= y && y < visina) {
    		return mat[x][y];
    	} else {
    		return GRESKA;
    	}
    }
    
    public Mapa(int sirina, int visina) {
    	this.sirina = sirina;
    	this.visina = visina;
    	mat = new int[sirina][visina];
    	pos = new boolean[sirina][visina];
    }
    
    public Mapa(String imeFajla) {
    	
    	if (!Svetovid.testIn(imeFajla)) {
    		throw new RuntimeException(
    				"Fajl za kreiranje mape (" + imeFajla + ") nije pristupacan.");
    	}
    	
    	sirina = Svetovid.in(imeFajla).readInt();
    	visina = Svetovid.in(imeFajla).readInt();
    	
    	mat = new int[sirina][visina];
    	pos = new boolean[sirina][visina];
    	
    	for (int j = 0; j < visina; j++) {
    		for (int i = 0; i < sirina; i++) {
    			mat[i][j] = Svetovid.in(imeFajla).readInt();
    		}
    	}
    	
    	Svetovid.closeIn(imeFajla);
    	
    	Prikaz.boja(ZID, Prikaz.CRNA, null);
        Prikaz.boja(IZLAZ, Prikaz.CRVENA, Prikaz.BELA);
        Prikaz.boja(1, 20, Prikaz.SVETLO_ZELENA, Prikaz.ZELENA, Prikaz.CRNA);
        Prikaz.boja(99, Prikaz.ZUTA, Prikaz.CRNA);
        Prikaz.mapa("Lavirint", getSirina(), getVisina(), this::getMat);
    }
    
    public void stampaj() {
    	if (visina != 0 &&  sirina != 0) {
    		System.out.println();
    		System.out.println("Mapa sirine " + sirina + " i visine " + visina);
    		for (int j = 0; j < visina; j++) {
    			for (int i = 0; i < sirina; i++) {
    				System.out.printf("%5d", mat[i][j]);
    			}
    			System.out.println();
    		}
    	}
    }
    
    public String toString() {
    	return "Mapa velicine " + sirina + " x " + visina;
    }
}
