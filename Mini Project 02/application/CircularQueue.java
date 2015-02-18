package application;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author
 * 
 */
public class CircularQueue<E> implements FixedSizeQueueInterface<E> {
	private int capacity;
	private E dataArray[];
	private int head;
	private int tail;
	private int size;

	/**
	 * This constructor will instantiate a new circular queue of the size given
	 * as an attribute.
	 * 
	 * @param maxQueueSize This is the capacity of the circular queue.
	 * @throws Exception An exception will be thrown if an invalid capacity is passed into the method.
	 */
	public CircularQueue(int maxQueueSize) throws Exception {
		super();
		//if (maxQueueSize!=0)
		if (maxQueueSize<=0)
		{
			throw new Exception("Queue capacity invalid.");
		}
//		this.capacity = maxQueueSize+1;
		this.capacity = maxQueueSize;
		clear();
	}

	@Override
	public boolean add(E arg0) {
		return offer(arg0);
	}

	@Override
	public E element() {
		if (size == 0) {
			throw new NoSuchElementException("Circular queue is empty.");
		} else {
			//return dataArray[tail-1];
			return dataArray[head];
		}
	}

	@Override
	public boolean offer(E arg0) {
		boolean retVal = false;
		//if (this.size < this.capacity) {
			//this.dataArray[head+1] = arg0;
			//head = (head) % capacity;
			
			this.dataArray[tail] = arg0;
			tail = (tail + 1) % capacity;
			this.size++;
			retVal = true;
		//}
		return retVal;
	}

	@Override
	public E peek() {
		if (size == 0) {
			return null;
		} else {
			//return dataArray[tail-1];
			return dataArray[head];
		}
	}

	@Override
	public E poll() {
		E retVal = null;
		if (size == 0) {
			// DO nothing.
		} else {
//			retVal = dataArray[tail-1];
//			dataArray[tail-1] = null;
			
			retVal = dataArray[head];
			dataArray[head] = null;
			head = (head + 1) % capacity;
			size--;
		}
		return retVal;

	}

	@Override
	public E remove() {
		if (size == 0) {
			throw new NoSuchElementException("Circular queue is empty.");
		} else {
//			E retVal = dataArray[tail-1];
//			dataArray[tail-1] = null;
//			tail = (tail) % capacity;
			
			E retVal = dataArray[head];
			dataArray[head] = null;
			head = (head+1) % capacity;
			size--;
			
			return retVal;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		dataArray = ((E[]) new Object[capacity]);
		head = 0;
		tail = 0;
		size = 0;
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
	public boolean isEmpty() {
//		if (this.size != 0) {
//			return true;
//		} else {
//			return false;
//		}
		
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
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
	public int size() {
		return this.size;
	}

	@Override
	public Object[] toArray() {
		Object retVal[] = new Object[size];

		for (int index = 0; index < size; index++) {
			int myOffset = (head + index) % this.capacity;
			retVal[index] = this.dataArray[myOffset];
		}
		return retVal;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException("Method not yet supported.");
	}

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

}
