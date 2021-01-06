package pv05_lavirint_practice_p01;

import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;

public class PostojanjePutaDomaci {
	
	/*
	 * Ovo je verzija zadatka sa uradjenim cetvrtim domacim zadatkom p-
	 * osle petih prakticnih vezbi. Zadaci:
	 * 1. Omoguciti dijagonalno kretanje;
	 * 2. Omoguciti penjanje po preprekama, ali samo po preprekama koj-
	 *    e su za najviše 3 vise od visine trenutnog polja - ukoliko se
	 *    vec nalazimo na prepreci, dozvoljeno je preci na višu prepre-
	 *    ku samo ukoliko ona nije veca za vise od 3 od trenutne prepr-
	 *    eke. Silazak sa prepreke je moguc na bilo koje polje osim zi-
	 *    da;
	 * 3. Omoguciti preskakanje prepreka, ali najvise 4 puta
	 */ 

	private static final int IZLAZ 	= -99;
	private static final int ZID	= -11;
	
	private static final int MAX_PREPREKA = 4;
	
	
	private static int visina, sirina;
	
	private static int[][] lavirint;
	private static boolean[][] posecenost;
		
	private static String[] fajlovi = { 
			"lavirint_1",
			"lavirint_2",
			"lavirint_neresiv",
			"lavirint_prazan",
			"lavirint_prepreke1",
			"lavirint_rupe",
	};
	
	
	public static boolean ucitaj(String input) {
		
		String fajl = "res//" + input + ".txt";
		
		if (!Svetovid.testIn(fajl)) return false;
		
		sirina = Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();

		lavirint = new int[sirina][visina];
		posecenost = new boolean[sirina][visina];
		
		for (int j = 0; j < visina; j++)
			for (int i = 0; i < sirina; i++)
				lavirint[i][j] = Svetovid.in(fajl).readInt();
		
		Svetovid.closeIn(fajl);
		return true;
	}
	
	
	/**
	 * Stampa matrice vezane za zadatak.<br>
	 * @param a == 0 : stampa lavirint,<br>
	 * @param a == 1 : stampa posecenost
	 */ 
	public static void stampaj(int a) {
		
		System.out.println(visina + " " + sirina);
		System.out.println("Lavirint: ");
		
		for (int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				if (a == 0)
					System.out.printf("%1$5d", lavirint[i][j]);
				else
					System.out.printf("%1$5d", posecenost[i][j] ? 1 : 0);
			}
			System.out.println();
		}
	}
	
	
	public static boolean postojiPut(int x, int y, List<String> polja, int prethodnoPolje, int doSadaPreskocio) {
				
		if (x < 0 || sirina <= x || y < 0 || visina <= y)	// obavezan uslov #1
			return false;
		
		if (lavirint[x][y] == ZID)							// obavezan uslov #2
			return false;

		if (posecenost[x][y] == true)						// obavezan uslov #3
			return false;
		
		if (!dodatniUslov2(x, y, polja, prethodnoPolje))
			return false;

		if (lavirint[x][y] == IZLAZ) {						// obavezan uslov #4
			posecenost[x][y] = true;
			polja.add(y + "-" + x);
			return true;
		}

		if (!dodatniUslov3(x, y, doSadaPreskocio))
			return false;
		
		posecenost[x][y] = true;
		polja.add(y + "-" + x);
		if (lavirint[x][y] > 0) doSadaPreskocio++;
		
//		if (dodatniUslov1(x, y, polja, lavirint[x][y], doSadaPreskocio)) return true;
		
		if (postojiPut(x + 1, y, polja, lavirint[x][y], doSadaPreskocio)) return true;
		if (postojiPut(x - 1, y, polja, lavirint[x][y], doSadaPreskocio)) return true;
		if (postojiPut(x, y + 1, polja, lavirint[x][y], doSadaPreskocio)) return true;
		if (postojiPut(x, y - 1, polja, lavirint[x][y], doSadaPreskocio)) return true;		
		
		posecenost[x][y] = false;
		polja.remove(y + "-" + x);
		if (lavirint[x][y] > 0) doSadaPreskocio--;
		
		return false;
	}
	
	
	//////////////////
	//  ZADATAK #1  //
	//////////////////
	private static boolean dodatniUslov1(int x, int y, List<String> polja, int prethodnoPolje, int doSadaPreskocio) {
		
		if (postojiPut(x + 1, y + 1, polja, lavirint[x][y], doSadaPreskocio)) return true;
		if (postojiPut(x - 1, y - 1, polja, lavirint[x][y], doSadaPreskocio)) return true;
		if (postojiPut(x - 1, y + 1, polja, lavirint[x][y], doSadaPreskocio)) return true;
		if (postojiPut(x + 1, y - 1, polja, lavirint[x][y], doSadaPreskocio)) return true;
		
		return false;
	}
	
	
	//////////////////
	//  ZADATAK #2  //
	//////////////////
	private static boolean dodatniUslov2(int x, int y, List<String> polja, int prethodnoPolje) {
		
		if (lavirint[x][y] - prethodnoPolje > 3)
			return false;
		
		return true;
	}
	
	
	//////////////////
	//  ZADATAK #3  //
	//////////////////
	private static boolean dodatniUslov3(int x, int y, int doSadaPreskocio) {
		
		if (doSadaPreskocio >= MAX_PREPREKA)
			return false;
		
		return true;
	}
	
	
	public static String startMenu() {
		
		System.out.println("Odaberite lavirint: ");
		for (int i = 0; i < fajlovi.length; i++)
			System.out.printf("[%2d] %s \n", i, fajlovi[i]);
		
		int idx = Svetovid.in.readInt("-> ");
		
		if (idx < 0 || fajlovi.length <= idx)
			throw new IllegalArgumentException("Indeks fajla izlazi van okvira niza");
		else
			return fajlovi[idx];
	}
	
	
	public static void main(String[] args) {
		
		if (ucitaj(startMenu())) {
			
			stampaj(0);
			
			List<String> polja = new ArrayList<String>();
			int x = Svetovid.in.readInt("Unesite x koordinatu pocetka: ");
			int y = Svetovid.in.readInt("Unesite y koordinatu pocetka: ");
			
			try {
				if (postojiPut(x, y, polja, lavirint[x][y], 0)) {
					System.out.println("Postoji put.");
					stampaj(1);
					System.out.println(polja);
				} else {
					System.out.println("Ne postoji put.");
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.printf("Koordinate (%d, %d) su van okvira niza lavirinta! \n", x, y);
			}
		}
	}
}
