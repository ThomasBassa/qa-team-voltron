package test;


import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.FixedSizeQueueInterface;
import application.CircularQueue;

public class CircularQueueTest extends TestCase{
	//FixedSizeQueueInterface<Integer> q;
	CircularQueue<Integer> q;
	public void setUp() throws Exception {
		try{
			q = new CircularQueue<Integer>(3);
		}catch(Exception e){
			
		}
	}
	
	@Test
	public void testCircularQueue(){
		try {
			q = new CircularQueue<Integer>(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			q = new CircularQueue<Integer>(-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdd(){
		q.add(0);
		q.add(1);
		q.add(2);
		q.add(3);
		assertEquals(3, q.remove(), 0);
		assertEquals(1, q.remove(), 0);
		assertEquals(2, q.remove(), 0);
		
	}

	@Test
	public void testAddAll(){
		//method not implemented
		try{
			q.addAll(q);
		} catch (Exception e){
			
		}
	}
	
	
	@Test
	public void testContains(){
		//method not implemented
		try{
			q.contains(q);
		} catch (Exception e){
			
		}
	}
	
	@Test
	public void testContainsAll(){
		//method not implemented
		try{
			q.containsAll(q);
		} catch (Exception e){
			
		}
	}
	
	@Test
	public void testElement(){
		try{
			q.element();
		} catch (Exception e){
			//expected exception thrown
		}
		q.add(0);
		assertEquals(0, q.element(), 0);
		q.add(1);
		assertEquals(0, q.element(), 0);
	}
	
	@Test
	public void testGetQueueCapacity(){
		assertEquals(3, q.getQueueCapacity(), 0);
		q.add(0);
		assertEquals(3, q.getQueueCapacity(), 0);
	}
	
	@Test
	public void testGetRemeainingQueueSpace(){
		assertEquals(3, q.getRemainingQueueSpace(), 0);
		q.add(0);
		assertEquals(2, q.getRemainingQueueSpace(), 0);
	}
	
	@Test
	public void testIsEmpty(){
		assertEquals(q.isEmpty(), true);
		q.add(0);
		assertEquals(q.isEmpty(), false);
	}
	
	@Test
	public void testIsQueueFull(){
		assertEquals(false, q.isQueueFull());
		q.add(0);
		assertEquals(false, q.isQueueFull());
		q.add(1);
		assertEquals(false, q.isQueueFull());
		q.add(2);
		assertEquals(true, q.isQueueFull());
		q.remove();
		assertEquals(false, q.isQueueFull());
	}
	
	@Test
	public void testIterator(){
		//method not implemented
		try{
			q.iterator();
		} catch (Exception e){
			//expected exception thrown
		}
	}
	
	@Test
	public void testOffer(){
		q.offer(0);
		q.offer(1);
		q.offer(2);
		q.offer(3);
		assertEquals(3, q.remove(), 0);
		assertEquals(1, q.remove(), 0);
		assertEquals(2, q.remove(), 0);
		
	}
	
	@Test
	public void testPeek(){
		assertEquals(null, q.peek());
		q.add(0);
		assertEquals(0, q.peek(), 0);
		q.add(1);
		assertEquals(0, q.peek(), 0);
	}
	
	@Test
	public void testPoll(){
		q.poll();
		q.add(0);
		assertEquals(0, q.poll(), 0);
		assertEquals(null, q.poll());
	}
	
	@Test
	public void testRemove(){
		try{
			q.remove();
		} catch (Exception e){
			//expected exception thrown
		}
		q.add(0);
		assertEquals(0, q.remove(), 0);
		q.add(1);
		q.add(2);
		q.add(3);
		assertEquals(1, q.remove(), 0);
	}
	
	@Test
	public void testRemoveObj(){
		//method not implemented
		try{
			q.remove(q);
		} catch (Exception e){
			//expected exception thrown
		}
	}
	
	@Test
	public void testRemoveAll(){
		//method not implemented
		try{
			q.removeAll(q);
		} catch (Exception e){
			//expected exception thrown
		}
	}
	
	@Test
	public void testRetainAll(){
		//method not implemented
		try{
			q.retainAll(q);
		} catch (Exception e){
			//expected exception thrown
		}
	}
	
	@Test
	public void testSize(){
		assertEquals(0,q.size(),0);
		q.add(0);
		assertEquals(1,q.size(),0);
	}
	
	@Test
	public void testToArray(){
		q.add(0);
		q.add(1);
		assertEquals(0, (int)q.toArray()[0], 0);
		assertEquals(1, (int)q.toArray()[1], 0);
		
		
	}
	
	@Test
	public void testToArrayA(){
		//method not implemented
	}
}