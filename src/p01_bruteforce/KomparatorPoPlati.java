package p01_bruteforce;

import java.util.Comparator;

class KomparatorPoPlati implements Comparator<Radnik> {
	
	public int compare(Radnik a, Radnik b) {
		return a.getPlata() - b.getPlata();
	}
}
