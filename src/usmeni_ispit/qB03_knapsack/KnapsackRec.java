package usmeni_ispit.qB03_knapsack;

public class KnapsackRec {
	
	
	static class Item {
		
		private int weight;
		private int profit;
		
		public Item(int weight, int profit) {
			this.weight = weight;
			this.profit = profit;
		}
		
		public int getWeight() { return weight; }
		public int getProfit() { return profit; }
		
		public String toString() { 
			return "(" + weight + ", " + profit + ")"; 
		}
	}

	
	private int knapsackWeight;
	private Item[] items;
	private boolean in[];
	
	
	private int maxProfit;
	private boolean maxProfitConfiguration[];
	
	
	public KnapsackRec(int knapsackWeight, Item[] items) {
		this.items = items;
		this.knapsackWeight = knapsackWeight;
		in = new boolean[items.length];
		maxProfitConfiguration = new boolean[items.length];
	}
	
	
	public void find() {
		find(0, 0, 0);
	}
	

	private void find(int k, int currentWeight, int currentProfit) {
		
		if (k == items.length) {
			if (currentProfit > maxProfit) {
				// sacuvaj resenje
				maxProfit = currentProfit;
				for (int i = 0; i < items.length; i++)
					maxProfitConfiguration[i] = in[i];
			}
		} else {
			if (items[k].getWeight() + currentWeight <= knapsackWeight) {
				in[k] = true;
				find(
					k + 1,
					currentWeight + items[k].getWeight(),
					currentProfit + items[k].getProfit()
				);
			}
			
			in[k] = false;
			find(k + 1, currentWeight, currentProfit);
		}
	}
	
	// Samo za potrebe demonstracije!
	public void printSolution() {
		System.out.println("Max profit: " + maxProfit);
		System.out.println("Solution... ");
		for (int i = 0; i < items.length; i++)
			if (maxProfitConfiguration[i])
				System.out.println(items[i]);
	}
	
	// Samo za potrebe demonstracije!
	public static void main(String[] args) {
		
		int[] weight = { 1, 2,  5,  6,  7 };
		int[] profit = { 1, 6, 18, 22, 28 };
		
		Item[] items = new Item[weight.length];
		for (int i = 0; i < items.length; i++)
			items[i] = new Item(weight[i], profit[i]);
		
		KnapsackRec kr = new KnapsackRec(11, items);
		kr.find();
		kr.printSolution();
	}
}
