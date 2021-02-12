package prakticne_vezbe.pv05_lavirint_practice_p02;

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
	
	
	static class Put implements Comparable<Put> {
		
		List<String> polja;
		int vrednost;
		int brBlaga;
		
		public Put() {
			this.polja = new ArrayList<String>();
			this.vrednost = 0;
			this.brBlaga = 0;
		}
		
		public void dodaj(int x, int y) {
			polja.add(y + "-" + x);
			if (lavirint[x][y] > 0) {
				vrednost += lavirint[x][y];
				brBlaga++;
			}
		}

		public void izbaci(int x, int y) {
			polja.remove(y + "-" + x);
			if (lavirint[x][y] > 0) {
				vrednost -= lavirint[x][y];
				brBlaga--;
			}
		}
		
		public Put kopija() {
			
			Put k = new Put();
			
			k.polja = new ArrayList<String>();
			for (String p : polja) k.polja.add(p);
			k.vrednost = vrednost;
			k.brBlaga = brBlaga;
			
			return k;
		}

		@Override
		public int compareTo(Put o) {
			int out = this.vrednost - o.vrednost;
			return out == 0 ? o.polja.size() - this.polja.size() : out;
		}
		
		@Override
		public String toString() {
			String out = "";
			for (String s : polja) out += s + " ";
			return out + ", vrednost : " + vrednost + " (broj blaga : " + brBlaga + " )";
		}
	}
	
	
	private static int visina, sirina;
	
	private static int[][] lavirint;
	private static boolean[][] posecenost;
	
	
	private static List<Put> nadjeniPutevi;
	
	
	private static String[] fajlovi = { 
			"lavirint_1",
			"lavirint_1_blago",
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
	
	
	public static void nadjiPut(int x, int y, Put p) {
		
		if (x < 0 || x >= sirina || y < 0 || y >= visina)
			return;
		
		if (lavirint[x][y] == ZID)
			return;
		
		if (posecenost[x][y])
			return;
		
		if (lavirint[x][y] == IZLAZ) {
			p.dodaj(x, y);
			posecenost[x][y] = true;
			
			nadjeniPutevi.add(p.kopija());
			
			p.izbaci(x, y);
			posecenost[x][y] = false;
		}
		
		p.dodaj(x, y);
		posecenost[x][y] = true;
		
		nadjiPut(x + 1, y, p);
		nadjiPut(x - 1, y, p);
		nadjiPut(x, y + 1, p);
		nadjiPut(x, y - 1, p);
		
		p.izbaci(x, y);
		posecenost[x][y] = false;
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
	
	
	public static void stampajSvePuteve() {
		
		for (Put p : nadjeniPutevi)
			System.out.println(p);
	}
	
	
	public static void main(String[] args) {
		
		if (ucitaj(fajlovi[2])) {
			stampaj(0);
			
			nadjeniPutevi = new ArrayList<Put>();
			nadjiPut(0, 0, new Put());
			
			stampajSvePuteve();
			System.out.println();
			
			nadjeniPutevi.sort(
					(Put p0, Put p1) -> p1.vrednost - p0.vrednost != 0 ? 
										p1.vrednost - p0.vrednost : (p0.polja.size() - p1.polja.size()));
			stampajSvePuteve();
		}
	}
}