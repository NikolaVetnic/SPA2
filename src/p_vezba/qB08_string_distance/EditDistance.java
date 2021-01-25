package p_vezba.qB08_string_distance;

public class EditDistance {

	
	private String s1, s2;		// ulazni stringovi
	private int[][] d;			// matrica distanci
	
	
	public EditDistance(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
		d = new int[s1.length() + 1][s2.length() + 1];
		computeDistance();
	}


	private void computeDistance() {
		
		for (int i = 0; i < d.length; i++) d[i][0] = i;
		for (int i = 0; i < d[0].length; i++) d[0][i] = i;
		
		for (int i = 1; i < d.length; i++) {
			for (int j = 1; j < d[0].length; j++) {
				
				char c1 = s1.charAt(i - 1);
				char c2 = s2.charAt(j - 1);
				
				if (c1 == c2) {
					d[i][j] = d[i - 1][j - 1];
				} else {
					int dDel = d[i - 1][j    ];
					int dAdd = d[i    ][j - 1];
					int dSub = d[i - 1][j - 1];
					
					d[i][j] = 1 + Math.min(Math.min(dDel, dAdd), dSub);
				}
			}
		}
	}
	
	
	public int getDistance() {
		return d[s1.length()][s2.length()];
	}
	
	
	public static void main(String[] args) {
		
		EditDistance ed = new EditDistance("petar", "patrik");
		System.out.println(ed.getDistance());
	}
}
