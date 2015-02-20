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
	 * </ul> 
	 * @author Thomas */
	@Test
	public void testAdd() {
		//Save initial remaining space for -1 check, add, check not empty
		int initRemaining = size3InitQueue.getRemainingQueueSpace();
		assertTrue(size3InitQueue.add(0));
		assertFalse(size3InitQueue.isEmpty());
		
		//-1 check, cycle variables around for one more -1 check
		int curRemaining = size3InitQueue.getRemainingQueueSpace();
		assertEquals(initRemaining - 1, curRemaining);
		initRemaining = curRemaining;
		
		//Second addition is checked for not empty, -1 conditions
		assertTrue(size3InitQueue.add(1));
		assertFalse(size3InitQueue.isEmpty());
		curRemaining = size3InitQueue.getRemainingQueueSpace();
		assertEquals(initRemaining - 1, curRemaining);
	
		//Third addition is just checked for not empty
		assertTrue(size3InitQueue.add(2));
		assertFalse(size3InitQueue.isEmpty());
		try {
			size3InitQueue.add(3);
			fail("Expected exception when using add on full queue");
		} catch (IllegalStateException e) {
			//Pass throw test.
			//May as well check whether full here...
			assertTrue(size3InitQueue.isQueueFull());
		}	
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
	//Offer one element to queue check if size increased by 1, remaining size decreased by 1
	//and queue is not full
	assertTrue(size3InitQueue.offer(0));
	assertFalse(size3InitQueue.isEmpty());
	assertEquals(1, size3InitQueue.size(), 0);
	assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
	assertFalse(size3InitQueue.isQueueFull());
	
	//Offer second element to queue check if size increased by 1, remaining size decreased by 1
	//and queue is not full
	assertTrue(size3InitQueue.offer(1));
	assertFalse(size3InitQueue.isEmpty());
	assertEquals(2, size3InitQueue.size(), 0);
	assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
	assertFalse(size3InitQueue.isQueueFull());
	
	//Offer third element to queue check if size increased by 1, remaining size decreased by 1
	//and queue is full
	assertTrue(size3InitQueue.offer(2));
	assertFalse(size3InitQueue.isEmpty());
	assertEquals(3, size3InitQueue.size(), 0);
	assertEquals(0, size3InitQueue.getRemainingQueueSpace(), 0);
	assertTrue(size3InitQueue.isQueueFull());
	
	//Offer one element to queue check if size remains the same, remaining size is the same
	//and queue is full
	assertFalse(size3InitQueue.offer(3));
	assertFalse(size3InitQueue.isEmpty());
	assertEquals(3, size3InitQueue.size(), 0);
	assertEquals(0, size3InitQueue.getRemainingQueueSpace(), 0);
	assertTrue(size3InitQueue.isQueueFull());
	
	//Remove elements from queue to ensure they were input in correct order
	assertEquals(0, size3InitQueue.remove(), 0);
	assertEquals(1, size3InitQueue.remove(), 0);
	assertEquals(2, size3InitQueue.remove(), 0);

	}

	/** Test the remove method.
	 * It should...
	 * <ul>
	 * <li>Cause the queue to not be full</li>
	 * <li>Return the next element in FIFO order
	 * (and not return the same thing twice if added elements are distinct)</li>
	 * <li>Increase the number of elements remaining by one if queue not empty</li>
	 * <li>Throw NoSuchElementException if queue is empty</li>
	 * </ul> */
	@Test
	public void testRemove() {
	//Try to remove from empty queue
	//Expected outcome is an exception to be thrown
	try {
		size3InitQueue.remove();
		fail("Removed an element from queue when queue was empty");
	} catch (Exception e) {
		//pass
	}
	
	//Add elements to queue
	size3InitQueue.add(0);
	size3InitQueue.add(1);
	size3InitQueue.add(2);
	
	//Check initial size
	assertEquals(3, size3InitQueue.size(), 0);
	assertEquals(0, size3InitQueue.getRemainingQueueSpace(), 0);
	
	//remove an element. check if it was the element inserted first
	//check if the size was decreased, and the remaining space was increased
	assertEquals(0, size3InitQueue.remove(), 0);
	assertEquals(2, size3InitQueue.size(), 0);
	assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
	
	//remove an element. check if it was the element inserted first
	//check if the size was decreased, and the remaining space was increased
	assertEquals(1, size3InitQueue.remove(), 0);
	assertEquals(1, size3InitQueue.size(), 0);
	assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
	
	//remove an element. check if it was the element inserted first
	//check if the size was decreased, and the remaining space was increased
	assertEquals(2, size3InitQueue.remove(), 0);
	assertEquals(0, size3InitQueue.size(), 0);
	assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
	}

	/** Test the poll method.
	 * It should...
	 * <ul>
	 * <li>Cause the queue to not be full</li>
	 * <li>Return the next element in FIFO order
	 * (and not return the same thing twice if added elements are distinct)</li>
	 * <li>Increase the number of elements remaining by one if queue not empty</li>
	 * <li>Return null if queue is empty</li>
	 * </ul> */
	@Test
	public void testPoll() {
		//poll from empty queue
		assertNull(size3InitQueue.poll());
		
		//add elements to queue
		size3InitQueue.add(0);
		size3InitQueue.add(1);
		size3InitQueue.add(2);
		
		//check the initial size, and remaining queue space
		assertEquals(3, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getRemainingQueueSpace(), 0);
		
		//poll the queue. check if the element polled is the first element inserted
		//check that the size decreased by 1, and the remaining space increase by 1
		assertEquals(0, size3InitQueue.poll(), 0);
		assertEquals(2, size3InitQueue.size(), 0);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
		
		//poll the queue. check if the element polled is the second element inserted
		//check that the size decreased by 1, and the remaining space increase by 1
		assertEquals(1, size3InitQueue.poll(), 0);
		assertEquals(1, size3InitQueue.size(), 0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
		
		//poll the queue. check if the element polled is the third element inserted
		//check that the size decreased by 1, and the remaining space increase by 1
		assertEquals(2, size3InitQueue.poll(), 0);
		assertEquals(0, size3InitQueue.size(), 0);
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
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
		//Try to use element on empty queue
		//Expected exception thrown
		try {
		size3InitQueue.element();
		fail("Expected exception when using element on empty queue");
		} catch (Exception e) {
		//pass
		assertTrue(size3InitQueue.isEmpty());
		}
		
		//Add elements to queue, then use element() to check the front element of queue
		//Check that element() does not alter the size or remaining space of queue
		size3InitQueue.add(0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
		assertEquals(0, size3InitQueue.element(), 0);
		assertEquals(0, size3InitQueue.element(), 0);
		assertEquals(0, size3InitQueue.element(), 0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
		size3InitQueue.add(1);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace());
		assertEquals(0, size3InitQueue.element(), 0);
		assertEquals(0, size3InitQueue.element(), 0);
		assertEquals(0, size3InitQueue.element(), 0);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace());
		
		
		//Remove elements from queue, then use element() to check the front element of queue
		//Check that element() does not alter the size or remaining space of queue
		size3InitQueue.remove();
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
		assertEquals(1, size3InitQueue.element(), 0);
		assertEquals(1, size3InitQueue.element(), 0);
		assertEquals(1, size3InitQueue.element(), 0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
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
		//peek on an empty queue
		assertNull(size3InitQueue.peek());
		
		//Add elements to queue, then use peek() to check the front element of queue
		//Check that peek() does not alter the size or remaining space of queue
		size3InitQueue.add(0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
		assertEquals(0, size3InitQueue.peek(), 0);
		assertEquals(0, size3InitQueue.peek(), 0);
		assertEquals(0, size3InitQueue.peek(), 0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
		size3InitQueue.add(1);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace());
		assertEquals(0, size3InitQueue.peek(), 0);
		assertEquals(0, size3InitQueue.peek(), 0);
		assertEquals(0, size3InitQueue.peek(), 0);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace());
		
		//Remove elements from queue, then use peek() to check the front element of queue
		//Check that peek() does not alter the size or remaining space of queue
		size3InitQueue.remove();
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
		assertEquals(1, size3InitQueue.peek(), 0);
		assertEquals(1, size3InitQueue.peek(), 0);
		assertEquals(1, size3InitQueue.peek(), 0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace());
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
		size3InitQueue.add(1);
		size3InitQueue.add(2);
		size3InitQueue.add(3);
		assertFalse(size3InitQueue.isEmpty());
		
		size3InitQueue.clear();
		assertTrue(size3InitQueue.isEmpty());
		assertEquals(size3InitQueue.getQueueCapacity(), size3InitQueue.getRemainingQueueSpace());
		//Poll multiple times to attempt to get later additions
		assertNull(size3InitQueue.poll());
		assertNull(size3InitQueue.poll());
		assertNull(size3InitQueue.poll());
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
		//check initial queue is empty
		assertTrue(size3InitQueue.isEmpty());
		
		//check initial queue remaining space and capacity are equal
		assertEquals(size3InitQueue.getRemainingQueueSpace(), size3InitQueue.getQueueCapacity(), 0);
		
		//check the initial queue is not full
		assertFalse(size3InitQueue.isQueueFull());
		
		//clear the queue then check that queue is empty
		size3InitQueue.clear();
		assertTrue(size3InitQueue.isEmpty());
		
		//add elements to queue then check that queue is not empty
		size3InitQueue.add(0);
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.add(1);
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.add(2);
		assertFalse(size3InitQueue.isEmpty());
		
		//remove elements from queue check if queue is empty
		size3InitQueue.remove();
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.remove();
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.remove();
		assertTrue(size3InitQueue.isEmpty());
		
		//offer elements to queue then check that queue is not empty
		size3InitQueue.offer(0);
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.offer(1);
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.offer(2);
		assertFalse(size3InitQueue.isEmpty());
		
		//remove elements from queue check if queue is empty
		size3InitQueue.poll();
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.poll();
		assertFalse(size3InitQueue.isEmpty());
		size3InitQueue.poll();
		assertTrue(size3InitQueue.isEmpty());
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
		//check initial size is 0 and queue is empty
		assertEquals(0, size3InitQueue.size(), 0);
		assertTrue(size3InitQueue.isEmpty());
		
		//add elements to queue, check that size increases by 1 each time
		size3InitQueue.add(0);
		assertEquals(1, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.add(1);
		assertEquals(2, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.add(2);
		assertEquals(3, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		
		//remove elements from queue, check that size decreases by 1 each time
		size3InitQueue.remove();
		assertEquals(2, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.remove();
		assertEquals(1, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.remove();
		assertEquals(0, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		
		//offer elements to queue, check that size increases by 1 each time
		size3InitQueue.offer(0);
		assertEquals(1, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.offer(1);
		assertEquals(2, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.offer(2);
		assertEquals(3, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		
		//poll elements from queue, check that size decreases by 1 each time
		size3InitQueue.poll();
		assertEquals(2, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.poll();
		assertEquals(1, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
		size3InitQueue.poll();
		assertEquals(0, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getQueueCapacity() - size3InitQueue.size() - size3InitQueue.getRemainingQueueSpace() , 0);
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
		//add elements to queue, check the remaining space is decreasing by 1
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(1);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(2);
		assertEquals(0, size3InitQueue.getRemainingQueueSpace(), 0);
		
		//remove elements from queue, check the remaining space is increasing by 1
		size3InitQueue.remove();
		assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.remove();
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.remove();
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
		
		//offer elements to queue, check the remaining space is decreasing by 1
		size3InitQueue.offer(0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.offer(1);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.offer(2);
		assertEquals(0, size3InitQueue.getRemainingQueueSpace(), 0);
		
		//poll elements from queue, check the remaining space is increasing by 1
		size3InitQueue.poll();
		assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.poll();
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.poll();
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);

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
		//check if initial queue is full
		assertFalse(size3InitQueue.isQueueFull());
		
		//add elements to queue, check if queue is full, size increases by 1, space decreases by 1 each time
		assertFalse(size3InitQueue.isQueueFull());
		assertEquals(0, size3InitQueue.size(), 0);
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(0);
		assertFalse(size3InitQueue.isQueueFull());
		assertEquals(1, size3InitQueue.size(), 0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(1);
		assertFalse(size3InitQueue.isQueueFull());
		assertEquals(2, size3InitQueue.size(), 0);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.add(2);
		assertTrue(size3InitQueue.isQueueFull());
		assertEquals(3, size3InitQueue.size(), 0);
		assertEquals(0, size3InitQueue.getRemainingQueueSpace(), 0);
		
		//remove elements to queue, check if queue is full, size decreases by 1, space increase by 1 each time
		size3InitQueue.remove();
		assertFalse(size3InitQueue.isQueueFull());
		assertEquals(2, size3InitQueue.size(), 0);
		assertEquals(1, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.remove();
		assertFalse(size3InitQueue.isQueueFull());
		assertEquals(1, size3InitQueue.size(), 0);
		assertEquals(2, size3InitQueue.getRemainingQueueSpace(), 0);
		size3InitQueue.remove();
		assertFalse(size3InitQueue.isQueueFull());
		assertEquals(0, size3InitQueue.size(), 0);
		assertEquals(3, size3InitQueue.getRemainingQueueSpace(), 0);
	}
}
