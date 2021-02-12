package usmeni_ispit.qB03_knapsack;

public class KnapsackG4G {

	private static int max(int a, int b) {
		return a > b ? a : b;
	}
	
	public static int knapsack(int W, int[] wgt, int[] val, int n) {
	
		/*
		 * Naivno rekurzivno resenje problema rukzaka.
		 */ 
		
		/*
		 * Trivijalni slucaj iliti izlaz iz rekurzije, kada je tezina ranca
		 * nula ili nije preostalo predmeta.
		 */ 
		if (W == 0 || n == 0)
			return 0;

		/*
		 * Ako je predmet koji trenutno ubacujemo tezi od tezine koju tren-
		 * utno prima ranac odustajemo od ubacivanja.
		 */ 
		if (wgt[n - 1] > W)
			return knapsack(W, wgt, val, n - 1);
		/*
		 * Ako nije uzimamo vecu od dve varijante, kada kada ubacimo predm-
		 * et i kada ga ne ubacimo.
		 */
		else
			return max(
					val[n - 1] + knapsack(W - wgt[n - 1], wgt, val, n - 1),
					knapsack(W, wgt, val, n - 1));
	}
	
	public static void main(String[] args) {
		
		int val[] = new int[] { 28, 22, 18, 6, 1 }; 
        int wt[] = new int[] { 7, 6, 5, 2, 1 }; 
		
        int W = 11; 
        int n = val.length;
        
        System.out.println(knapsack(W, wt, val, n)); 
	}
}
