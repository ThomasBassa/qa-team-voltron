package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.CircularQueue;


public class CircularQueueTest {

	private CircularQueue<Integer> size3InitQueue;

	/** Before each test, construct a basic CircularQueue that can
	 * hold three integers.*/
	@Before
	public void beforeMethod() {
		size3InitQueue = new CircularQueue<Integer>(3);
	}

	/** Ensure the circular queue constructor functions,
	 * predominantly by testing various capacity values.
	 * Positive values should succeed. Zero or negative values
	 * should throw an exception. */
	@Test
	public void testCircularQueueConstructor() {
		try {
			size3InitQueue = new CircularQueue<Integer>(3);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Valid constructor fails.");
		}

		try {
			size3InitQueue = new CircularQueue<Integer>(0);
			fail("Circular queue created with 0 capacity");
		} catch (IllegalArgumentException e) {
			//Pass
		}

		try {
			size3InitQueue = new CircularQueue<Integer>(-1);
			fail("Circular queue created with -1 capacity");
		} catch (IllegalArgumentException e) {
			//Pass
		}


	}

	/** Test the add method.
	 * It should...
	 * <ul>
	 * <li>Cause the queue to not be empty</li>
	 * <li>Reduce the number of elements remaining by one if queue not full</li>
	 * <li>Return true, or throw IllegalStateException if full</li>
	 * </ul> */
	@Test
	public void testAdd() {
		//TODO testAdd involves remove?
		//TODO what happens when you add too many elements?
		size3InitQueue.add(0);
		size3InitQueue.add(1);
		size3InitQueue.add(2);
		size3InitQueue.add(3);
		assertEquals(3, size3InitQueue.remove(), 0);
		assertEquals(1, size3InitQueue.remove(), 0);
		assertEquals(2, size3InitQueue.remove(), 0);
	}

	/** Test the offer method.
	 * It should...
	 * <ul>
	 * <li>Cause the queue to not be empty</li>
	 * <li>Reduce the number of elements remaining by one if queue not full</li>
	 * <li>Return true, or false if queue is full</li>
	 * </ul> */
	@Test
	public void testOffer() {
		size3InitQueue.offer(0);
		size3InitQueue.offer(1);
		size3InitQueue.offer(2);
		size3InitQueue.offer(3);
		assertEquals(3, size3InitQueue.remove(), 0);
		assertEquals(1, size3InitQueue.remove(), 0);
		assertEquals(2, size3InitQueue.remove(), 0);
		//TODO test the return is correct?
	}

	/** Test the remove method.
	 * It should...
	 * <ul>
	 * <li>Return the next element in FIFO order
	 * (and not return the same thing twice if added elements are distinct)</li>
	 * <li>Increase the number of elements remaining by one if queue not empty</li>
	 * <li>Throw NoSuchElementException if queue is empty</li>
	 * </ul> */
	@Test
	public void testRemove() {
		try {
			size3InitQueue.remove();
			//TODO maybe fail?
		} catch (Exception e) {
			//expected exception thrown
		}
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.remove(), 0);
		size3InitQueue.add(1);
		size3InitQueue.add(2);
		size3InitQueue.add(3);
		assertEquals(1, size3InitQueue.remove(), 0);
	}

	/** Test the poll method.
	 * It should...
	 * <ul>
	 * <li>Return the next element in FIFO order
	 * (and not return the same thing twice if added elements are distinct)</li>
	 * <li>Increase the number of elements remaining by one if queue not empty</li>
	 * <li>Return null if queue is empty</li>
	 * </ul> */
	@Test
	public void testPoll() {
		size3InitQueue.poll();
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.poll(), 0);
		assertEquals(null, size3InitQueue.poll());
	}

	/** Test the element method.
	 * It should...
	 * <ul>
	 * <li>Return the front element of the queue, repeatedly</li>
	 * <li>Not change number of elements remaining</li>
	 * <li>Throw NoSuchElementException if queue is empty</li>
	 * </ul> */
	@Test
	public void testElement() {
		try {
			size3InitQueue.element();
			//TODO probably needs to fail here
		} catch (Exception e) {
			//expected exception thrown
		}
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.element(), 0);
		size3InitQueue.add(1);
		assertEquals(0, size3InitQueue.element(), 0);
	}

	/** Test the peek method.
	 * It should...
	 * <ul>
	 * <li>Return the front element of the queue, repeatedly</li>
	 * <li>Not change number of elements remaining</li>
	 * <li>Return null if queue is empty</li>
	 * </ul> */
	@Test
	public void testPeek() {
		assertEquals(null, size3InitQueue.peek());
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.peek(), 0);
		size3InitQueue.add(1);
		assertEquals(0, size3InitQueue.peek(), 0);
	}

	/** Test the clear method.
	 * It should...
	 * <ul>
	 * <li>Remove all elements</li>
	 * <li>Cause isEmpty to return true</li>
	 * <li>Cause the number of elements remaining to equal capacity</li>
	 * </ul> */
	@Test
	public void testClear() {
		//TODO implement testClear
		size3InitQueue.clear();
		fail("Test for Is Clear is not implemented yet");
	}

	/** Test the isEmpty method.
	 * It should...
	 * <ul>
	 * <li>Return true on create, after clear(), or after using remove/poll enough</li>
	 * <li>Return true when the number of elements remaining equals capacity</li>
	 * <li>Return false otherwise</li>
	 * <li>Not return the same result as isFull</li>
	 * </ul> */
	@Test
	public void testIsEmpty() {
		assertEquals(size3InitQueue.isEmpty(), true);
		size3InitQueue.add(0);
		assertEquals(size3InitQueue.isEmpty(), false);
	}

	/** Test the size method.
	 * It should...
	 * <ul>
	 * <li>Return increasing numbers when add/offer are used</li>
	 * <li>Return decreasing numbers when remove/poll are used</li>
	 * <li>Always return a value between 0 and capacity, inclusive</li>
	 * </ul> Capacity - size - remaining elements == 0 */
	@Test
	public void testSize() {
		assertEquals(0, size3InitQueue.size(), 0);
		size3InitQueue.add(0);
		assertEquals(1, size3InitQueue.size(), 0);
	}

	/** Test the toArray method.
	 * This method should produce an array where
	 * the size is equal to the number of utilized elements,
	 * and the order is the same as would be obtained through repeated
	 * use of remove/poll.
	 * This array is independent of the one maintained by the queue
	 * and altering its elements will not impact the queue. */
	@Test
	public void testToArray() {
		size3InitQueue.add(0);
		size3InitQueue.add(1);
		assertEquals(0, (int) size3InitQueue.toArray()[0], 0);
		assertEquals(1, (int) size3InitQueue.toArray()[1], 0);
	}

	/** Test the queue capacity method.
	 * It should always return the same value as used by
	 * the constructor, regardless of other queue operations. */
	@Test
	public void testGetQueueCapacity() {
		assertEquals(3, size3InitQueue.getQueueCapacity(), 0);
		size3InitQueue.add(0);
		assertEquals(3, size3InitQueue.getQueueCapacity(), 0);
	}

	/** Test the remaining space method.
	 * It should...
	 * <ul>
	 * <li>Return decreasing numbers when add/offer are used</li>
	 * <li>Return increasing numbers when remove/poll are used</li>
	 * <li>Always return a value between 0 and capacity, inclusive</li>
	 * </ul> Capacity - size - remaining elements == 0 */
	@Test
	public void testGetRemainingQueueSpace() {
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
	}

	/** Test the isEmpty method.
	 * It should...
	 * <ul>
	 * <li>Return false on create, after clear(), or after using remove/poll enough</li>
	 * <li>Return false when the number of elements remaining equals capacity</li>
	 * <li>Return true otherwise (i.e. after enough add/offer)</li>
	 * <li>Not return the same result as isEmpty</li>
	 * </ul> */
	@Test
	public void testIsQueueFull() {
		assertEquals(false, size3InitQueue.isQueueFull());
		size3InitQueue.add(0);
		assertEquals(false, size3InitQueue.isQueueFull());
		size3InitQueue.add(1);
		assertEquals(false, size3InitQueue.isQueueFull());
		size3InitQueue.add(2);
		assertEquals(true, size3InitQueue.isQueueFull());
		size3InitQueue.remove();
		assertEquals(false, size3InitQueue.isQueueFull());
	}
}