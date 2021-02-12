package usmeni_ispit.qB07_knapsack_dp;

import java.util.LinkedList;

public class KnapsackDP {

	
	static class Item {
		
		int weight, profit;
		
		public Item(int weight, int profit) {
			this.weight = weight;
			this.profit = profit;
		}
		
		public int weight() { return weight; }
		public int profit() { return profit; }
		
		public String toString() {
			return "(" + weight + ", " + profit + ")";
		}
	}
	
	
	private int knapsackWeight;
	private Item[] items;
	private int[][] mp;						// tabela maksimalnog profita
	
	
	public KnapsackDP(int knapsackWeight, Item[] items) {
		this.knapsackWeight = knapsackWeight;
		this.items = items;
		this.mp = new int[items.length][knapsackWeight + 1];
	}
	
	
	public int findMaxProfit() {
		
		for (int i = 0; i < mp.length; i++)
			mp[i][0] = 0;
		
		for (int j = 0; j < mp[0].length; j++)
			if (items[0].weight > j)
				mp[0][j] = 0;
			else
				mp[0][j] = items[0].weight;
		
		for (int i = 1; i < mp.length; i++) {
			for (int j = 1; j < mp[0].length; j++) {
				
				int profitAdd = 0;
				
				if (items[i].weight <= j)	// da li predmet moze da stane?
					profitAdd = items[i].profit + mp[i - 1][j - items[i].weight];
				
				int profitSkip = mp[i - 1][j];
				
				mp[i][j] = Math.max(profitSkip, profitAdd);
			}
		}
		
		return mp[items.length - 1][knapsackWeight];
	}
	
	
	public LinkedList<Item> getAddedItems() {
		
		LinkedList<Item> l = new LinkedList<>();
		
		int i = items.length - 1;
		int j = knapsackWeight;
		
		while (i > 0) {
			if (mp[i][j] != mp[i - 1][j]) {
				l.addFirst(items[i]);
				j -= items[i].weight;
			}
			i--;
		}
		
		if (j > 0 && items[0].weight <= j)
			l.addFirst(items[0]);
		
		return l;
	}
	
	
	public static void main(String[] args) {
		
		int[] weight = {7, 6, 5, 2, 1};
		int[] profit = {28, 22, 18, 6, 1};
		
		Item[] items = new Item[weight.length]; 
		
		for (int i = 0; i < items.length; i++)
			items[i] = new Item(weight[i], profit[i]);
		
		KnapsackDP k = new KnapsackDP(11, items);
		
		System.out.println("Maks profit = " + k.findMaxProfit()); 
		
		LinkedList<Item> addedItems = k.getAddedItems();
		
		for (int i = 0; i < addedItems.size(); i++)
			System.out.println(addedItems.get(i));
	}
}
