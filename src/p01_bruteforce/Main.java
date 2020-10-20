package p01_bruteforce;

public class Main {

	public static void main(String[] args) {
		
		Radnik[] radnici = new Radnik[4];
		radnici[0] = new Radnik("Mika", 2000); 
		radnici[1] = new Radnik("Tika", 1000); 
		radnici[2] = new Radnik("Zika", 5000); 
		radnici[3] = new Radnik("Pera", 3000);
		
		System.out.println("Sortiranje po imenu: ");	
		
		BruteForceSort.sort(radnici);
		for (int i = 0; i < radnici.length; i++)
			System.out.println(radnici[i]);
			
		System.out.println("\nSortiranje po plati: ");	
		
		BruteForceSort.sort(radnici, new KomparatorPoPlati());
		for (int i = 0; i < radnici.length; i++)
			System.out.println(radnici[i]);
	}
}
