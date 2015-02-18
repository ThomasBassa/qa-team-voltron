package application;

import java.util.Queue;

public interface FixedSizeQueueInterface<E> extends Queue<E> {
	
	/* Methods inherited from Queue<E>:
	 * boolean add(E e) //return true if OK, throws IllegalStateException if no space
	 * boolean offer(E e) //As add, without exception throwing if full (just return false)
	 * E remove() //throws NoSuchElementException if empty queue
	 * E poll() //returns null if empty queue
	 * E element() //Returns, doesn't remove front. Throws NoSuchElementException if empty
	 * E peek() //Returns front item or null when empty
	 */
	
	/**
	 * This method will obtain the maximum size for the queue, namely the number
	 * of elements that can be held on the queue without it overflowing.
	 * 
	 * @return The maximum size of the queue will be returned.
	 */
	public int getQueueCapacity();

	/**
	 * This method will return the remaining queue space, or the number of
	 * elements that can be added to the queue without it overflowing.
	 * 
	 * @return The number of items that can be added to the queue will be
	 *         returned.
	 */
	public int getRemainingQueueSpace();

	/**
	 * This method will indicate whether the given queue is full or not.
	 * 
	 * @return The method will return true if the queue is full and false
	 *         otherwise.
	 */
	public boolean isQueueFull();

}
