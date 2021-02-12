package usmeni_ispit.qB03_knapsack;

public class KnapsackRecClean {
	
	
	static class Item {
		
		int weight, profit;
		
		public Item(int weight, int profit) {
			this.weight = weight;
			this.profit = profit;
		}
	}
	
	
	private int knapsackWeight;
	private Item[] items;
	private boolean[] in;
	
	private int maxProfit;
	private boolean[] maxConfig;
	
	
	public KnapsackRecClean(int knapsackWeight, Item[] items) {
		this.knapsackWeight = knapsackWeight;
		this.items = items;
		this.in = new boolean[items.length];
		
		this.maxProfit = 0;
		this.maxConfig = new boolean[items.length];
	}
	
	
	public void find() {
		find(0, 0, 0);
	}


	private void find(int k, int currWeight, int currProfit) {
		
		if (k == items.length) {
			if (currProfit > maxProfit)
				System.out.println("Update solution...");
		} else {
			if (items[k].weight + currWeight <= knapsackWeight) {
				in[k] = true;
				find(
					k + 1,
					currWeight + items[k].weight,
					currProfit + items[k].profit
				);
			}
			
			in[k] = false;
			find(k + 1, currWeight, currProfit);
		}
	}
}
