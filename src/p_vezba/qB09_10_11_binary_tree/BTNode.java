package p_vezba.qB09_10_11_binary_tree;

public class BTNode<T extends Comparable<T>> implements Comparable<BTNode<T>> {

	
	private T info;
	private BTNode<T> left, right;
	
	
	public BTNode(T info) {
		this.info = info;
	}
	
	
	public BTNode(T info, BTNode<T> left, BTNode<T> right) {
		this.info = info;
		this.left = left;
		this.right = right;
	}
	
	
	public BTNode<T> left()					{ return left;				}
	public void setLeft(BTNode<T> left) 	{ this.left = left;			}
	
	public BTNode<T> right()				{ return right;				}
	public void setRight(BTNode<T> right) 	{ this.right = right;		}
	
	public T info()							{ return info;				}
	public void setInfo(T info)				{ this.info = info;			}
	
	public String toString() 				{ return info.toString();	}
	
	
	@Override
	public int compareTo(BTNode<T> o) {
		return info.compareTo(o.info);
	}
}
