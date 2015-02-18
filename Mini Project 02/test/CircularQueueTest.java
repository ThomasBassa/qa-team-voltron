package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.CircularQueue;


public class CircularQueueTest {

	CircularQueue<Integer> size3InitQueue;

	@Before
	public void setUp() throws Exception {
		size3InitQueue = new CircularQueue<Integer>(3);
	}

	@Test
	public void testCircularQueue() {
		try {
			size3InitQueue = new CircularQueue<Integer>(3);
		} catch (Exception e) {
			// TODO Is 3 supposed to fail?
			e.printStackTrace();
		}
		try {
			size3InitQueue = new CircularQueue<Integer>(-1);
		} catch (Exception e) {
			// TODO Is -1 supposed to fail?
			e.printStackTrace();
		}
	}

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
	public void testAddAll() {
		//method not implemented
		try {
			size3InitQueue.addAll(size3InitQueue);
		} catch (Exception e) {

		}
	}


	@Test
	public void testContains() {
		//method not implemented
		try {
			size3InitQueue.contains(size3InitQueue);
		} catch (Exception e) {

		}
	}

	@Test
	public void testContainsAll() {
		//method not implemented
		try {
			size3InitQueue.containsAll(size3InitQueue);
		} catch (Exception e) {

		}
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
	public void testIsEmpty() {
		assertEquals(size3InitQueue.isEmpty(), true);
		size3InitQueue.add(0);
		assertEquals(size3InitQueue.isEmpty(), false);
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

	@Test
	public void testIterator() {
		//method not implemented
		try {
			size3InitQueue.iterator();
		} catch (Exception e) {
			//expected exception thrown
		}
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
	public void testPeek() {
		assertEquals(null, size3InitQueue.peek());
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.peek(), 0);
		size3InitQueue.add(1);
		assertEquals(0, size3InitQueue.peek(), 0);
	}

	@Test
	public void testPoll() {
		size3InitQueue.poll();
		size3InitQueue.add(0);
		assertEquals(0, size3InitQueue.poll(), 0);
		assertEquals(null, size3InitQueue.poll());
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
	public void testRemoveObj() {
		//method not implemented
		try {
			size3InitQueue.remove(size3InitQueue);
		} catch (Exception e) {
			//expected exception thrown
		}
	}

	@Test
	public void testRemoveAll() {
		//method not implemented
		try {
			size3InitQueue.removeAll(size3InitQueue);
		} catch (Exception e) {
			//expected exception thrown
		}
	}

	@Test
	public void testRetainAll() {
		//method not implemented
		try {
			size3InitQueue.retainAll(size3InitQueue);
		} catch (Exception e) {
			//expected exception thrown
		}
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
	public void testToArrayA() {
		//method not implemented
	}
}