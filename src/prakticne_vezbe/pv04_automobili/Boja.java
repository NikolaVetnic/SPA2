package prakticne_vezbe.pv04_automobili;

import org.svetovid.io.SvetovidReader;

public class Boja extends InfoTip {
	
	private int r, g, b;
	
	public Boja(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Boja() { }
	
	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		
		if (o == null) 
			return false;
		
		if (this.getClass() != o.getClass())
			return false;
		
		Boja boja = (Boja) o;
		
		return r != boja.r ? false :
			   g != boja.g ? false :
			   b != boja.b ? false : true;
	}
	
	public int hashCode() {
		return (r + 1) + (g + 1) * 256 + (b + 1) * 256 * 256;
	}

	@Override
	public InfoTip ucitaj(SvetovidReader read) {
		
		int r = read.readInt();
		int g = read.readInt();
		int b = read.readInt();
		
		Boja boja = new Boja(r, g, b);
		return boja;
	}
	
	public static void main(String[] args) {
		new TestHash(new Boja(), "res//", "boje").run();
	}
	
	public void setR(int r) { this.r = r; }
	public void setG(int g) { this.g = g; }
	public void setB(int b) { this.b = b; }
}
