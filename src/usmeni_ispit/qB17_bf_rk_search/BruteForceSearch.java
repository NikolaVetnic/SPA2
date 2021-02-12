package usmeni_ispit.qB17_bf_rk_search;

public class BruteForceSearch {

	public static int search(String pat, String txt) {
		
		if (pat.length() > txt.length())
			throw new IllegalArgumentException("Pattern duzi od teksta");
		
		for (int i = 0; i < txt.length() - pat.length(); i++) {
			
			boolean match = true;
			int j = 0;
			
			while (match && j < pat.length())
				if (pat.charAt(j) != txt.charAt(i + j))
					match = false;
				else
					j++;
			
			if (match) return i;
		}
		
		return -1;
	}
}
