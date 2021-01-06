package pv05_lavirint_practice_p01;

import java.util.List;

public class PostojiPutBareBones {
	
	/*
	 * Najosnovnija verzija postojiPut() metoda bez ikakvih dodataka za
	 * podsecanje na brzinu.
	 */ 
	
	private static final int IZLAZ 	= -99;
	private static final int ZID	= -11;
	
	private static int visina, sirina;
	
	private static int[][] lavirint;
	private static boolean[][] posecenost;

	public static boolean postojiPut(int x, int y, List<String> polja) {
		
		if (x < 0 || sirina <= x || y < 0 || visina <= y)	// obavezan uslov #1
			return false;
		
		if (lavirint[x][y] == ZID)							// obavezan uslov #2
			return false;

		if (posecenost[x][y] == true)						// obavezan uslov #3
			return false;
		
		if (lavirint[x][y] == IZLAZ) {						// obavezan uslov #4
			posecenost[x][y] = true;
			polja.add(y + "-" + x);
			return true;
		}
		
		posecenost[x][y] = true;
		polja.add(y + "-" + x);
		
		if (postojiPut(x + 1, y, polja)) return true;
		if (postojiPut(x - 1, y, polja)) return true;
		if (postojiPut(x, y + 1, polja)) return true;
		if (postojiPut(x, y - 1, polja)) return true;
		
		posecenost[x][y] = false;
		polja.remove(y + "-" + x);
		
		return false;
	}
}
