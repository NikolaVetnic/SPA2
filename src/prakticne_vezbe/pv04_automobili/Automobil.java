package prakticne_vezbe.pv04_automobili;

import java.util.Objects;

import org.svetovid.io.SvetovidReader;

public class Automobil extends InfoTip {
	
	private String model;
	private Boja bojaKaroserije, bojaSedista, bojaVolana;
	
	public Automobil(String model, Boja bojaKaroserije, Boja bojaSedista, Boja bojaVolana) {
		this.model = model;
		this.bojaKaroserije = bojaKaroserije;
		this.bojaSedista = bojaSedista;
		this.bojaVolana = bojaVolana;
	}

	public Automobil() { }

	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		
		if (o == null)
			return false;
		
		if (this.getClass() != o.getClass())
			return false;
		
		Automobil a = (Automobil) o;
		
		if (!Objects.equals(model, a.model))
            return false;

        if (!Objects.equals(bojaKaroserije, a.bojaKaroserije))
            return false;

        if (!Objects.equals(bojaSedista, a.bojaSedista))
            return false;

        if (!Objects.equals(bojaVolana, a.bojaVolana))
            return false;

        return true;
	}

	@Override
    public Automobil ucitaj(SvetovidReader read) {
        String model = read.readLine();

        Boja temp = new Boja();
        Boja b1 = (Boja) temp.ucitaj(read);
        Boja b2 = (Boja) temp.ucitaj(read);
        Boja b3 = (Boja) temp.ucitaj(read);

        read.readLine();
        return new Automobil(model, b1, b2, b3);
    }
	
	public int hashCode() {
		return model.hashCode() + 
			   7 * bojaKaroserije.hashCode() + 
			   89 * bojaSedista.hashCode() + 
			   193 * bojaVolana.hashCode(); 
	}
	
	public static void main(String[] args) {
		
		new TestHash(new Automobil(), "res//", "a").run();
	}
}
