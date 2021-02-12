package usmeni_ispit.qB14_huffman_tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HuffmanTreeClean {

	
	private class CharFrequency implements Comparable<CharFrequency> {
		
		char c;
		int freq;
		
		public CharFrequency(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}

		@Override
		public int compareTo(CharFrequency o) {
			return freq - o.freq;
		}
	}
	
	
	LinkedList<CharFrequency> frequencyList;
	BinaryTree<CharFrequency> hTree;
	
	
	public HuffmanTreeClean(String inputText) {
		computeFrequencies(inputText);
		construct();
	}


	private void computeFrequencies(String inputText) {
		
		frequencyList = new LinkedList<CharFrequency>();
		HashMap<Character, CharFrequency> frequencyIndex = new HashMap<Character, CharFrequency>();
		
		for (int i = 0; i < inputText.length(); i++) {
			
			char c = inputText.charAt(i);
			CharFrequency cf = frequencyIndex.get(c);
			
			if (cf == null) {
				cf = new CharFrequency(c, 1);
				frequencyList.add(cf);
				frequencyIndex.put(c, cf);
			} else {
				cf.freq++;
			}
		}
	}


	private void construct() {
		
		int numCharacters = frequencyList.size();
		PriorityQueue<BTNode<CharFrequency>> pq = new PriorityQueue<BTNode<CharFrequency>>(numCharacters);
		
		Iterator<CharFrequency> it = frequencyList.iterator();
		
		while (it.hasNext()) {
			CharFrequency cf = it.next();
			BTNode<CharFrequency> node = new BTNode<>(cf);
			pq.add(node);
		}
		
		while (pq.size() >= 2) {
			
			BTNode<CharFrequency> rightSubtree = pq.poll();
			int rsFreq = rightSubtree.info().freq;
			
			BTNode<CharFrequency> leftSubtree = pq.poll();
			int lsFreq = leftSubtree.info().freq;
			
			CharFrequency aggFreq = new CharFrequency('#', lsFreq + rsFreq);
			
			BTNode<CharFrequency> newNode = new BTNode<CharFrequency>(aggFreq, leftSubtree, rightSubtree);
			
			pq.add(newNode);
		}
		
		hTree = new BinaryTree<CharFrequency>();
		hTree.setRoot(pq.poll());
	}
	
	
	public void printCodes() {
		
		BTNode<CharFrequency> root = hTree.root();
		
		if (root.left() == null && root.right() == null)
			System.out.println(root.info() + " --> 1");
		else
			printCodes(root, "");
	}


	private void printCodes(BTNode<CharFrequency> curr, String code) {
		
		if (curr.left() == null && curr.right() == null) {
			System.out.println(curr.info() + " --> " + code);
		} else {
			if (curr.left() != null) printCodes(curr.left(), code + "0");
			if (curr.right() != null) printCodes(curr.right(), code + "1");
		}
	}
}
