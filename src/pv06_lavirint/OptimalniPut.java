package pv06_lavirint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.svetovid.Svetovid;

public class OptimalniPut {

	public static final int IZLAZ = -5;
    public static final int ZID = -1;

    private static int visina, sirina;

    private static int [][] lavirint;
    private static boolean [][] posecenost;
    
    private Put optimalniPut;
    
	// MODIFIKACIJA #1
    private List<Put> sviPutevi;
    
    public OptimalniPut(String fajl) {
    	
    	if (!Svetovid.testIn(fajl))
            throw new RuntimeException("Nemoguce je ucitati fajl.");

        sirina = Svetovid.in(fajl).readInt();
        visina = Svetovid.in(fajl).readInt();
        lavirint = new int[sirina][visina];
        posecenost = new boolean [sirina][visina];

        for (int j = 0; j< visina; j++){
            for (int i = 0; i< sirina; i++) {
                lavirint[i][j] = Svetovid.in(fajl).readInt();
            }
        }
        
        Svetovid.closeIn(fajl);
        
        stampaj();
    }

    public static void stampaj (){
        System.out.println(visina + " " + sirina);
        System.out.println("Lavirint: ");
        for (int j = 0; j< visina; j++){
            for (int i = 0; i< sirina; i++) {
                System.out.printf("%1$5d",lavirint[i][j]);
            }
            System.out.println();
        }
    }
    
    public Put najkraciPut(int x, int y) {
    	
    	Put r = new Put();
    	optimalniPut(x, y, r, new KomparatorPoDuzini());
    	return optimalniPut;
    }
    
	// MODIFIKACIJA #1
    // Mala modifikacija metoda za pronalazenje optimalnog puta ko-
    // ja ne poredi puteve vec svaki pronadjeni smesta u listu.
    private void pronadjiSvePuteve(int x, int y, Put r){

        if (x < 0 || x >= sirina || y < 0 || y >= visina)
            return;

        if (lavirint[x][y] == ZID)
            return;

        if (posecenost[x][y])
            return;
        
        if (lavirint[x][y] == IZLAZ) {
        	
            r.dodaj(x, y, 0);
            sviPutevi.add(r.kopija());
            
            r.izbaciKraj();
            return;
        }

        posecenost[x][y] = true;
        r.dodaj(x, y, lavirint[x][y]);
        
        pronadjiSvePuteve(x+1, y, r);
        pronadjiSvePuteve(x-1, y, r);
        pronadjiSvePuteve(x, y+1, r);
        pronadjiSvePuteve(x, y-1, r);

        posecenost[x][y] = false;
        r.izbaciKraj();
    }
    
    private void optimalniPut(int x, int y, Put r, Comparator<Put> comparator){

        if (x < 0 || x >= sirina || y < 0 || y >= visina)
            return;

        if (lavirint[x][y] == ZID)
            return;

        if (posecenost[x][y])
            return;
        
        if (lavirint[x][y] == IZLAZ) {
        	
            r.dodaj(x, y, 0);
            sviPutevi.add(r);
            
            if (optimalniPut == null || comparator.compare(r, optimalniPut) < 0)
            	optimalniPut = r.kopija();
            
            r.izbaciKraj();
            return;
        }

        posecenost[x][y] = true;
        r.dodaj(x, y, lavirint[x][y]);
        
        optimalniPut(x+1, y, r, comparator);
        optimalniPut(x-1, y, r, comparator);
        optimalniPut(x, y+1, r, comparator);
        optimalniPut(x, y-1, r, comparator);

        posecenost[x][y] = false;
        r.izbaciKraj();
    }
    
    public void stampajSvePuteve(int x, int y) {
    	
    	sviPutevi = new ArrayList<Put>();
    	
    	pronadjiSvePuteve(x, y, new Put());
    	
    	if (sviPutevi == null) {
    		System.out.println("Lista puteva je prazna.");
    		return;
    	}
    	
    	for (int i = 0; i < sviPutevi.size(); i++)
    		System.out.printf("(%3d) " + sviPutevi.get(i) + "\n", i);
    }
    
    // MODIFIKACIJA #2
    public int prebrojSvePuteve() {
    	
    	if (sviPutevi == null) {
    		System.out.println("Nijedan put do sada nije pronadjen.");
    		return -1;
    	} else {
    		return sviPutevi.size();
    	}
    }
    
    // MODIFIKACIJA #3
    public int prebrojSvePuteveSaViseOdTriBlaga() {
    	
    	int out = 0;
    	
    	if (sviPutevi == null) {
    		
    		System.out.println("Nijedan put do sada nije pronadjen.");
    		return -1;
    	} else {
    		
    		for (Put sp : sviPutevi) {
    			int count = 0;
    			for (Polje p : sp.polja()) {
    				if 		(p.v() >  0) count++;
    				else if (p.v() == 0) count = 0;
    				
    				if (count == 3) {
    					out++;
    					break;
    				}
    			}
    		}
    	}
    	
    	return out;
    }
    
    public static void main(String[] args) {
    	
    	System.out.println("Unesite ime fajla: ");
    	String fajl = Svetovid.in.readLine();
    	
    	if (fajl.equalsIgnoreCase(""))
    		fajl = "res//pv06_lavirint_blago1.txt";
    	
    	if (!Svetovid.testIn(fajl)) {
            System.out.println("Nemoguce je ucitati fajl.");
    		return;
    	}
    	
    	// MODIFIKACIJA #1
    	OptimalniPut optP = new OptimalniPut(fajl);
    	optP.stampajSvePuteve(0, 0);
    	
    	// MODIFIKACIJA #2
    	System.out.println("Puteva ukupno ima: " + optP.prebrojSvePuteve());
    	
    	// MODIFIKACIJA #3
    	System.out.println("Puteva sa vise od tri blaga ukupno ima: " + optP.prebrojSvePuteveSaViseOdTriBlaga());
    	
    	// Trazenje najkraceg puta.
    	Put put;
    	
    	System.out.print("Najkraci put je :  ");
    	put = optP.najkraciPut(0, 0);
    	if (put != null)
    		System.out.println(put);
    	else
    		System.out.println("Nema resenja.");
    }
}
