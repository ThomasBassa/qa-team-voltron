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
	 * predominantly by testing various capacity values. */
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
	 * Cause the queue to not be empty
	 * Reduce the number of elements remaining by one
	 * Return true on success, or throw IllegalStateException if full
	 */
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

	@Test
	public void testPoll() {
		size3InitQueue.poll();
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.poll(), 0);
		assertEquals(null, size3InitQueue.poll());
	}

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

	@Test
	public void testPeek() {
		assertEquals(null, size3InitQueue.peek());
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.peek(), 0);
		size3InitQueue.add(1);
		assertEquals(0, size3InitQueue.peek(), 0);
	}
	
	@Test
	public void testIsClear() {
		//TODO implement testIsClear
		fail("Test for Is Clear is not implemented yet");
	}

	@Test
	public void testIsEmpty() {
		assertEquals(size3InitQueue.isEmpty(), true);
		size3InitQueue.add(0);
		assertEquals(size3InitQueue.isEmpty(), false);
	}

	@Test
	public void testSize() {
		assertEquals(0, size3InitQueue.size(), 0);
		size3InitQueue.add(0);
		assertEquals(1, size3InitQueue.size(), 0);
	}

	@Test
	public void testToArray() {
		size3InitQueue.add(0);
		size3InitQueue.add(1);
		assertEquals(0, (int) size3InitQueue.toArray()[0], 0);
		assertEquals(1, (int) size3InitQueue.toArray()[1], 0);
	}

	@Test
	public void testGetQueueCapacity() {
		assertEquals(3, size3InitQueue.getQueueCapacity(), 0);
		size3InitQueue.add(0);
		assertEquals(3, size3InitQueue.getQueueCapacity(), 0);
	}

	@Test
	public void testGetRemainingQueueSpace() {
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
	}

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