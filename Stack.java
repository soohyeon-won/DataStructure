package week8;

interface Stack<T> {
	public boolean isEmpty();
	public T peek();
	public T pop();
	public void push(T item);
	public int size();
}
