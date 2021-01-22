package p_vezba.qB12_13_bintree_adt_set;

import java.util.Random;

public class BST<T extends Comparable <T>> {

	
	private BTNode<T> root;
	
	
	public BST() 						{ this.root = null; 	}
	public BST(BTNode<T> root) 			{ this.root = root; 	}
	
	
	public BTNode<T> root()				{ return root;			}
	public void setRoot(BTNode<T> root)	{ this.root = root;		}
	
	
	private class SearchResult {
		
		BTNode<T> node, parent;
		
		public SearchResult(BTNode<T> node, BTNode<T> parent) {
			this.node = node;
			this.parent = parent;
		}
	}
	
	
	private SearchResult search(T info) {
		
		BTNode<T> current = root, parent = null;
		boolean found = false;
		
		while (current != null && !found) {
			
			int cmp = info.compareTo(current.info());
			
			if (cmp == 0) {
				found = true;
			} else {
				parent = current;
				if (cmp < 0) current = current.left();
				else		 current = current.right();
			}
		}
		
		return new SearchResult(current, parent);
	}
	
	
	public boolean member(T element) {
		SearchResult sr = search(element);
		return sr.node != null;
	}
	
	
	public boolean insert(T element) {
		
		if (root == null) {
			root = new BTNode<T>(element);
			return true;
		}
		
		SearchResult sr = search(element);
		if (sr.node != null)
			return false;
		
		BTNode<T> newNode = new BTNode<T>(element);
		BTNode<T> parent = sr.parent;
		
		if (element.compareTo(parent.info()) < 0)
			parent.setLeft(newNode);
		else
			parent.setRight(newNode);
		
		return true;
	}
	
	
	public boolean remove(T element) {
		
		SearchResult sr = search(element);
		if (sr.node == null) return false;
		
		BTNode<T> toRemove = sr.node;
		BTNode<T> parent = sr.parent;
		
		/*
		 * Prvi slucaj : cvor je list (trivijalno); drugi slucaj : cvor ne-
		 * ma jednog sina (prosto prevezivanje, kao kod listi); treci sluc-
		 * aj - cvor ima oba sina. Mora se voditi racuna o tome da li bris-
		 * emo koren stabla.
		 */ 
		
		if (toRemove.left() == null && toRemove.right() == null) {
			removeLeaf(toRemove, parent);
		} else if (toRemove.left() == null || toRemove.right() == null) {
			removeInternalWithOneChild(toRemove, parent);
		} else {
			removeInternal(toRemove, parent);
		}
		
		return true;
	}
	
	
	private void removeLeaf(BTNode<T> toRemove, BTNode<T> parent) {
		
		if (parent == null) {
			// uklanja se koren
			root = null;
		} else {
			
			boolean left = parent.left() == toRemove;
			if (left) parent.setLeft(null);
			else	  parent.setRight(null);
		}
	}
	
	
	private void removeInternalWithOneChild(BTNode<T> toRemove, BTNode<T> parent) {
		
		// child -- jedino dete toRemove cvora
		BTNode<T> child = toRemove.left() != null ? toRemove.left() : toRemove.right();
		
		if (parent == null) {
			// uklanja se koren
			root = null;
		} else {
			
			boolean left = parent.left() == toRemove;
			if (left) parent.setLeft(child);
			else 	  parent.setRight(child);
		}
	}
	
	
	private void removeInternal(BTNode<T> toRemove, BTNode<T> parent) {
		
		// nadji minimum u desnom podstablu i njegovog oca
		BTNode<T> min = toRemove.right();
		BTNode<T> minParent = toRemove;
		while (min.left() != null) {
			minParent = min;
			min = min.left();
		}
		
		T minInfo = min.info();
		
		// izbaci min iz stabla
		if (minParent == toRemove)
			// nije napravljen nijedan korak ulevo
			minParent.setRight(min.right());
		else
			// napravljen je bar jedan korak ulevo
			minParent.setLeft(min.right());
		
		toRemove.setInfo(minInfo);
	}
	
	
	public void printInOrder() {
		
		System.out.print("[ ");
		printInOrder(root);
		System.out.println("]\n");
	}
	
	
	private void printInOrder(BTNode<T> node) {
		
		if (node == null) return;
		
		printInOrder(node.left());
		System.out.print(node + " ");
		printInOrder(node.right());
	}
	
	
	public static void main(String[] args) {
		
		BST s = new BST();
		
		for (int i = 0; i < 10; i++) {
			int n = new Random().nextInt(10);
			System.out.println("Inserting " + n + "...");
			s.insert(n);
		}
		
		s.printInOrder();

		for (int i = 0; i < 10; i++) {
			int n = new Random().nextInt(10);
			if (s.remove(n)) System.out.println("Deleted " + n + "...");
		}
		
		s.printInOrder();
	}
}
