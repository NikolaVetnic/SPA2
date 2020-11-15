package kol1_v_z01_p01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Raspored {

	Predmet[] predmeti;
	
	private Raspored(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		predmeti = new Predmet[Integer.parseInt(br.readLine().trim())];
		
		for (int i = 0; i < predmeti.length; i++) {
			
			br.readLine();
			int dan 		= Integer.parseInt(br.readLine().trim());
			int hPocetak 	= Integer.parseInt(br.readLine().trim());
			int hKraj		= Integer.parseInt(br.readLine().trim());
			String sala 	= br.readLine().trim();
			int god			= Integer.parseInt(br.readLine().trim());
			String ime 		= br.readLine().trim();
			
			predmeti[i] = Predmet.novi(dan, hPocetak, hKraj, sala, god, ime);
		}
		
		br.close();
	}
	
	public static Raspored ucitaj(String input) throws IOException {
		return new Raspored(input);
	}
	
	public void snimi(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		bw.write(predmeti.length + "\n\n");
		
		for (Predmet p: predmeti) {
			bw.write(p.dan() + "\n");
			bw.write(p.hPocetak() + "\n");
			bw.write(p.hKraj() + "\n");
			bw.write(p.sala() + "\n");
			bw.write(p.god() + "\n");
			bw.write(p.ime() + "\n\n");
		}
		
		bw.close();
	}
	
	public void stampaj() {
		
		for (Predmet p : predmeti)
			System.out.println(p);
	}
	
	public Predmet[] predmeti() { return predmeti; }
}
