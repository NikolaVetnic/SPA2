package p06_backtrack_nqueens;

public class NQueens {
	

	private int n;			// velicina table
	private int slCounter;	// brojac resenja
	private int[] table;	// raspored kraljica

	
	// lista raspolozivih polja za neku vrstu
	private class AvailableCell {
		int y;
		AvailableCell next;
		
		public AvailableCell(int y) {
			this.y = y;
		}
	}
	
	
	// availableCells[i] - lista raspolozivih polja za i-tu vrstu
	private AvailableCell[] availableCells;
	
	
	public NQueens(int n) {
		this.n = n;
		table = new int[n];
		availableCells = new AvailableCell[n];
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
		
		/*
		 * Formiranje liste raspolozivih polja za prvu vrstu, u sustini kr-
		 * eira listu koja ce imati sadrzaj { 0, 1 .. n-1 }, tim redom.
		 */
		for (int i = n - 1; i >= 0; i--) {
			AvailableCell c = new AvailableCell(i);
			c.next = availableCells[0];
			availableCells[0] = c;
		}
		
		int currentRow = 0;
		while (currentRow >= 0) {
			while (availableCells[currentRow] != null) {
				table[currentRow] = availableCells[currentRow].y;
				availableCells[currentRow] = availableCells[currentRow].next;
				
				if (currentRow == n - 1)	// da li imamo resenje?
					printSolution();
				else {						// nemamo resenje - idi napred
					currentRow++;
					findAvailableCells(currentRow);
				}
			}
			currentRow--;					// sve smo isprobali i nema resenja - idi nazad
		}
	}


	private void findAvailableCells(int currentRow) {
		
		availableCells[currentRow] = null;
		
		/*
		 * Prover
		 * Formiranje liste raspolozivih polja za prvu vrstu, u sustini kr-
		 */ 
		for (int j = n - 1; j >= 0; j--) {
			
			/*
			 * Proveravamo da li je polje (currentRow, j) dostupno tako sto
			 * za sve prethodne vrste i, 0 <= i < currentRow proveravamo da
			 * li je polje (i, table[i]) u koliziji sa poljem pozicije (cu-
			 * rrentRow, j); kolizija se desava ako je:
			 * 1. table[i] = j (kraljice se napadaju "vertikalno")
			 * 2. |i - currentRow| = |table[i] - j| (napad "dijagonalno")
			 */
			boolean available = true;
			for (int i = 0; i < currentRow; i++)
				if ((table[i] == j) || Math.abs(i - currentRow) == Math.abs(table[i] - j)) {
					available = false;
					break;
				}
			
			if (available) {
				AvailableCell c = new AvailableCell(j);
				c.next = availableCells[currentRow];
				availableCells[currentRow] = c;
			}
		}
	}
	
	public static void main(String[] args) {
		NQueens nq = new NQueens(8);
		nq.find();
	}
}
