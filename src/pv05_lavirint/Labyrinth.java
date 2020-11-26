package pv05_lavirint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mars.geometry.Vector;

public class Labyrinth {
	

    public static final int EXIT = -99;
    public static final int WALL = -11;
    
    private static final int MAX_OBSTACLES = 3;

    
    private final Vector size;
	
    private final int[][] tiles;
    private final boolean[][] visited;
	
	
	public Labyrinth(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String[] tokens = br.readLine().split(" ");
		size = Vector.xy(
				Integer.parseInt(tokens[1].trim()), 
				Integer.parseInt(tokens[0].trim()));
		tiles = new int[size.xInt()][size.yInt()];
		visited = new boolean[size.xInt()][size.yInt()];
		
		String line;
		int count = 0;
		
		while ((line = br.readLine()) != null) {
			
			tokens = line.trim().split("\\s+");
			
			for (int i = 0; i < tokens.length; i++)
				tiles[count][i] = Integer.parseInt(tokens[i].trim());
			
			count++;
		}
		
		br.close();
	}
	
	
	public static Labyrinth load(String input) throws IOException {
		return new Labyrinth(input);
	}
	
	
	public void print() {
		
		System.out.printf("%d %d\n", size.yInt(), size.xInt());

        System.out.println("Labyrinth layout: ");
        
		for (int i = 0; i < (int) size.xInt(); i++) {
			
			for (int j = 0; j < size.yInt(); j++)
				System.out.printf("%4d", tiles[i][j]);

			System.out.println();
		}
	}
	

    public void printVisited () {
    	
        System.out.println(size.yInt() + " " + size.xInt());
        
        System.out.println("Visited tiles: ");
        
        for (int j = 0; j< size.yInt(); j++) {
        	for (int i = 0; i< size.xInt(); i++)
                System.out.printf("%1$5d",visited[i][j]?1:0);
            
            System.out.println();
        }
    }
    

    public int[][] findPath(int x, int y) {
        
    	ArrayList<String> path = new ArrayList<>();
    	
    	if (!this.pathExists(x, y, path, false, tiles[x][y] > 0 ? tiles[x][y] : 0, 0))
    		return null;
    	
    	int[][] arr = new int[path.size()][2];
    	
    	for (int i = 0; i < arr.length; i++) {
    		
    		String[] tokens = path.get(i).split("-");
    		
    		arr[i][0] = Integer.parseInt(tokens[0].trim());
    		arr[i][1] = Integer.parseInt(tokens[1].trim());
    	}
    	
    	return arr;
    }
	

	public Vector size() 		{ return size; 						}
	public int tile(Vector p)	{ return tiles[p.xInt()][p.yInt()];	}
	public int[][] tiles() 		{ return tiles; 					}

	
	private boolean pathExists(int x, int y, List<String> pathTiles, boolean hole, int currObstacle, int obstaclesJumped){

        if (0 > x || size.xInt() <= x || 0 > y || size.yInt() <= y)
            return false;

        if (tiles[x][y] == WALL)
            return false;

        if (visited[x][y])
            return false;

        if (tiles[x][y] == EXIT) {
        	pathTiles.add(x + "-" + y);
            return true;
        }

        if (hole && tiles[x][y] == -1)
            return false;
        
        // assignment #2
        boolean allowClimbingToHeight3 = false;
        if (allowClimbingToHeight3 && tiles[x][y] > 0 && tiles[x][y] - currObstacle > 3)
        	return false;
        
        // assignment #3
        boolean allowJumping = true;
        if (allowJumping && tiles[x][y] > 0 && obstaclesJumped > MAX_OBSTACLES)
    		return false;

        visited[x][y] = true;
        pathTiles.add(x + "-" + y);
        if (tiles[x][y] > 0) obstaclesJumped++;
        
        // right - left - up - down
        if (pathExists(x + 1, y, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
        if (pathExists(x - 1, y, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
        if (pathExists(x, y + 1, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
        if (pathExists(x, y - 1, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
        
        // assignment #1
        boolean diagonalAllowed = false;
        if (diagonalAllowed) {
        	if (pathExists(x + 1, y + 1, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
            if (pathExists(x - 1, y + 1, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
            if (pathExists(x + 1, y - 1, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
            if (pathExists(x - 1, y - 1, pathTiles, tiles[x][y] == -1, tiles[x][y] > 0 ? tiles[x][y] : 0, obstaclesJumped)) return true;
        }

        visited[x][y] = false;
        pathTiles.remove(x + "-" + y);
        if (tiles[x][y] > 0) obstaclesJumped--;

        return false;
    }
}
