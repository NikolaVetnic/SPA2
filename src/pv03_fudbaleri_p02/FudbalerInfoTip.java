package pv03_fudbaleri_p02;

import org.svetovid.io.SvetovidReader;

public class FudbalerInfoTip extends InfoTip {

	// da se prazan konstruktor za InfoTip ne bi bunio ne sme biti final polja
	private String 	ime;
	private String 	poz;
	private int	 	god;
	private boolean prvaPost;
	
	public FudbalerInfoTip(String ime, String poz, String god, String prvaPost) {
		this.ime = ime;
		setPoz(poz);
		this.god = Integer.parseInt(god);
		setPrvaPost(prvaPost);
	}

	// prazan konstruktor potreban za TestHash program
	public FudbalerInfoTip() { }
	
	@Override
	public String toString() {
		return ime + " (" + god + "), " + poz + " [prvi tim : " + (prvaPost ? "DA" : "NE") + "]";
	}
	
	public String ime() 		{ return ime; 		}
	public String poz() 		{ return poz; 		}
	public int god() 			{ return god; 		}
	public boolean prvaPost() 	{ return prvaPost;  }
	
	public void setPoz(String s) {
		this.poz = s.equalsIgnoreCase("golman")    ? "golman" 	 :
				   s.equalsIgnoreCase("napadac")   ? "napadac" 	 :
				   s.equalsIgnoreCase("sredisnji") ? "sredisnji" : "odbrambeni";
	}
	
	public void setPrvaPost(String s) { 
		this.prvaPost = s.equalsIgnoreCase("da") ? true : false;	
	}
	
	public boolean equals(Object o) {
		
		if (this == o)							return true;
		if (o == null)							return false;
		if (this.getClass() != o.getClass())	return false;
		
		FudbalerInfoTip f = (FudbalerInfoTip) o;
		
		if (!this.ime.equalsIgnoreCase(f.ime))	return false;
		if (!this.poz.equalsIgnoreCase(f.poz))	return false;
		if (this.god != f.god)					return false;
		if (this.prvaPost != f.prvaPost)		return false;
		
		return true;
	}
	
	public int hashCode() {
		
		int out = 13;
		
		if (ime != null)	out += ime.hashCode() * 17;
		if (poz != null)	out += poz.hashCode() * 19;
							out += god * 23;
							out += (prvaPost ? 1 : 0) * 29;
							
		return out;
	}

	@Override
	public InfoTip ucitaj(SvetovidReader r) {
		
		return new FudbalerInfoTip(
				r.readLine().trim(),
				r.readLine().trim(),
				r.readLine().trim(),
				r.readLine().trim()
				);
	}
	
	public static void main(String[] args) {
		new TestHash(new FudbalerInfoTip(), "res/", "f_edit").run();
	}
}
