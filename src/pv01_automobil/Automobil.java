package pv01_automobil;

import java.util.Objects;

public class Automobil implements Comparable<Automobil> {

	// osobine koje se ne mogu menjati
	public final String model;
	public final int godiste;
	public final Motor motor;
	
	// osobine koje su slobodno izmenjive
	public String boja;
	
	// osobine koje su zasticene
	private int kilometraza;
	
	public Automobil(String model, int godiste, Motor motor, String boja, int kilometraza) {
		this.model = model;
		this.godiste = godiste;
		this.motor = motor;
		this.boja = boja;
		this.kilometraza = kilometraza;
	}
	
	public int getKilometraza() { return kilometraza; }
	
	public void vozi(int duzina) {
		this.kilometraza = this.kilometraza + duzina;
	}
	
	public String toString() {
		// tekstualna reprezentacija automobila oblika:
		// Zastava 101 (1999) Crvena boja, presao 231000 km
		return model + " (" + godiste + ") " + boja + " boja, presao " + kilometraza + " km";
	}
	
	public int compareTo(Automobil that) {
		
		// prvo poredimo naziv modela i ne uzimamo u obzir razlike izmedju velikih i malih slova
		int rezultat = Objects.compare(this.model, that.model, String.CASE_INSENSITIVE_ORDER);
		
		if (rezultat == 0)
			rezultat = this.godiste - that.godiste;
		
		return rezultat;
	}
}
