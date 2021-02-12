package usmeni_ispit.qB02_nqueens;

public class NQueensMin {
	

	private int n;
	private int slCounter;
	private int[] table;
	
	
	// firstAvailableCell[i] - prvo dostupno polje za vrstu i
	private int[] firstAvailableCell;
	
	
	public NQueensMin(int n) {
		this.n = n;
		table = new int[n];
		firstAvailableCell = new int[n];
	}
	
	
	private void printSolution() {
		
		++slCounter;
		System.out.println("Solution " + slCounter);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j == table[i])
					System.out.print("X ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public void find() {
		
		firstAvailableCell[0] = 0;
		int currentRow = 0;
		
		while (currentRow >= 0) {
			
			while (firstAvailableCell[currentRow] < n) {
				
				table[currentRow] = firstAvailableCell[currentRow];
				firstAvailableCell[currentRow]++;
				updateFirstAvailableCell(currentRow);
				
				if (currentRow == n - 1) {
					printSolution();
				} else {
					currentRow++;
					firstAvailableCell[currentRow] = 0;
					updateFirstAvailableCell(currentRow);
				}
			}
			
			currentRow--;
		}
	}


	private void updateFirstAvailableCell(int currentRow) {
		
		for (int j = firstAvailableCell[currentRow]; j < n; j++) {
			
			/*
			 * Proveravamo da li je polje (currentRow, j) u koliziji sa ostalim
			 * poljima.
			 */
			boolean available = true;
			for (int i = 0; i < currentRow; i++) {
				if (table[i] == j || Math.abs(i - currentRow) == Math.abs(table[i] - j)) {
					available = false;
					break;
				}
			}
			
			if (available) {
				firstAvailableCell[currentRow] = j;
				return;
			}
		}
		
		// nema dostupne celije - prekidamo potragu i vracamo se nazad
		firstAvailableCell[currentRow] = n;
	}
	
	public static void main(String[] args) {
		NQueens nq = new NQueens(8);
		nq.find();
	}
}
