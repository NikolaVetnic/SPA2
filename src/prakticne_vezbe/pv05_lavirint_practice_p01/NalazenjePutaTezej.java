package prakticne_vezbe.pv05_lavirint_practice_p01;

import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;

public class NalazenjePutaTezej {
	
	
	public static final int ARIJADNA =  30;
	public static final int JAZBINA  =  25;
	public static final int MINOTAUR =  20;
	public static final int ZID 	 = -11;
	
	
	static class Put implements Comparable<Put> {
		
		List<String> polja;
		int vrednost;
		
		public Put() {
			this.polja = new ArrayList<String>();
			this.vrednost = 0;
		}
		
		public void dodajPolje(int x, int y, int v) {
			polja.add(x + "-" + y);
			vrednost += v;
		}
		
		public void izbaciPolje(int x, int y, int v) {
			polja.remove(x + "-" + y);
			vrednost -= v;
		}
		
		public Put kopija() {
			Put out = new Put();
			for (String polje : this.polja) out.polja.add(polje);
			out.vrednost = this.vrednost;
			
			return out;
		}
		
		public String toString() {
			return polja.toString() + " VREDNOST : " + vrednost;
		}
		
		public void stampajKaoMapu(int sirina, int visina) {
			
			int[][] out = new int[sirina][visina];
			for (String polje : polja) {
				String[] tokens = polje.split("-");
				out[Integer.parseInt(tokens[0].trim())][Integer.parseInt(tokens[1].trim())] = 1;
			}
			
			for (int j = 0; j < visina; j++) {
				for (int i = 0; i < sirina; i++) {
					System.out.printf("%1$5d  ", out[i][j]);
				}
				System.out.println();
			}
		}
		
		@Override
		public int compareTo(Put o) {
			return this.vrednost - o.vrednost;
		}
	}
	
	
	private static int sirina, visina;
	
	private static int[][] lavirint;
	private static boolean[][] posecenost;
	
	private static List<Put> nadjeniPutevi;
	
	private static String[] fajlovi = { 
			"pv06_knossos_1",
			"pv06_knossos_2",
			"pv06_knossos_3",
			"pv06_knossos_4",
			"pv06_knossos_5",
			"pv06_knossos_empty",
			"pv06_knossos_lav",
			"pv06_knossos_no-solution",
	};	
	
	
	public static boolean ucitaj(String input) {
		
		String fajl = "res//" + input + ".txt";
		
		if (!Svetovid.testIn(fajl))
			return false;
		
		sirina = Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();
		
		lavirint = new int[sirina][visina];
		posecenost = new boolean[sirina][visina];
		
		for (int j = 0; j < visina; j++)
			for (int i = 0; i < sirina; i++) {
				lavirint[i][j] = lavirint[i][j] == 0 ? Svetovid.in(fajl).readInt() : lavirint[i][j];
				if (lavirint[i][j] == 20) oznaciJazbinu(i, j);
			}
		
		Svetovid.in(fajl).close();
		return true;
	}
	
	
	private static void oznaciJazbinu(int i, int j) {
		
		if (i + 1 < sirina) lavirint[i + 1][j] = JAZBINA;
		if (i - 1 >= 0    ) lavirint[i - 1][j] = JAZBINA;
		if (j - 1 >= 0    ) lavirint[i][j - 1] = JAZBINA;
		if (j + 1 < visina) lavirint[i][j + 1] = JAZBINA;
	}


	public static void print(int a) {
		
		System.out.println(sirina + " " + visina);
		System.out.println("Lavirint: ");
		
		for (int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				System.out.printf("%1$5d  ", a == 0 ? lavirint[i][j] : (posecenost[i][j] ? 1 : 0));
			}
			System.out.println();
		}
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
	
	
	public static void nadjiPut(int x, int y, Put put) {
				
		if (x < 0 || sirina <= x || y < 0 || visina <= y)	// obavezan uslov #1
			return;
		
		if (lavirint[x][y] == ZID)	 						// obavezan uslov #2
			return;
		
		if (lavirint[x][y] == MINOTAUR || lavirint[x][y] == JAZBINA)
			return;

		if (posecenost[x][y] == true)						// obavezan uslov #3
			return;
		
		if (lavirint[x][y] == ARIJADNA) {					// obavezan uslov #4
			
			put.dodajPolje(x, y, lavirint[x][y]);
			nadjeniPutevi.add(put.kopija());
			
			put.izbaciPolje(x, y, lavirint[x][y]);
			return;
		}
		
		posecenost[x][y] = true;
		put.dodajPolje(x, y, lavirint[x][y]);
		
		nadjiPut(x + 1, y, put);
		nadjiPut(x - 1, y, put);
		nadjiPut(x, y + 1, put);
		nadjiPut(x, y - 1, put);
		
		posecenost[x][y] = false;
		put.izbaciPolje(x, y, lavirint[x][y]);
	}
	
	
	public static void main(String[] args) {
		
		if (ucitaj(startMenu())) {
			print(0);
			
			nadjeniPutevi = new ArrayList<Put>();
			nadjiPut(0, 0, new Put());
			
			nadjeniPutevi.sort(
					(Put p0, Put p1) -> p1.vrednost - p0.vrednost != 0 ? 
										p1.vrednost - p0.vrednost : (p1.polja.size() - p0.polja.size()));
			
			for (Put p : nadjeniPutevi) System.out.println(p);
			
			System.out.println("NAJBOLJI PUT : ");
			nadjeniPutevi.get(0).stampajKaoMapu(sirina, visina);
		}
	}
}