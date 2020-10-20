package pv01_automobil;

public class Motor implements Comparable<Motor> {

	// osobine koje se ne mogu menjati
	public final String gorivo;
	public final double snaga;
	
	public Motor(String gorivo, double snaga) {
		this.gorivo = gorivo;
		this.snaga = snaga;
	}
	
	public String toString() {
		
		// tekstualna reprezentacija motora automobila oblika:
		// Motor [Dizel, 52KW / 69.73 hp]
		return "Motor [" + gorivo + ", " + snaga + " KW / " + snaga * 1.34101 + " hp]";
	}

	@Override
	public int compareTo(Motor that) {
		
		// poredimo motore iskljucivo po snazi
		return (int) Math.signum(this.snaga - that.snaga);
	}
}
