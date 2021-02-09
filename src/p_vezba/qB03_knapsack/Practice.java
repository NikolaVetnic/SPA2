package p_vezba.qB03_knapsack;

public class Practice {
	
	
	int n;
	int slCounter;
	int[] table;
	
	
	int[] firstAvailableCell;
	
	
	public Practice(int n) {
		this.n = n;
		this.slCounter = 0;
		this.table = new int[n];
	}
	
	
	void find() {
		
		int k = 0;
		firstAvailableCell[k] = 0;
		
		while (k >= 0) {
			while (firstAvailableCell[k] <= n) {
				table[k] = firstAvailableCell[k];
				firstAvailableCell[k]++;
				updateFirstAvailableCell(k);
				
				if (k == n - 1) {
					System.out.println("Solution");
				} else {
					k++;
					firstAvailableCell[k] = 0;
					updateFirstAvailableCell(k);
				}
			}
			k--;
		}
	}


	private void updateFirstAvailableCell(int k) {
		
		for (int j = firstAvailableCell[k]; j < n; j++) {
			
			boolean available = true;
			for (int i = 0; i < k; i++)
				if (table[i] == j || Math.abs(table[i] - j) == Math.abs(i - k)) {
					available = false;
					break;
				}
			
			if (available) {
				firstAvailableCell[k] = j;
				return;
			}
		}
		
		firstAvailableCell[k] = n;
	}
}
