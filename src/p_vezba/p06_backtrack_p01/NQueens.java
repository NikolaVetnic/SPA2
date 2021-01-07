package p_vezba.p06_backtrack_p01;

public class NQueens {

	int n;
	int slCounter;
	int[] table;
	
	static class AvailableCell {
		int y;
		AvailableCell next;
		
		public AvailableCell(int y) {
			this.y = y;
		}
	}
	
	AvailableCell[] availableCells;
	
	public NQueens(int n) {
		this.n = n;
		this.table = new int[n];
		this.availableCells = new AvailableCell[n];
	}
	
	public void printSolution() {
		
		slCounter++;
		System.out.println("Solution " + slCounter);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (table[i] == j)
					System.out.print("X ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void find() {
		
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
				
				if (currentRow == n - 1)
					printSolution();
				else {
					currentRow++;
					findAvailableCells(currentRow);
				}
			}
			currentRow--;
		}
	}

	private void findAvailableCells(int currentRow) {
		
		availableCells[currentRow] = null;
		
		for (int j = n - 1; j >= 0; j--) {
			
			boolean available = true;
			
			for (int i = 0; i < currentRow; i++) {
				if (table[i] == j || Math.abs(i - currentRow) == Math.abs(table[i] - j)) {
					available = false;
					break;
				}
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
