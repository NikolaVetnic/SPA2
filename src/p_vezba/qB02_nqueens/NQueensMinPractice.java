package p_vezba.qB02_nqueens;

public class NQueensMinPractice {

	
	int n;
	int slCounter;
	int[] table;
	
	
	int[] firstAvailableCell;
	
	
	public NQueensMinPractice(int n) {
		this.n = n;
		this.slCounter = 0;
		this.table = new int[n];
	}
	
	
	void find() {
		
		firstAvailableCell[0] = 0;
		int currRow = 0;
		
		while (currRow >= 0) {
			
			while (firstAvailableCell[currRow] <= n) {
				
				table[currRow] = firstAvailableCell[currRow];
				firstAvailableCell[currRow]++;
				updateFirstAvailableCell(currRow);
				
				if (currRow == n - 1) {
					System.out.println("Solution");
				} else {
					currRow++;
					firstAvailableCell[currRow] = 0;
					updateFirstAvailableCell(currRow);
				}
			}
			
			currRow--;
		}
	}


	private void updateFirstAvailableCell(int currRow) {
		
		for (int j = firstAvailableCell[currRow]; j < n; j++) {
			
			boolean available = true;
			for (int i = 0; i < currRow; i++)
				if (table[i] == j || Math.abs(table[i] - j) == Math.abs(i - currRow)) {
					available = false;
					break;
				}
			
			if (available) {
				firstAvailableCell[currRow] = j;
				return;
			}
		}
		
		firstAvailableCell[currRow] = n;
	}
}
