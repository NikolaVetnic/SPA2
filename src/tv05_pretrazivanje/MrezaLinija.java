package tv05_pretrazivanje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.svetovid.io.SvetovidReader;

//Klasa koja sadrzi informacije o svim autobuskim linijama
class MrezaLinija {

	// Mapiramo svaki grad na skup linija koje polaze iz tog grada
	protected final Map<String, Set<Linija>> linije;

	// Pravimo novu praznu mrezu autobuskih linija
	public MrezaLinija() {
		linije = new HashMap<>();
	}

	// Pravimo kopiju prosledjene mreze autobuskih linija
	public MrezaLinija(MrezaLinija mreza) {

		// Napravimo praznu mrezu na standardan nacin
		this();

		// Iskopiramo sve linije
		linije.putAll(mreza.linije);
	}

	// Ucitavamo mrezu autobuskih linija iz prosledjenog fajla
	public MrezaLinija(SvetovidReader in) {

		// Napravimo praznu mrezu na standardan nacin
		this();

		// Ucitavamo liniju po liniju sve dok ima podataka
		while (in.hasMore()) {

			// Ucitamo prvi neprazan red
			String odGrada = null;
			do {
				odGrada = in.readLine();
			} while (odGrada != null && odGrada.isEmpty());

			// Ucitamo sledeci red
			String doGrada = in.readLine();

			// I cenu na kraju
			Double cenaKarte = in.readDoubleBoxed();

			// Napravimo liniju i ubacimo je
			// samo ako smo dobro ucitali podatke
			if (odGrada != null && doGrada != null && cenaKarte != null) {
				Linija linija = new Linija(odGrada, doGrada, cenaKarte);
				dodajLiniju(linija);
			}
		}
	}

	// Vracamo uvek novu promenlivu sortiranu listu svih gradova
	public List<String> gradovi() {

		// Skupljamo sve gradove u skup
		Set<String> gradovi = new HashSet<>();

		// Idemo po svim registrovanim linijama
		for (Set<Linija> skup : linije.values()) {
			for (Linija linija : skup) {

				// I ubacujemo gradove
				gradovi.add(linija.getOdGrada());
				gradovi.add(linija.getDoGrada());
			}
		}

		// napravimo novu listu i dodamo sve gradve iz skupa
		List<String> lista = new ArrayList<>(gradovi);

		// Sortiramo je i vratimo
		Collections.sort(lista);
		return lista;
	}

	// Vracamo novi promenljivi skup linija koje polaze iz datog grada
	public Set<Linija> linije(String grad) {

		// Napravimo novi prazan skup
		Set<Linija> rezultat = new HashSet<>();

		// Pronadjemo skup linija
		Set<Linija> skup = linije.get(grad);

		// Ako taj skup postoji, dodamo sve linije u novi skup
		// koji vracamo onome ko nas je pozvao
		if (skup != null) {
			rezultat.addAll(skup);
		}

		// Vratimo novi skup
		return rezultat;
	}

	// Dodajemo datu autobusku liniju u interne strukture
	public void dodajLiniju(Linija linija) {

		// Pronadjemo skup linija iz polaznog grada
		String grad = linija.getOdGrada();
		Set<Linija> skup = linije.get(grad);

		// Ako nema tog skupa, ovo je prva linija
		if (skup == null) {

			// Napravimo novi prazan skup
			skup = new HashSet<>();

			// Dodamo ga u internu mapu
			linije.put(grad, skup);
		}

		// Sada svakako imamo skup i samo dodamo liniju
		skup.add(linija);
	}

	// Uklanjamo datu autobusku liniju internih struktura
	public void ukloniLiniju(Linija linija) {

		// Pronadjemo skup linija iz polaznog grada
		String grad = linija.getOdGrada();
		Set<Linija> skup = linije.get(grad);

		// Ako nema tog skupa, znaci da linija nije registrovana
		if (skup == null) {
			return;
		}

		// Uklonimo liniju
		skup.remove(linija);

		// A ako je skup ostao prazan, uklonimo i ceo skup
		if (skup.isEmpty()) {
			linije.remove(skup);
		}
	}
}