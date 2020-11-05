package tv03_recnik;

import java.util.*;

final class Recnik {

	private Map<Fraza, Fraza> domacaNaStranu = new HashMap<>();
	private Map<Fraza, Fraza> stranaNaDomacu = new HashMap<>();
	
	public void ubaci(Fraza domacaFraza, Fraza stranaFraza) {
		
		if (domacaNaStranu.containsKey(domacaFraza))
			throw new IllegalArgumentException(
					"Domaca fraza " + domacaFraza.tekst() + " vec postoji.");
		
		if (domacaNaStranu.containsKey(domacaFraza))
			throw new IllegalArgumentException(
					"Strana fraza " + stranaFraza.tekst() + " vec postoji.");
		
		domacaNaStranu.put(domacaFraza, stranaFraza);
		stranaNaDomacu.put(stranaFraza, domacaFraza);
	}
	
	public void izbaci(Fraza f) {
		
		if (!domacaNaStranu.containsKey(f) && !stranaNaDomacu.containsKey(f))
			throw new IllegalArgumentException(
					"Fraza " + f.tekst() + " ne postoji u recniku.");
		
		Fraza strana = domacaNaStranu.remove(f);
		if (strana != null)
			stranaNaDomacu.remove(strana);
		
		Fraza domaca = stranaNaDomacu.remove(f);
		if (domaca != null)
			domacaNaStranu.remove(domaca);
	}
	
	public Fraza prevediDomacuNaStranu(Fraza d) {
		return domacaNaStranu.get(d);
	}
	
	public Fraza prevediStranuNaDomacu(Fraza s) {
		return stranaNaDomacu.get(s);
	}
	
	public Fraza prevedi(Fraza f) {
		
		return domacaNaStranu.get(f) != null ? domacaNaStranu.get(f) : stranaNaDomacu.get(f);
	}
}
