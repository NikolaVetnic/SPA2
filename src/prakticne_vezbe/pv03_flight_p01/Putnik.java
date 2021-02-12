package prakticne_vezbe.pv03_flight_p01;

import java.util.Objects;

import org.svetovid.io.SvetovidReader;

public class Putnik extends InfoTip {

	private String ime, prezime;
	private int godina;
	
	private Putnik(String ime, String prezime, int godina) {
		this.ime = ime;
		this.prezime = prezime;
		this.godina = godina;
	}
	
	private Putnik() { }
	
	public static Putnik novi(String ime, String prezime, int godina) {
		return new Putnik(ime, prezime, godina);
	}
	
	public static Putnik novi() {
		return new Putnik();
	}
	
	public String toString() {
		return prezime + " " + ime + ", " + godina;
	}

	public String ime() 	{ return ime; 		}
	public String prezime() { return prezime; 	}
	public int godina() 	{ return godina; 	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		
		if (o == null)
			return false;
		
		if (getClass() != o.getClass())
			return false;
		
		Putnik that = (Putnik) o;
		
		if (!Objects.equals(this.ime, that.ime)) 
			return false;
		
		if (!Objects.equals(this.prezime, that.prezime)) 
			return false;
		
		if (this.godina != that.godina) 
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {

		int out = 11;
		
		if (ime != null) 
			out += 13 * ime.hashCode();
		
		if (prezime != null) 
			out += 7 * prezime.hashCode();
		
		out += 17 * godina;
		
		return out;
	}

	@Override
	public InfoTip ucitaj(SvetovidReader in) {
		
		String ime = in.readLine();
		String prezime = in.readLine();
		int god = in.readInt();
		
		return novi(ime, prezime, god);
	}
	
	public static void main(String[] args) {
		new TestHash(Putnik.novi(), "res/", "p").run();
	}
}
