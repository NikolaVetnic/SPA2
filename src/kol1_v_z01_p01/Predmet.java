package kol1_v_z01_p01;

public class Predmet implements Comparable<Predmet> {

	private final int dan, hPocetak, hKraj, god;
	private final String sala, ime;
	
	private Predmet(int dan, int hPocetak, int hKraj, String sala, int god, String ime) {
		this.dan = dan;
		this.hPocetak = hPocetak;
		this.hKraj = hKraj;
		this.sala = sala;
		this.god = god;
		this.ime = ime;
	}
	
	public static Predmet novi(int dan, int hPocetak, int hKraj, String sala, int god, String ime) {
		return new Predmet(dan, hPocetak, hKraj, sala, god, ime);
	}

	public int dan() 		{ return dan; 		}
	public int hPocetak() 	{ return hPocetak; 	}
	public int hKraj() 		{ return hKraj; 	}
	public String sala() 	{ return sala; 		}
	public int god() 		{ return god; 		}
	public String ime() 	{ return ime; 		}
	
	public String toString() {
		return "" + dan + ", pocetak: " + hPocetak + ", kraj: " + hKraj + 
			   ", sala: " + sala + ", godina: " + god + ", predmet: " + ime;
	}

	@Override
	public int compareTo(Predmet that) {
		return dan - that.dan() 			!= 0 ? dan - that.dan() :
			   hPocetak - that.hPocetak() 	!= 0 ? hPocetak - that.hPocetak() : 
				   								   sala.compareTo(that.sala()); 
	}
}
