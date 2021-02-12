package prakticne_vezbe.pv05_lavirint_practice_p02;

import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;

public class PostojanjePuta {

	public static final int RUPA 	= -1 ;
	public static final int ZID 	= -11;
	public static final int IZLAZ 	= -99;
	
	private static int sirina, visina;
	
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
		
		if (!Svetovid.testIn(fajl))
			return false;
		
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
	
	public static boolean postojiPut(int x, int y, List<String> polja, boolean rupa) {
		
		if (x < 0 || x >= sirina || y < 0 || y >= visina)
			return false;
		
		if (lavirint[x][y] == ZID)
			return false;
		
		if (posecenost[x][y])
			return false;
		
		if (rupa && lavirint[x][y] == RUPA)
			return false;
		
		if (lavirint[x][y] == IZLAZ) {
			polja.add(x + "-" + y);
			posecenost[x][y] = true;
			
			return true;
		}
		
		polja.add(x + "-" + y);
		posecenost[x][y] = true;
		
		if (postojiPut(x + 1, y, polja, lavirint[x][y] == RUPA)) return true;
		if (postojiPut(x - 1, y, polja, lavirint[x][y] == RUPA)) return true;
		if (postojiPut(x, y + 1, polja, lavirint[x][y] == RUPA)) return true;
		if (postojiPut(x, y - 1, polja, lavirint[x][y] == RUPA)) return true;
		
		polja.remove(x + "-" + y);
		posecenost[x][y] = false;
		
		return false;
	}
	
	public static void print(int a) {
		
		System.out.println(sirina + " " + visina);
		System.out.println("Lavirint: ");
		for (int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				if (a == 0) System.out.printf("%1$5d", lavirint[i][j]);
				else
					if (posecenost[i][j])
							System.out.print("    X");
					else
							System.out.print("    -");
			}

			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		if (ucitaj(fajlovi[5])) {
			
			print(0);
			
			List<String> polja = new ArrayList<String>();
			if (postojiPut(0, 0, polja, lavirint[0][0] == RUPA)) {
				print(1);
			} else {
				System.out.println("Put ne postoji.");
			}
		}
	}
}
