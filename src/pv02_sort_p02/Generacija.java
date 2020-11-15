package pv02_sort_p02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Generacija {

	Student[] studenti;
	
	public Generacija(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(
				new FileReader(file));
		
		studenti = new Student[Integer.parseInt(br.readLine().trim())];
		
		for (int i = 0; i < studenti.length; i++)
			studenti[i] = new Student(
									 br.readLine().trim(),
									 br.readLine().trim(),
					Integer.parseInt(br.readLine().trim())
					);
		
		br.close();
	}
	
	public void snimi(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(file));
		
		bw.write(studenti.length + "\n");
		
		for (Student s : studenti)
			bw.write(s.prz() + "\n" +
					 s.ime() + "\n" +
					 s.god() + "\n");
		
		bw.close();
	}
	
	public void stampaj() {
		
		for (Student s : studenti)
			System.out.println(s);
	}
}
