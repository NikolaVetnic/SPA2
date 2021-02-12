package teorijske_vezbe.tv05_pretrazivanje;

import java.util.ArrayList;
import java.util.List;

//Tip podataka koji predstavlja jedan put
class Put {

	// Lista linija preko kojih putujemo
	private final List<Linija> linije;

	// Napravimo novi prazan put
	public Put() {
		linije = new ArrayList<>();
	}

	// Napravimo kopiju datog puta
	public Put(Put original) {

		// Napravimo prazan put na standardan nacin
		this();

		// Dodamo sve linije
		linije.addAll(original.linije);

	}

	// Da li se grad nalazi na putu
	public boolean sadrzi(String grad) {

		// Da li je to polazni grad?
		if (!linije.isEmpty() && linije.get(0).getOdGrada().equals(grad)) {
			return true;
		}

		// Ili je neki od medjustanica
		for (Linija linija : linije) {
			if (linija.getDoGrada().equals(grad)) {
				return true;
			}
		}

		// Nema ga medju linijama
		return false;

	}

	// Produzimo put za datu liniju
	public void produzi(Linija linija) {

		// Ako vec imamo linija na putu
		if (!linije.isEmpty()) {

			// Poslednji grad poslednje linije
			String poslednjiGrad = linije.get(linije.size() - 1).getDoGrada();

			// I prvi grad nove linije moraju da se poklapaju
			if (!poslednjiGrad.equals(linija.getOdGrada())) {
				throw new IllegalArgumentException("linija");
			}

		}

		// Ako je sve ok, samo dodamo liniju
		linije.add(linija);

	}

	// Skratimo put za poslednju liniju
	public Linija skrati() {

		// Ako je put prazan, ne mozemo ga skratiti
		if (linije.isEmpty()) {
			throw new IllegalStateException("Prazan put ne moze da se skrati");
		}

		// Inace samo uklonimo poslednju liniju
		return linije.remove(linije.size() - 1);

	}

	public double ukupnaCena() {
		double ukupnaCena = 0.0;
		for (Linija linija : linije) {
			ukupnaCena = ukupnaCena + linija.getCenaKarte();
		}
		return ukupnaCena;
	}

	@Override
	public String toString() {
		return ukupnaCena() + " " + linije.toString();
	}
}