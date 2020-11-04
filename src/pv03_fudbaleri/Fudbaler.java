package pv03_fudbaleri;

import java.util.Objects;

import org.svetovid.io.SvetovidReader;

public class Fudbaler extends InfoTip {

	private String ime, poz;
	private int god;
	private boolean prv;
	
	private Fudbaler(String ime, String poz, int god, String prv) {
		this.ime = ime;
		setPoz(poz);
		this.god = god;
		setPrv(prv);
	}
	
	private Fudbaler() { }
	
	public static Fudbaler novi(String ime, String poz, int god, String prv) {
		return new Fudbaler(ime, poz, god, prv);
	}
	
	public static Fudbaler novi() {
		return new Fudbaler();
	}
	
	public String toString() {
		return ime + " (" + god + "), " + poz + " [prvi tim : " + (prv ? "DA" : "NE") + "]";
	}
	
	public String  ime() { return ime; }
	public String  poz() { return poz; }
	public int 	   god() { return god; }
	public boolean prv() { return prv; }
	
	public void setPoz(String poz) {
		this.poz = poz.equalsIgnoreCase("golman") 	 ? "golman"    :
				   poz.equalsIgnoreCase("napadac") 	 ? "napadac"   :
				   poz.equalsIgnoreCase("sredisnji") ? "sredisnji" : "odbrambeni";
	}
	
	public void setPrv(String prv) {
		this.prv = prv.equalsIgnoreCase("da") ? true : false;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) 					return true;
		if (o == null) 					return false;
		if (getClass() != o.getClass()) return false;
		
		Fudbaler that = (Fudbaler) o;
		
		if (!Objects.equals(ime, that.ime)) return false;
		if (!Objects.equals(poz, that.poz)) return false;
		if (god != that.god				  ) return false;
		if (prv != that.prv				  ) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		
		int out = 13;
		
		if (ime != null) out += 17 * ime.hashCode();
		if (poz != null) out += 23 * poz.hashCode();
						 out += 19 * god;
						 out += 27 * (prv ? 1 : 0);
						 
		return out;
	}

	@Override
	public InfoTip ucitaj(SvetovidReader r) {
		
		String	ime = r.readLine();
		String 	poz = r.readLine();
		int    	god = r.readInt();
		String	prv = r.readLine();
		
		return novi(ime, poz, god, prv);
	}

	public static void main(String[] args) {
		new TestHash(Fudbaler.novi(), "res/", "p");
	}
}
