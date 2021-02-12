package prakticne_vezbe.pv03_flight_p02;

import org.svetovid.io.SvetovidReader;

public class Putnik extends InfoTip {

	private String prz, ime;
	private int god;
	
	public Putnik(String prz, String ime, int god) {
		super();
		this.prz = prz;
		this.ime = ime;
		this.god = god;
	}
	
	public Putnik() { }
	
	public String toString() {
		return String.format("%-20s %-20s %d", ime, prz, god);
	}
	
	public String prz() { return prz; }
	public String ime() { return ime; }
	public int god() 	{ return god; }
	
	public boolean equals(Object o) {
		
		if (this == o)					return true;
		if (o == null)					return false;
		if (this.getClass() != o.getClass())
										return false;
		
		Putnik p = (Putnik) o;
		
		if (!this.prz.equals(p.prz))	return false;
		if (!this.ime.equals(p.ime))	return false;
		if (this.god != p.god)			return false;
		
		return true;	
	}
	
	public int hashCode() {
		return ime.hashCode() * 13 + prz.hashCode() * 17 + god * 23;
	}

	@Override
	public InfoTip ucitaj(SvetovidReader r) {
		
		return new Putnik(
								 r.readLine().trim(),
								 r.readLine().trim(),
				Integer.parseInt(r.readLine().trim())
				);
	}
	
	public static void main(String[] args) {
		new TestHash(new Putnik(), "res//", "p").run();
	}
}
