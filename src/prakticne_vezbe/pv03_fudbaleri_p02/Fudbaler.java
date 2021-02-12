package prakticne_vezbe.pv03_fudbaleri_p02;

public class Fudbaler {

	private final String ime;
	private String 		 poz;
	private final int	 god;
	private boolean prvaPost;
	
	public Fudbaler(String ime, String poz, String god, String prvaPost) {
		this.ime = ime;
		setPoz(poz);
		this.god = Integer.parseInt(god);
		setPrvaPost(prvaPost);
	}
	
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
		
		Fudbaler f = (Fudbaler) o;
		
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
}
