package prakticne_vezbe.pv05_lavirint_practice_p01;

import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;

public class PostojanjePuta {
	
	/*
	 * Ovo je originalna verzija zadatka kako je resena na petim prakt-
	 * icnim vezbama.
	 */ 
	
	public static final int IZLAZ 	= -99;
	public static final int ZID		= -11;
	
	
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
	
	
	public static void stampaj() {
		
		System.out.println(visina + " " + sirina);
		System.out.println("Lavirint: ");
		for (int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				System.out.printf("%1$5d", lavirint[i][j]);
			}
			System.out.println();
		}
	}
	
	
	public static void stampajPosecenost() {
		
		System.out.println(visina + " " + sirina);
		System.out.println("Lavirint: ");
		for (int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				System.out.printf("%1$5d", posecenost[i][j] ? 1 : 0);
			}
			System.out.println();
		}
	}
	
	
	private static boolean postojiPut(int x, int y, List<String> polja, boolean rupa) {
		
		if (x < 0 || x >= sirina || y < 0 || y >= visina)	// trenuto polje je van table?
			return false;
		
		if (lavirint[x][y] == ZID)							// trenuto polje je zid?
			return false;
		
		if (posecenost[x][y])								// trenuto polje je vec poseceno?
			return false;
		
		if (lavirint[x][y] == IZLAZ) {						// trenuto polje je izlaz?
			posecenost[x][y] = true;
			polja.add(y + "-" + x);
			return true;
		}
		
		if (rupa && lavirint[x][y] == -1)					// prethodno i trenuto polje su rupe?
			return false;
		
		posecenost[x][y] = true;							// ako nista od navedenog, posecujemo polje
		polja.add(y + "-" + x);
		
		if (postojiPut(x + 1, y, polja, lavirint[x][y] == -1)) return true;		// ispitujemo okolna polja
		if (postojiPut(x - 1, y, polja, lavirint[x][y] == -1)) return true;		// i pitamo se koje je sl-
		if (postojiPut(x, y + 1, polja, lavirint[x][y] == -1)) return true;		// edece koje cemo pokusa-
		if (postojiPut(x, y - 1, polja, lavirint[x][y] == -1)) return true;		// ti da posetimo
		
		posecenost[x][y] = false;							// ako nema takvih moramo nazad ...
		polja.remove(y + "-" + x);
		
		return false;										// ...i vracamo false za dato polje
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
		
		String fajl = startMenu();
		
		if (ucitaj(fajl)) {
			
			stampaj();
			
			List<String> polja = new ArrayList<String>();
			int x = Svetovid.in.readInt("Unesite x koordinatu pocetka: ");
			int y = Svetovid.in.readInt("Unesite y koordinatu pocetka: ");
			
			try {
				if (postojiPut(x, y, polja, lavirint[x][y] == -1)) {
					System.out.println("Put postoji.");
					stampajPosecenost();
					System.out.println(polja);
				} else {
					System.out.println("Put ne postoji.");
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.printf("Koordinate (%d, %d) su van okvira niza lavirinta! \n", x, y);
			}
		}
	}
}
