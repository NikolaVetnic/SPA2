package tv05_pretrazivanje;

import org.svetovid.Svetovid;

public class Main {

	public static void main(String[] args) {

		// Ucitamo mrezu autobuskih linija
		MrezaLinija mreza = new MrezaLinija(Svetovid.in("res//Linije.txt"));

		// Pitamo korisnika za polazni i ciljni grad
		Svetovid.out.println("Red voznje sadrzi sledece gradove:");
		Svetovid.out.println(mreza.gradovi());
		String polazniGrad = Svetovid.in.readLine("Unesite polazni grad:");
		String ciljniGrad = Svetovid.in.readLine("Unesite ciljni grad:");

		// Rekurzivni algoritam
		pronadjiPuteveRekurzivno(mreza, polazniGrad, ciljniGrad);
	}

	// Priprema za rekurzivno trazenje puta
	private static void pronadjiPuteveRekurzivno(MrezaLinija mreza, String polazniGrad, String ciljniGrad) {

		// Napravimo pomocnu strukturu koja nam sadrzi trenutni put
		// Ona je na poetku prazna
		Put put = new Put();

		// Rekurzivno pronadjemo sve puteve
		int brPuteva = pronadjiPuteveRekurzivno(mreza, put, 0, polazniGrad, ciljniGrad);

		// Ispisemo poruku ako nismo pronasli ni jedan put
		if (brPuteva == 0) {
			Svetovid.out.println();
			Svetovid.out.println("Nazalost, ne postoji put izmadju datih gradova");
		}
	}

	// Rekurzivno trazenje puteva
	private static int pronadjiPuteveRekurzivno(MrezaLinija mreza, Put put, int brPuteva, String polazniGrad, String ciljniGrad) {

		// Stigli smo do ciljnog grada
		if (polazniGrad.equals(ciljniGrad)) {

			// Ispisemo put
			Svetovid.out.println(put);

			// Nasli smo jos jedan put
			return ++brPuteva;
		}

		// Ako nismo, idemo redom po svim linijama koje polaze iz tog grada
		for (Linija linija : mreza.linije(polazniGrad)) {

			// Ako vec nismo bili tamo, probamo da odemo
			if (!put.sadrzi(linija.getDoGrada())) {

				// Produzimo put za ovu novu liniju
				put.produzi(linija);

				// Probamo preko ovog novog grada da stignemo do cilja
				brPuteva = pronadjiPuteveRekurzivno(mreza, put, brPuteva, linija.getDoGrada(), ciljniGrad);

				// Pri povratku, izbacimo liniju iz puta
				put.skrati();
			}
		}

		// Vratimo koliko smo puteva pronasli
		return brPuteva;
	}
}