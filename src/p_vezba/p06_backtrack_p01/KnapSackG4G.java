package p_vezba.p06_backtrack_p01;

public class KnapSackG4G {

	private static int max(int a, int b) {
		return a > b ? a : b;
	}
	
	public static int knapSack(int W, int[] wt, int[] val, int n) {
		
		if (W == 0 || n == 0)
			return 0;
		
		if (wt[n - 1] > W)
			return knapSack(W, wt, val, n - 1);
		else
			return max(
					val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1),
					knapSack(W, wt, val, n - 1));
	}
	
	public static void main(String[] args) {
		
		int val[] = new int[] { 28, 22, 18, 6, 1 }; 
        int wt[] = new int[] { 7, 6, 5, 2, 1 }; 
		
        int W = 11; 
        int n = val.length;
        
        System.out.println(knapSack(W, wt, val, n)); 
	}
}
