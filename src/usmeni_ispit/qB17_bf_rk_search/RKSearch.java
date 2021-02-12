package usmeni_ispit.qB17_bf_rk_search;

public class RKSearch {

	
	private static final int LP = 15485863;
	private String pat, txt;
	private int patH;			// hash kod od pattern
	private int txtH;			// hash kod od podstringa txt
	private int fcFactor;		// 31^L % LP
	
	
	public RKSearch(String pat, String txt) {
		
		if (pat.length() > txt.length())
			throw new IllegalArgumentException("Pattern duzi od teksta");
		
		this.pat = pat;
		this.txt = txt;
		
		patH = computeHash(pat, pat.length());
		txtH = computeHash(txt, pat.length());
		
		fcFactor = 1;
		for (int i = 0; i < pat.length() - 1; i++)
			fcFactor = (fcFactor * 31) % LP;
	}

	
	/*
	 * Racuna hash za prvih len karaktera str.
	 */ 
	private int computeHash(String str, int len) {
		
		int hash = 0;
		
	/*
	 * Dopuna Hornerove seme sa ostatkom pri deljenju, pri cemu je osn-
	 * ova izraza : (a[n] * x + a[n - 1])
	 */ 
		
		for (int i = 0; i < len; i++)
			hash = ((31 * hash) % LP + str.charAt(i)) % LP;
		
		return hash;
	}
	
	
	/*
	 * Azurira vrednost hash funkcije za podstring txt tako sto se bri-
	 * se karakter na poziciji pos i dodaje prvi sledeci karakter (koji
	 * je na poziciji pos + pat.length).
	 */ 
	private void updateTextHash(int pos) {
		txtH = (LP + txtH - (txt.charAt(pos) * fcFactor) % LP) % LP;
		txtH = ((31 * txtH) % LP + txt.charAt(pos + pat.length())) % LP;
	}
	
	
	public int search() {
		
		int lastAlignment = txt.length() - pat.length();
		
		for (int i = 0; i <= lastAlignment; i++) {
			
			if (patH == txtH) {
				
				boolean match = true;
				int j = 0;
				
				while (match && j < pat.length())
					if (pat.charAt(j) != txt.charAt(i + j))
						match = false;
					else
						j++;
				
				if (match) return i;
			}
			
			if (i < lastAlignment) updateTextHash(i);
		}
		
		return -1;
	}
}
