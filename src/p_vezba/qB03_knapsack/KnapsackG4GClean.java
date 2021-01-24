package p_vezba.qB03_knapsack;

public class KnapsackG4GClean {

	
	public static int knapsack(int W, int[] wgt, int[] val, int n) {
		
		if (W == 0 || n == 0)
			return 0;
		
		if (wgt[n - 1] > W)
			return knapsack(W, wgt, val, n - 1);
		else
			return Math.max(val[n - 1] + knapsack(W - wgt[n - 1], wgt, val, n - 1), 
										 knapsack(W, 			  wgt, val, n - 1));
	}
	
	
	public static void main(String[] args) {
		
		int val[] = new int[] { 28, 22, 18, 6, 1 }; 
        int wt[] = new int[] { 7, 6, 5, 2, 1 }; 
		
        int W = 11; 
        int n = val.length;
        
        System.out.println(knapsack(W, wt, val, n)); 
	}
}
