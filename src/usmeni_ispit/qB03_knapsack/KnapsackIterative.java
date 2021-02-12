package usmeni_ispit.qB03_knapsack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Objects;

public class KnapsackIterative {

	
	static class Item {
		
		
		int value, weight;
		
		
		public Item(int value, int weight) {
			this.value = value;
			this.weight = weight;
		}
		
		
		public String toString() {
			return "(v = " + value + ", w = " + weight + ")";
		}
	}
	
	
	static class Knapsack { 
		
		
		List<Item> contents;
		int max, weight, value;
		
		
		Comparator<Knapsack> c = (Knapsack k1, Knapsack k2) -> k2.value - k1.value;
		
		
		public Knapsack(int max) {
			this.max = max;
			this.contents = new ArrayList<Item>();
			this.weight = 0;
			this.value = 0;
		}
		
		
		public boolean add(Item item) {
			if (!contains(item) && fits(item)) {
				contents.add(0, item);
				weight += item.weight;
				value += item.value;
				return true;
			}
			
			return false;
		}
		
		
		public boolean remove() {
			if (contents.size() == 0)
				return false;
			
			weight -= contents.get(0).weight;
			value -= contents.get(0).value;
			contents.remove(0);
			
			return true;
		}
		
		
		public boolean contains(Item item) {
			for (Item i : contents)
				if (Objects.equal(item, i))
					return true;
			
			return false;
		}
		
		
		public boolean fits(Item item) {
			return weight + item.weight <= max;
		}
		
		
		public Knapsack copy() {
			
			Knapsack out = new Knapsack(this.max);
			out.contents = new ArrayList<Item>();
			out.weight = 0;
			out.value = 0;
			
			for (Item i : this.contents) out.add(i);
			
			return out;
		}
		
		
		public String toString() {
			return "VAL = " + value + ", WGT = " + weight + ", CON = " + contents.toString();
		}
	}
	
	
	static Item[] items = {
		new Item(28, 7),
		new Item(22, 6),
		new Item(18, 5),
		new Item( 6, 2),
		new Item( 1, 1),
	};
	
	
	@SuppressWarnings("unchecked")
	private static ArrayList<Item>[] D = new ArrayList[items.length];
	private static List<Knapsack> solutions = new ArrayList<Knapsack>();
	
	
	public static void backtrack(int ksWeight) {
		
		Knapsack ks = new Knapsack(ksWeight);
		
		int k = 0;
		D[k] = populate(ks);
		
		while (k >= 0) {
			while (!D[k].isEmpty()) {
				
				Item f = D[k].remove(0);
				ks.add(f);
				
				if (ks.weight <= ks.max) 
					solutions.add(ks.copy());
				
				k++;
				D[k] = populate(ks);
			}
			
			ks.remove();
			k--;
		}
	}
	
	
	private static ArrayList<Item> populate(Knapsack ks) {
		
		ArrayList<Item> out = new ArrayList<>();
		
		for (Item i : items)
			if (ks.fits(i) && !ks.contains(i))
				out.add(i);
		
		return out;
	}
	
	
	public static void main(String[] args) {
		
		backtrack(11);
		solutions.sort(new Knapsack(0).c);
		System.out.println("Optimal solution:\t" + solutions.get(0));
	}
}
