package week8;

public class LinkedStack<T> implements Stack<T>{

	private static class Node<T> {
		private T item;
		private Node<T> next;
		private Node() {
			next = null;
		}
		private Node(T item) {
			this(item, null);
		}
		private Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}

	private Node<T> head = null;
	private int size=0;

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T peek() {
		if (isEmpty())
			throw new java.util.EmptyStackException();
		return head.item;
	}

	@Override
	public T pop() {
		Node<T> curr = head;
		if(head.next != null)
			head = head.next;
		size--;
		return curr.item;
	}

	@Override
	public void push(T item) {
		Node<T> node = new Node<T>(item);
		node.next = head;
		head = node;
		size++;
	}

	@Override
	public int size() {
		return size;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node<T> curr = head;
		while(curr!= null){
			sb.append(curr.item+" ");
			curr = curr.next;
		}
		return sb.toString();
	}

}
