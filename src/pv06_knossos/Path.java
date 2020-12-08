package pv06_knossos;

public class Path {
	
	
	public static final int MAX_MOVES = 1000;
	

	private int[][] path;
	private int movesCount;
	
	
	public Path() {
		this.path = new int[MAX_MOVES][3];
		this.movesCount = 0;
	}
	
	
	public int[][] path() 			{ return path; 			}
	public int x(int move)			{ return path[move][0];	}
	public int y(int move)			{ return path[move][1];	}
	public int value(int move) 		{ return path[move][2]; }
	public int length()				{ return movesCount;	}
	
	
	public int pathValue() {
		
		int sum = 0;
		
		for (int i = 0; i < path.length; i++) 
			if (0 < path[i][2] && path[i][2] <= 10)
				sum += path[i][2];
		
		return sum;
	}
	
	
	public void print() {
		for (int i = 0; i < movesCount; i++)
			System.out.print("[(" + path[i][0] + "," + path[i][1] + "), " + path[i][2] + "] ");
	}
	
	
	public boolean add(int x, int y, int v) {
		
		if (movesCount == MAX_MOVES)
			return false;
		
		path[movesCount][0] = x;
		path[movesCount][1] = y;
		path[movesCount][2] = v;
		movesCount++;
		
		return true;
	}
	
	
	public boolean removeLast() {
		
		if (movesCount == 0)
			return false;
		
		movesCount--;
		
		return true;
	}
	

	public Path copy() {
		
		Path out = new Path();
		
		for (int i = 0; i < movesCount; i++)
			out.add(path[i][0], path[i][1], path[i][2]);
		
		return out;
	}
}
