package kolokvijumi.kol1_v_z01_p03;

public class Predmet implements Comparable<Predmet> {

	private int		dan;
	private int 	poc;
	private	int 	krj;
	private String	mst;
	private int		god;
	private String 	ime;
	
	public Predmet(int dan, int poc, int krj, String mst, int god, String ime) {
		this.dan = dan;
		this.poc = poc;
		this.krj = krj;
		this.mst = mst;
		this.god = god;
		this.ime = ime;
	}
	
	@Override
	public String toString() {
		return String.format("%-40s dan: %d  trajanje: %2d-%2d  godina: %d  sala: %-10s",
				ime, dan, poc, krj, god, mst);
	}

	public int 		dan() { return dan; }
	public int 		poc() { return poc; }
	public int 		krj() { return krj; }
	public String 	mst() { return mst; }
	public int 		god() { return god; }
	public String 	ime() { return ime; }

	@Override
	public int compareTo(Predmet that) {
		
		return dan - that.dan != 0 ? dan - that.dan :
			   poc - that.poc != 0 ? poc - that.poc : mst.compareTo(that.mst);
	}
}
