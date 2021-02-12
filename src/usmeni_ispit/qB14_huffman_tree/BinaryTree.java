package usmeni_ispit.qB14_huffman_tree;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {

	
	private BTNode<T> root = null;
	
	
	public void setRoot(BTNode<T> root)	{ this.root = root;	}
	public BTNode<T> root() 			{ return root; 		}
	
	
	public boolean isEmpty() 		{ return root == null; 	}
	
	
	public int size() {
		return root == null ? 0 : size(root);
	}
	
	
	private int size(BTNode<T> curr) {
		
		int leftSize = 0;
		BTNode<T> left = curr.left();
		if (left != null) leftSize = size(left);
		
		int rightSize = 0;
		BTNode<T> right = curr.right();
		if (right != null) rightSize = size(right);
		
		return 1 + leftSize + rightSize;
	}
	
	
	public int depth() {
		return root == null ? 0 : depth(root);
	}
	
	
	private int depth(BTNode<T> curr) {
		
		int depthLeft = -1;
		BTNode<T> left = curr.left();
		if (left != null) depthLeft = depth(left);
		
		int depthRight = -1;
		BTNode<T> right = curr.right();
		if (right != null) depthRight = depth(right);
		
		if (depthLeft > depthRight) return 1 + depthLeft;
		else 						return 1 + depthRight;
	}
	
	
	public BTNode<T> dfs(T info) {
		return root != null ? dfs(root, info) : null;
	}
	
	
	private BTNode<T> dfs(BTNode<T> curr, T info) {
		
		if (curr.info().equals(info))
			return curr;
		
		BTNode<T> left = curr.left();
		if (left != null) {
			BTNode<T> n = dfs(left, info);
			if (n != null) return n;
		}
		
		BTNode<T> right = curr.right();
		if (right != null) {
			BTNode<T> n = dfs(right, info);
			if (n != null) return n;
		}
		
		return null;
	}
	
	
	private BTNode<T> dfsIter(T info) {
		
		if (root == null) return null;
		
		Stack<BTNode<T>> stack = new Stack<BTNode<T>>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			
			BTNode<T> f = stack.pop();
			if (f.info().equals(info)) return f;
			
			BTNode<T> right = f.right();
			if (right != null) stack.push(right);
			
			BTNode<T> left = f.left();
			if (left != null) stack.push(left);
		}
		
		return null;
	}
	
	
	private BTNode<T> bfs (T info) {
		
		if (root == null) return null;
		
		LinkedList<BTNode<T>> queue = new LinkedList<BTNode<T>>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			
			BTNode<T> f = queue.removeFirst();
			
			if (f.info().equals(info)) return f;
			
			BTNode<T> left = f.left();
			if (left != null) queue.addLast(left);
			
			BTNode<T> right = f.right();
			if (right != null) queue.addLast(right);
		}
		
		return null;
	}
}
