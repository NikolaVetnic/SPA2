package usmeni_ispit.qB08_string_distance;

public class Prirastaji {

	public static void main(String[] args) {
		
		int[][] A = { 	{ 1, 2, 3 },
						{ 4, 5, 6 },
						{ 7, 8, 9 }
					};
		
		int[] dx = { -1, -1, -1,  0,  0,  1,  1,  1 };
		int[] dy = { -1,  0,  1, -1,  1, -1,  0,  1 };
		
		int i = 1, j = 1;
		
		System.out.println("Murovo susedstvo za element A[1][1]: ");
		for (int k = 0; k < dx.length; k++)
			System.out.println(A[i + dx[k]][j + dy[k]]);
	}
}
