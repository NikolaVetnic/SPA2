package pv05_lavirint_practice_p01;

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
	

	public static void nadjiPut(int x, int y, List<String> polja) {
		
		if (x < 0 || sirina <= x || y < 0 || visina <= y)	// obavezan uslov #1
			return;
		
		if (lavirint[x][y] == ZID)	 						// obavezan uslov #2
			return;

		if (posecenost[x][y] == true)						// obavezan uslov #3
			return;
		
		if (lavirint[x][y] == IZLAZ) {						// obavezan uslov #4
			
			polja.add(y + "-" + x);
			
			nadjeniPutevi.add((ArrayList<String>) kopirajPut(polja));
			polja.remove(y + "-" + x);
			
			return;
		}
		
		posecenost[x][y] = true;
		polja.add(y + "-" + x);
		
		nadjiPut(x + 1, y, polja);
		nadjiPut(x - 1, y, polja);
		nadjiPut(x, y + 1, polja);
		nadjiPut(x, y - 1, polja);
		
		posecenost[x][y] = false;
		polja.remove(y + "-" + x);
	}
	
	
	public static List<String> kopirajPut(List<String> l) {
		
		List<String> out = new ArrayList<String>();
		for (String s : l) out.add(s);
		return out;
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
	
	
	public static int prebrojSaTriBlaga() {
		
		int count = 0;
		
		for (ArrayList<String> put : nadjeniPutevi) {
			
			int inRow = 0;
			
			for (String polje : put) {
				if (vrednostPolja(polje) > 0) inRow++;
				else inRow = 0;
				
				if (inRow > 3) {
					count++;
					break;
				}
			}
		}
		
		return count;
	}
	
	
	private static int vrednostPolja(String polje) {
		String[] tokens = polje.split("-");
		return lavirint[Integer.parseInt(tokens[1].trim())][Integer.parseInt(tokens[0].trim())];
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
			nadjeniPutevi = new ArrayList<ArrayList<String>>();
			
			int x = Svetovid.in.readInt("Unesite x koordinatu pocetka: ");
			int y = Svetovid.in.readInt("Unesite y koordinatu pocetka: ");
			
			try {
				
				System.out.println();
				nadjiPut(x, y, polja);
				System.out.println("Pronadjeno puteva : [" + nadjeniPutevi.size() + "] "
								 + "(od toga sa vise od tri uzastopna blaga : " + prebrojSaTriBlaga() + ")");
				System.out.println("Svi putevi : ");
				for (ArrayList<String> put : nadjeniPutevi) System.out.println(put);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.printf("Koordinate (%d, %d) su van okvira niza lavirinta!\n", x, y);
			}
		}
	}
}
