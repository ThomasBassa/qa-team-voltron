package application;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** A fixed size queue that utilizes an array and a set of
 * rotating "pointers" to maintain its data. */
public class CircularQueue<E> implements FixedSizeQueueInterface<E> {

	/** The maximum number of elements this CircularQueue may hold. */
	private int capacity;

	/** The internal array used to store the elements. */
	private E dataArray[];

	/** "Pointer" to the front of the queue */
	private int head;

	/** "Pointer" to the back of the queue */
	private int tail;

	/** The number of elements currently stored by this CircularQueue */
	private int size;

	/**
	 * This constructor will instantiate a new circular queue of the size given
	 * as an attribute.
	 * 
	 * @param maxQueueSize This is the capacity of the circular queue.
	 * @throws IllegalArgumentException An exception will be thrown if an invalid capacity is passed into the method.
	 */
	public CircularQueue(int maxQueueSize) {
/***************************************ERROR IN FOLLOWING CODE********************************************************/
		//if (maxQueueSize!=0)
/*************************************************************************************************************************/
		if(maxQueueSize <= 0) {
			throw new IllegalArgumentException("Queue capacity must be greater than zero.");
		}
		this.capacity = maxQueueSize;
		clear();
	}

	//Methods implemented from Queue
	@Override
	public boolean add(E arg0) {
		return offer(arg0);
	}

	@Override
	public boolean offer(E element) {
		boolean retVal = false;

/***************************************ERROR IN FOLLOWING CODE********************************************************/
		//if (this.size < this.capacity) {
		//this.dataArray[head+1] = arg0;
		//head = (head) % capacity;
/*************************************************************************************************************************/

/****************************************FIX WAS TO UPDATE TAIL RATHER THAN HEAD**************************************/
		this.dataArray[tail] = element;
		tail = (tail + 1) % capacity;
		this.size++;
/***************************************************************************************************************************/
		retVal = true;
		//}
		return retVal;
	}

	@Override
	public E remove() {
		if(size == 0) {
			throw new NoSuchElementException("Circular queue is empty.");
		} else {

/***************************************ERROR IN FOLLOWING CODE********************************************************/
			//E retVal = dataArray[tail-1];
			//dataArray[tail-1] = null;
			//tail = (tail) % capacity;
/*************************************************************************************************************************/

/****************************************FIX WAS TO UPDATE THE HEAD RATHER THAN TAIL**************************************/
			E retVal = dataArray[head];
			dataArray[head] = null;
			head = (head + 1) % capacity;
/***************************************************************************************************************************/
			size--;

			return retVal;
		}
	}

	@Override
	public E poll() {
		E retVal = null;
		if(size == 0) {
			//Do nothing.
		} else {
/***************************************ERROR IN FOLLOWING CODE********************************************************/
			//retVal = dataArray[tail-1];
			//dataArray[tail-1] = null;
/*************************************************************************************************************************/

/****************************************FIX WAS TO UPDATE THE HEAD RATHER THAN TAIL**************************************/
			retVal = dataArray[head];
			dataArray[head] = null;
/***************************************************************************************************************************/
			head = (head + 1) % capacity;
			size--;
		}
		return retVal;

	}

	@Override
	public E element() {
		if(size == 0) {
			throw new NoSuchElementException("Circular queue is empty.");
		} else {
/***************************************ERROR IN FOLLOWING CODE********************************************************/
			//return dataArray[tail-1];
/*************************************************************************************************************************/

/****************************************FIX WAS TO UPDATE THE HEAD RATHER THAN TAIL**************************************/
			return dataArray[head];
/*************************************************************************************************************************/
		}
	}

	@Override
	public E peek() {
		if(size == 0) {
			return null;
		} else {
/***************************************ERROR IN FOLLOWING CODE********************************************************/
			//return dataArray[tail-1];
/*************************************************************************************************************************/
			
/****************************************FIX WAS TO RETURN THE HEAD RATHER THAN TAIL**************************************/
			return dataArray[head];
/*************************************************************************************************************************/
		}
	}

	//Methods implemented from Collection
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		dataArray = ((E[]) new Object[capacity]);
		head = 0;
		tail = 0;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
/***************************************ERROR IN FOLLOWING CODE********************************************************/
		//if (this.size != 0) {
		//	return true;
		//} else {
		//	return false;
		//}
/*************************************************************************************************************************/

/****************************************FIX WAS TO RETURN COMPARRISON OF SIZE VS 0**************************************/
		return this.size == 0;
/*************************************************************************************************************************/
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Object[] toArray() {
		Object retVal[] = new Object[size];

		for(int index = 0; index < size; index++) {
			int myOffset = (head + index) % this.capacity;
			retVal[index] = this.dataArray[myOffset];
		}
		return retVal;
	}

	//Methods implemented from FixedSizeQueueInterface
	@Override
	public int getQueueCapacity() {
		return this.capacity;
	}

	@Override
	public int getRemainingQueueSpace() {
		return this.capacity - this.size;
	}

	@Override
	public boolean isQueueFull() {
		return (this.size >= this.capacity);
	}

	//The following methods from Collection were given unimplemented,
	//and are NOT to be implemented or tested as part of Mini-Project 2
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	@Override
	public boolean contains(Object arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}
	
	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}
}
