package prakticne_vezbe.pv05_lavirint_practice_p02;

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
	
	public static final int RUPA 	= -1 ;
	public static final int ZID 	= -11;
	public static final int IZLAZ 	= -99;
	
	public static final int MAX_PREPREKA = 4;
	
	private static int sirina, visina;
	
	private static int[][] lavirint;
	private static boolean[][] posecenost;
	
	private static String[] fajlovi = { 
			"lavirint_1",
			"lavirint_2",
			"lavirint_neresiv",
			"lavirint_prazan",
			"lavirint_prepreke1",
			"lavirint_prepreke2",
			"lavirint_prepreke3",
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
		
		Svetovid.in(fajl).close();
		return true;
	}
	
	private static boolean postojiPut(int x, int y, List<String> polja, int prethodnaPrepreka, int brojPrepreka) {
		
		if (x < 0 || x >= sirina || y < 0 || y >= visina)
			return false;
		
		if (posecenost[x][y])
			return false;
		
		// zadatak #2
		if (lavirint[x][y] - prethodnaPrepreka > 3)
			return false;
		
		// zadatak #3
		if (brojPrepreka > MAX_PREPREKA)
			return false;
		
		if (lavirint[x][y] == ZID)
			return false;
		
		if (lavirint[x][y] == IZLAZ) {
			polja.add(x + "-" + y);
			posecenost[x][y] = true;
			
			return true;
		}
		
		polja.add(y + "-" + x);
		posecenost[x][y] = true;
		brojPrepreka = lavirint[x][y] > 0 ? brojPrepreka + 1 : brojPrepreka;
		
		if (postojiPut(x + 1, y, polja, lavirint[x][y], brojPrepreka)) return true;
		if (postojiPut(x - 1, y, polja, lavirint[x][y], brojPrepreka)) return true;
		if (postojiPut(x, y + 1, polja, lavirint[x][y], brojPrepreka)) return true;
		if (postojiPut(x, y - 1, polja, lavirint[x][y], brojPrepreka)) return true;

		polja.remove(y + "-" + x);
		posecenost[x][y] = false;
		brojPrepreka = lavirint[x][y] > 0 ? brojPrepreka - 1 : brojPrepreka;
		
		return false;
	}
	
	public static void stampaj(int a) {
		
		System.out.println(sirina + " " + visina);
		System.out.println("Lavirint: ");
		for (int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				if (a == 0)
					System.out.printf("%1$5d", lavirint[i][j]);
				else
					if (posecenost[i][j])
						System.out.print("    X");
					else
						System.out.print("    -");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		if (ucitaj(fajlovi[4])) {
			
			stampaj(0);
			List<String> polja = new ArrayList<String>();
			
			if (postojiPut(0, 0, polja, lavirint[0][0], 0)) {
				stampaj(1);
				System.out.println(polja);
			} else {
				System.out.println("Put ne postoji.");
			}
		}
	}
}
