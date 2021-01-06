package p_vezba.p05_hash_tables_p01;

public class OHashSet_Primer1 {

	public static void main(String[] args) {
		OHashSet<Integer> set = new OHashSet<Integer>(5);
		
		for (int i = 0; i < 24; i++)
			set.insert(i);
		set.print();
		
		if (set.insert(10))
			System.out.println("10 je ubacen u skup");
		else
			System.out.println("10 je vec u skupu");
		
		System.out.println("Member(10) - " + set.member(10) + ", Member(34) - " + set.member(34));
		
		System.out.println("Remove(22) - " + set.remove(22));
		System.out.println("Member(22) - " + set.member(22));
		set.print();
	}
}
