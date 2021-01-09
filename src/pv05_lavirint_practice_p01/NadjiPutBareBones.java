package pv05_lavirint_practice_p01;

import java.util.ArrayList;
import java.util.List;

public class NadjiPutBareBones {
	
	
	public static final int IZLAZ 	=  99;
	public static final int ZID 	= -11;
	
	
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
	
	
	public static void nadjiPut(int x, int y, Put put) {
		
		if (x < 0 || sirina <= x || y < 0 || visina <= y)	// obavezan uslov #1
			return;
		
		if (lavirint[x][y] == ZID)	 						// obavezan uslov #2
			return;

		if (posecenost[x][y] == true)						// obavezan uslov #3
			return;
		
		if (lavirint[x][y] == IZLAZ) {						// obavezan uslov #4
			
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
}
