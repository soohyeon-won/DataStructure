package week11;

class BSTNode<T> {
	public T item;
	public BSTNode<T> left;
	public BSTNode<T> right;
	public BSTNode(T item) {  // »ý¼ºÀÚ
		this.item = item;
		left = null;
		right = null;
	}
}
