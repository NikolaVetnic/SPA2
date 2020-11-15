package kol1_v_z01_p02;

public class Predmet implements Comparable<Predmet> {

	private final int dan;
	private final int hPoc;
	private final int hKrj;
	private final String sala;
	private final int god;
	private final String ime;
	
	public Predmet(int dan, int hPoc, int hKrj, String sala, int god, String ime) {
		this.dan 	= dan;
		this.hPoc 	= hPoc;
		this.hKrj 	= hKrj;
		this.sala	= sala;
		this.god 	= god;
		this.ime 	= ime;
	}
	
	@Override
	public String toString() {
		return String.format("[ %-40s ] dan: %d, trajanje: %2d-%2d, godina: %d, sala: %s", 
				ime, dan, hPoc, hKrj, god, sala);
	}
	public int dan() 		{ return dan;  }
	public int hPoc() 		{ return hPoc; }
	public int hKrj() 		{ return hKrj; }
	public String sala() 	{ return sala; }
	public int god() 		{ return god;  }
	public String ime() 	{ return ime;  }

	@Override
	public int compareTo(Predmet that) {
		
		int out = dan - that.dan;
			out = out != 0 ? out : hPoc - that.hPoc;
			out = out != 0 ? out : sala.compareTo(that.sala);
			
		return out;
	}
}
