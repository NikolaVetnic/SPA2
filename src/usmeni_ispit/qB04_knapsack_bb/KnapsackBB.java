package usmeni_ispit.qB04_knapsack_bb;

import java.util.Arrays;

public class KnapsackBB {

	
	private static class Item implements Comparable<Item> {

		private int profit, weight;
		
		public Item(int profit, int weight) {
			this.profit = profit;
			this.weight = weight;
		}
		
		public int profit() { return profit;	}
		public int weight() { return weight;	}
		
		public String toString() {
			return "(" + weight + ", " + profit + ")";
		}
		
		@Override
		public int compareTo(Item o) {
			
			double pd = 1.0 * profit / weight;
			double opd = 1.0 * o.profit / o.weight;
			
			if (pd > opd)		return -1;
			else if (pd < opd)	return  1;
			else				return  0;
		}
	}
	
	
	private int knapsackWeight;
	private Item[] items;
	private boolean in[];
	
	
	private int maxProfit;
	private boolean maxProfitConfiguration[];
	
	
	public KnapsackBB(int knapsackWeight, Item[] items) {
		this.items = items;
		this.knapsackWeight = knapsackWeight;
		this.in = new boolean[items.length];
		this.maxProfitConfiguration = new boolean[items.length];
	}
	
	
	public void find() {
		Arrays.sort(items);
		find(0, 0, 0);
	}


	private void find(int k, int currentWeight, int currentProfit) {
		
		if (k == items.length) {
			if (currentProfit > maxProfit) {
				updateBestSolution(currentProfit);
			}
		} else {
			if (items[k].weight() + currentWeight <= knapsackWeight) {
				
				int nextWeight = currentWeight + items[k].weight();
				int nextProfit = currentProfit + items[k].profit();
				
				double boundAdd = nextProfit + bound(k + 1, knapsackWeight - nextWeight);
				
				if (boundAdd > maxProfit) {
					in[k] = true;
					find(k + 1, nextWeight, nextProfit);
				}
			}
			
			double boundSkip = currentProfit + bound(k + 1, knapsackWeight - currentWeight);
			
			if (boundSkip > maxProfit) {
				in[k] = false;
				find(k + 1, currentWeight, currentProfit);
			}
		}
	}


	private double bound(int k, int weight) {
		
		int sumw = 0;
		double profitBound = 0.0;
		
		while (k < items.length && sumw + items[k].weight() <= weight) {
			sumw += items[k].weight();
			profitBound += items[k].profit();
			k++;
		}
		
		if (k < items.length) {
			double fraction = 1.0 * (weight - sumw) / items[k].weight();
			profitBound += items[k].profit() * fraction;
		}
		
		return profitBound;
	}


	private void updateBestSolution(int currentProfit) {
		
		for (int i = 0; i < in.length; i++)
			this.maxProfitConfiguration[i] = this.in[i];
		
		this.maxProfit = currentProfit;
	}
	
	
	public void printBestSolution() {
		
		System.out.println("Maximum profit : " + maxProfit);
		System.out.print("Configuration : ");
		for (int i = 0; i < items.length; i++)
			if (maxProfitConfiguration[i]) System.out.print(items[i] + " ");
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		Item[] items = {
				new Item(28, 7),
				new Item(22, 6),
				new Item(18, 5),
				new Item( 6, 2),
				new Item( 1, 1),
		};
		
		KnapsackBB ks = new KnapsackBB(11, items);
		
		ks.find();
		ks.printBestSolution();
	}
}
