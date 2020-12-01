package game_of_life;

public class Util {
	
	public static void printArr(int[][] map) {
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++)
				System.out.print(map[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public static int getNeighboursFor(int[][] map, int x, int y) {
		
		int xx = map.length;
		int yy = map[0].length;
		
		int xr = (x + 1 + xx) % xx;
		int xl = (x - 1 + xx) % xx;
		int yd = (y + 1 + yy) % yy;
		int yu = (y - 1 + yy) % yy;
		
		int[] n = { 
				map[xl][yu], map[x ][yu], map[xr][yu],
				map[xl][y ],              map[xr][y ],
				map[xl][yd], map[x ][yd], map[xr][yd]
		};
		
		int count = 0;
		
		for (int v : n)
			if (v != 0) count++;
		
		return count;
	}
	
	public static void neighbours(int[][] map) {

		int xx = map.length;
		int yy = map[0].length;
		
		for (int i = 0; i < xx; i++) {
			for (int j = 0; j < yy; j++) {
				
				int xr = (i + 1 + xx) % xx;
				int xl = (i - 1 + xx) % xx;
				int yd = (j + 1 + yy) % yy;
				int yu = (j - 1 + yy) % yy;
				
				int[] n = { 
						map[xl][yu], map[i ][yu], map[xr][yu],
						map[xl][j ],              map[xr][j ],
						map[xl][yd], map[i ][yd], map[xr][yd]
				};
				
				int count = 0;
				
				for (int v : n)
					if (v != 0) count++;
				
				System.out.print(count + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int[][] copyArr(int[][] map) {
		
		int[][] out = new int[map.length][map[0].length];
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				out[i][j] = map[i][j];
		
		return out;
	}
	
	public static int[][] cycle(int[][] map) {
		
		int[][] out = copyArr(map);
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int n = Util.getNeighboursFor(out, i, j);
				
				if (out[i][j] == 1 && (2 <= n && n <= 3))
					;
				else if (out[i][j] == 0 && n == 3)
					map[i][j] = 1;
				else if (out[i][j] == 1)
					map[i][j] = 0;
			}
		}
		
		printArr(map);
		
		return map;
	}
	
	public static int[][][] thousandMoves(int[][] map) {
		
		int[][][] out = new int[1000][map.length][map[0].length];
		
		for (int i = 0; i < out.length; i++)
			out[i] = cycle(map);
		
		return out;
	}

	public static void main(String[] args) {
		
		int[][] map = { 
				{ 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 0 },
				{ 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 },
		};
		
		printArr(map);
		
		int count = 0;
		
		while (count++ < 10) {
			
			cycle(map);
		}
	}
}
