package teorijske_vezbe.tv03_recnik;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;

public class Main {
	
	public static void main(String[] args) {
		
		String input = Svetovid.in.readLine("Ucitavamo recnik: ");
		Recnik recnik = ucitajRecnik(input);
		
		if (recnik == null) {
			return;
		}
		
		System.out.println("[1] prevod domace fraze");
		System.out.println("[2] prevod strane fraze");
		System.out.println("[3] dodavanje nove fraze i njenog prevoda");
		System.out.println("[4] brisanje postojece fraze i njenog prevoda");
		System.out.println("Bilo koja druga opcija zavrsava rad programa.");
		
		while (true) {

			System.out.println();
			int opcija = Svetovid.in.readInt("Opcija:");

			switch (opcija) {
			
				case 1:
					String tekst = Svetovid.in.readLine("Unesite domacu frazu za koju zelite prevod:");
					Fraza domaca = Fraza.nova(tekst);
					Fraza strana = recnik.prevediDomacuNaStranu(domaca);
					if (strana == null) {
						System.out.println("Trazena fraza ne postoji u recniku.");
					} else {
						System.out.println("Prevod fraze je:");
						System.out.println(strana.tekst());
					}
					break;
					
				case 2:
					tekst = Svetovid.in.readLine("Unesite stranu frazu za koju zelite prevod:");
					strana = Fraza.nova(tekst);
					domaca = recnik.prevediStranuNaDomacu(strana);
					if (domaca == null) {
						System.out.println("Trazena fraza ne postoji u recniku!");
					} else {
						System.out.println("Prevod fraze je:");
						System.out.println(domaca.tekst());
					}
					break;
					
				case 3:
					tekst = Svetovid.in.readLine("Unesite domacu frazu koju zelite da dodate:");
					String prevod = Svetovid.in.readLine("Unesite njen prevod:");
					domaca = Fraza.nova(tekst);
					strana = Fraza.nova(prevod);
					recnik.ubaci(domaca, strana);
					System.out.println("Fraza i njen prevod su uspesno ubaceni u recnik.");
					break;
					
				case 4:
					tekst = Svetovid.in.readLine("Unesite domacu frazu koju zelite da izbacite:");
					domaca = Fraza.nova(tekst);
					recnik.izbaci(domaca);
					System.out.println("Fraza i njen prevod su uspesno izbaceni iz recnika.");
					break;
					
				default:
					System.out.println("Kraj!");
					return;
			}
		}
	}

	private static Recnik ucitajRecnik(String input) {
		
		String file = "res//" + input + ".txt";
		
		if (!Svetovid.testIn(file)) {
			System.out.println("Fajl ne postoji.");
			return null;
		}
		
		SvetovidReader in = Svetovid.in(file);
		Recnik recnik = new Recnik();
		
		while (in.hasMore()) {
			recnik.ubaci(
					Fraza.nova(in.readLine()), 
					Fraza.nova(in.readLine()));
			
			in.readLine();
		}
		
		Svetovid.closeIn(file);
		
		return recnik;	
	}
}
