package prakticne_vezbe.pv06_knossos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mars.geometry.Vector;

public class Labyrinth {
	

	public static final int ARDN =  30;
	public static final int LAIR =  25;
	public static final int MNTR =  20;
    public static final int WALL = -11;

    
    private final Vector size;
    private int goal;
	
    private final int[][] tiles;
    private final boolean[][] visited;
    
    private List<Path> allPaths;
	
	
	public Labyrinth(String input, String goal) throws IOException {
		
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
		
		Vector m = Vector.x(-99.0);
		
		while ((line = br.readLine()) != null) {
			
			tokens = line.trim().split("\\s+");
			
			for (int i = 0; i < tokens.length; i++) {
				tiles[count][i] = Integer.parseInt(tokens[i].trim());
				if (tiles[count][i] == MNTR) m = Vector.xy(count, i);
			}
			
			count++;
		}
				
		br.close();

		setMinotaursLair(m);
		
		setGoal(goal);
	}
	
	
	private void setMinotaursLair(Vector m) {
		
		// Theseus avoid eight tiles around the Minotaur.
		@SuppressWarnings("unused")
		Vector[] vicinity8 = {
				Vector.xy(m.xInt() - 1, m.yInt() + 1), Vector.xy(m.xInt()    , m.yInt() + 1), Vector.xy(m.xInt() + 1, m.yInt() + 1),
				Vector.xy(m.xInt() - 1, m.yInt()    ), 										  Vector.xy(m.xInt() + 1, m.yInt()    ),
				Vector.xy(m.xInt() - 1, m.yInt() - 1), Vector.xy(m.xInt()    , m.yInt() - 1), Vector.xy(m.xInt() + 1, m.yInt() - 1),
		}; 
		
		// Theseus avoid four tiles around the Minotaur.
		Vector[] vicinity4 = {
													   Vector.xy(m.xInt()    , m.yInt() + 1),
				Vector.xy(m.xInt() - 1, m.yInt()    ), 										  Vector.xy(m.xInt() + 1, m.yInt()    ),
													   Vector.xy(m.xInt()    , m.yInt() - 1),
		}; 
		
		for (Vector v : vicinity4)
			if (insideMap(v) && isPassable(v))
				tiles[v.xInt()][v.yInt()] = 25;
	}
	
	
	private boolean isPassable(Vector tile) {										// checks if tile can be traversed
		int val = tiles[tile.xInt()][tile.yInt()];
		if (-10 <= val && val <= 10) return true;
		return false;
	}
	
	
	private boolean insideMap(Vector tile) {										// checks if tile is within map bounds
		return 0 <= tile.xInt() && tile.xInt() < tiles.length && 0 <= tile.yInt() && tile.yInt() < tiles[0].length;
	}
	
	
	public static Labyrinth load(String input, String goal) throws IOException {
		return new Labyrinth(input, goal);
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
    
    
    public void printAllPaths(int x, int y) {
    	
    	getAllPaths(x, y);
    	
    	if (allPaths == null) return;
    	
    	for (int i = 0; i < allPaths.size(); i++) {
    		allPaths.get(i).print();
    		System.out.println();
    	}
    }
	

	public Vector size() 		{ return size; 						}
	public int tile(Vector p)	{ return tiles[p.xInt()][p.yInt()];	}
	public int[][] tiles() 		{ return tiles; 					}
	
	
	public void setGoal(String goal) {												// sets the tile labeld "ardn" (Ariadne) as goal
		if ("ardn".equalsIgnoreCase(goal)) 	this.goal = ARDN;
		else								this.goal = MNTR;
	}
	
	
	public Path getOptimalPath(int x, int y, Comparator<Path> c1, Comparator<Path> c2) {
		
		if (allPaths == null) {
			allPaths = new ArrayList<Path>();
			findAllPaths(x, y, new Path());
		}
		
		if (allPaths.size() == 0) return null;
		
		allPaths.sort(c2);	// sort by length
		allPaths.sort(c1);	// sort by value
		return allPaths.get(0);
	}
	
	
	private void getAllPaths(int x, int y) {

    	allPaths = new ArrayList<Path>();
		findAllPaths(x, y, new Path());
	}

	
	private void findAllPaths(int x, int y, Path p){

        if (0 > x || size.xInt() <= x || 0 > y || size.yInt() <= y)
            return;

        if (tiles[x][y] == WALL)
            return;
        
        if (visited[x][y])
        	return;
        
        // If the goal is to reach Ariadne first Theseus avoid the Min-
        // otaur and the four/eight fields surrounding him if those are
        // traversable.
        if (goal == ARDN && (tiles[x][y] == LAIR || tiles[x][y] == MNTR))
        	return;

        if (tiles[x][y] == goal) {

        	p.add(x, y, 0);
        	allPaths.add(p.copy());
        	
        	p.removeLast();
            return;
        }

        visited[x][y] = true;
        p.add(x, y, tiles[x][y]);
        
        // right - left - up - down
        findAllPaths(x+1, y, p);
        findAllPaths(x-1, y, p);
        findAllPaths(x, y+1, p);
        findAllPaths(x, y-1, p);

        visited[x][y] = false;
        p.removeLast();
    }
	
	
	public static void main(String[] args) throws IOException {
		
		Labyrinth l = Labyrinth.load("pv06_knossos_no-solution", "ardn");
		
		l.printAllPaths(0, 0);
		System.out.println("\n\n");
		
		Path opt = l.getOptimalPath(0, 0, new CompValue(), new CompLength());
		
		if (opt != null) opt.print();
	}
}


class CompValue implements Comparator<Path> {
	
	@Override
	public int compare(Path p1, Path p2) {
		return p2.pathValue() - p1.pathValue();
	}
}


class CompLength implements Comparator<Path> {
	
	@Override
	public int compare(Path p1, Path p2) {
		return p1.length() - p2.length();
	}
}