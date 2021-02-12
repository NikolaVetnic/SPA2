package teorijske_vezbe.tv03_recnik;

final class Fraza {

	private final String tekst;
	
	private Fraza(String tekst) {
		this.tekst = tekst;
	}
	
	public static Fraza nova(String tekst) {
		return new Fraza(tekst);
	}
	
	public String tekst() 		{ return tekst; }
	
	@Override
	public String toString() 	{ return tekst; }
	
	@Override
	public int hashCode() {
		if (tekst == null)
			return 0;
		else
			return tekst.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) return true;
		if (o == null) return false;
		if (getClass() != o.getClass()) return false;
		
		Fraza f = (Fraza) o;
		
		return tekst.equalsIgnoreCase(f.tekst);
	}
}
