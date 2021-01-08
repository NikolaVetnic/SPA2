package pv05_lavirint_practice_p02;

import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;

public class NalazenjePutaBlago {

	/*
	 * Ovo je verzija zadatka sa uradjenim petim domacim zadatkom posle
	 * sestih prakticnih vezbi. Zadaci:
	 * 1. ispisati sve nadjene puteve,
	 * 2. prebrojati sve puteve
	 * 3. prebrojati sve puteve sa vise od tri uzastopna blaga
	 */
	
	private static final int IZLAZ 	= -99;
	private static final int ZID	= -11;
	
	private static int visina, sirina;
	
	private static int[][] lavirint;
	private static boolean[][] posecenost;
	
	private static List<ArrayList<String>> nadjeniPutevi;
	
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
	
	public static void stampaj(int a) {
		
		System.out.println(sirina + " " + visina);
		System.out.println("Lavirint: ");
		
		for (int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				System.out.printf("%1$5d", a == 0 ? lavirint[i][j] : (posecenost[i][j] ? 1 : 0));
			}
			System.out.println();
		}			
	}
}
