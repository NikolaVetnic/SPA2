package kol1_grupa4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Spisak {

	KursnaLista[] liste;
	
	public Spisak(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		liste = new KursnaLista[Integer.parseInt(br.readLine().trim())];
		
		for (int i = 0; i < liste.length; i++)
			liste[i] = new KursnaLista(
									 br.readLine().trim() ,
					Integer.parseInt(br.readLine().trim()),
					Integer.parseInt(br.readLine().trim()),
					Integer.parseInt(br.readLine().trim()),
					Integer.parseInt(br.readLine().trim())
					);
		
		br.close();
	}
	
	public void snimi(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		bw.write(liste.length + "\n");
		
		for (KursnaLista l : liste) {
			bw.write(l.ozn() + "\n");
			bw.write(l.kup() + "\n");
			bw.write(l.srd() + "\n");
			bw.write(l.pro() + "\n");
			bw.write(l.dan() + "\n");
		}
		
		bw.close();
	}
	
	public void stampaj() {
		
		for (KursnaLista l : liste)
			System.out.println(l);
	}
}
