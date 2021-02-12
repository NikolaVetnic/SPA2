package usmeni_ispit.qB14_huffman_tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HuffmanTree {

	// informacija u cvoru hafmanovog stabla kodiranja
	private class CharFrequency implements Comparable<CharFrequency> {
		
		char c;		// karakter
		int freq;	// frekvencija
		
		public CharFrequency(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}
		
		// koristima java.util.PriorityQueue koji je MIN-PQ
		// najmanji element ima najveci prioritet
		public int compareTo(CharFrequency o) {
			return freq - o.freq;
		}
		
		public String toString() {
			return "[" + c + ", f = " + freq + "]";
		}
	}
	
	// lista listova stabla -- karakteri iz teksta i njihove frekvencije
	private LinkedList<CharFrequency> frequencyList;
	
	// hafmanovo stablo kodiranja
	private BinaryTree<CharFrequency> hTree;
	
	
	public HuffmanTree(String inputText) {
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
			BTNode<CharFrequency> node = new BTNode<CharFrequency>(cf);
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
			
			BTNode<CharFrequency> left = curr.left();
			if (left != null) printCodes(left, code + "0");
			
			BTNode<CharFrequency> right = curr.right();
			if (right != null) printCodes(right, code + "1");
		}
	}
	
	
	public static void main(String[] args) {
		
		HuffmanTree ht = new HuffmanTree("ana voli milovana"); 
		ht.printCodes();
	}
}
