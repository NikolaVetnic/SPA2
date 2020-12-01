package game_of_life;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Map {
	

	public int[][] map;

	
	public Map(int[][] map) {
		this.map = map;
	}
	
	
	public Map(int x, int y, long seed, double prob) {
		
		Random rng = new Random(seed);
		
		this.map = new int[x][y];
		
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				this.map[i][j] = rng.nextDouble() < prob ? 1 : 0;
	}
	
	
	public Map(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(file));

		@SuppressWarnings("unused")
		String line = null;
		int numLines = 0;
		
		while ((line = br.readLine()) != null)
			numLines++;
		
		br.close();
		
		br = new BufferedReader(new FileReader(file));
		
		map = new int[numLines][];
		
		for (int i = 0; i < numLines; i++) {
			
			String[] tokens = br.readLine().split(" ");
			map[i] = new int[tokens.length]; 
			
			for (int j = 0; j < map[i].length; j++)
				map[i][j] = Integer.parseInt(tokens[j].trim());
		}
		
		br.close();
	}
	
	
	public void print() {
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++)
				System.out.print(map[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	
	public int getNeighboursFor(int x, int y) {
		
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
	
	
	public void printNeighbours() {

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
	
	
	private int[][] copyArr() {
		
		int[][] out = new int[map.length][map[0].length];
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				out[i][j] = map[i][j];
		
		return out;
	}
	
	
	private Map copyMap() {
		return new Map(this.copyArr());
	}
	
	
	public int[][] advance() {
		
		int[][] curr = copyArr();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int n = Util.getNeighboursFor(curr, i, j);
				
				if (curr[i][j] == 1 && (2 <= n && n < 4))
					;
				else if (curr[i][j] == 0 && n == 3)
					map[i][j] = 1;
				else if (curr[i][j] == 1)
					map[i][j] = 0;
			}
		}
		
		return this.copyArr();
	}
	
	
	public Map[] recordEpochs(int n) {
		
		Map[] rec = new Map[n];
		
		rec[0] = this.copyMap();
		
		for (int i = 1; i < n; i++)
			rec[i] = new Map(advance());
		
		return rec;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		Map[] ma = new Map("map_test").recordEpochs(3);
		
		for (Map map : ma) map.print();
	}
}
