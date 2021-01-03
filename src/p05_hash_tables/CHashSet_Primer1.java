package p05_hash_tables;

public class CHashSet_Primer1 {

	public static void main(String[] args) {
		
		CHashSet<Integer> set = new CHashSet<Integer>(11);
		
		// formiramo brojeve koji imaju istu hash vrednost - 2
		for (int i = 0; i < 5; i++) {
			int num = 2 + 11 * i;
			System.out.println("Dodajem: " + num);
			set.insert(num);
			set.print();
		}
	}
}
