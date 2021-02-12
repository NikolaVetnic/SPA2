package kolokvijumi.kol1_v_z01_p03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Raspored {

	Predmet[] predmeti;
	
	public Raspored(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		predmeti = new Predmet[Integer.parseInt(br.readLine().trim())];
		
		for (int i = 0; i < predmeti.length; i++) {
			
			br.readLine();
			
			predmeti[i] = new Predmet(
					Integer.parseInt(br.readLine().trim()),
					Integer.parseInt(br.readLine().trim()),
					Integer.parseInt(br.readLine().trim()),
									 br.readLine().trim() ,
					Integer.parseInt(br.readLine().trim()),
									 br.readLine().trim());
		}
		
		br.close();
	}
	
	public void snimi(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		bw.write(predmeti.length + "\n\n");
		
		for (Predmet p : predmeti) {
			bw.write(p.dan() + "\n");
			bw.write(p.poc() + "\n");
			bw.write(p.krj() + "\n");
			bw.write(p.mst() + "\n");
			bw.write(p.god() + "\n");
			bw.write(p.ime() + "\n\n");
		}
		
		bw.close();
	}
	
	public void stampaj() {
		
		for (Predmet p : predmeti)
			System.out.println(p);
	}
}
