package pv03_flight_p02;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public final static String DOZVOLJENO = "flight";
	public final static String ZABRANJENO = "noflightlist";

	public static void main(String[] args)
		throws IOException
	{
		SpisakPutnika zabr = new SpisakPutnika(ZABRANJENO);
		SpisakPutnika dozv = new SpisakPutnika(DOZVOLJENO);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Prezime: ");
		String prz = sc.nextLine();
		
		System.out.print("Ime: ");
		String ime = sc.nextLine();

		System.out.print("Godina: ");
		int god = sc.nextInt();
		
		if (zabr.proveraPoImenu(prz, ime, god, true))
			System.out.println("Putnik NE SME da leti.");
		else
			System.out.println("Putnik SME da leti.");
		
		dozv.spisak().removeAll(zabr.spisak());
		System.out.println("\nPutnici koji smeju da lete: ");
		dozv.stampaj();
		
		sc.close();
	}
}
