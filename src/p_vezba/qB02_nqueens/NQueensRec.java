package p_vezba.qB02_nqueens;

public class NQueensRec {

	
	private int n;
	private int slCounter;
	private int[] table;
	
	
	public NQueensRec(int n) {
		this.n = n;
		table = new int[n];
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
		find(0);
	}


	private void find(int currentRow) {
		
		if (currentRow == n) {
			printSolution();
		} else {
			for (int j = 0; j < n; j++) {
				if (availableCell(currentRow, j)) {
					table[currentRow] = j;
					find(currentRow + 1);
				}
			}
		}
	}


	private boolean availableCell(int currentRow, int currentColumn) {
		
		for (int i = 0; i < currentRow; i++)
			if (table[i] == currentColumn || Math.abs(i - currentRow) == Math.abs(table[i] - currentColumn))
				return false;
		
		return true;
	}
	
	public static void main(String[] args) {
		NQueens nq = new NQueens(8);
		nq.find();
	}
}
