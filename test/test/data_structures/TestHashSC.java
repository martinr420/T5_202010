package test.data_structures;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.data_structures.HashSC;
import model.data_structures.LinkedQueue;
import model.data_structures.noExisteObjetoException;

public class TestHashSC extends TestCase
{
	HashSC<Integer, Character> hash;
	
	@Before
	public void setUp1()
	{
		hash = new HashSC<Integer, Character>(100);
		
		hash.put(1, 'a');
		hash.put(4, 'b');
		hash.put(15, 'e');
		hash.put(2, 'c');
		hash.put(12, 'd');
	}
	
	@Test
	public void testPut()
	{
		setUp1();
		hash.put(10, 'm');

		assertTrue(hash.get(10) == 'm');
	}
	
	@Test
	public void testGet()
	{
		setUp1();
		assertTrue('a' == hash.get(1));
		assertTrue('b' == hash.get(4));
		assertTrue('e' == hash.get(15));
		assertTrue('c' == hash.get(2));
		assertTrue('d' == hash.get(12));
	}
	
	@Test
	public void testDelete()
	{
		setUp1();
		assertTrue(hash.delete(1) == 'a');
	}
	
	@Test
	public void testKeys()
	{
		setUp1();
		LinkedQueue<Integer> q = (LinkedQueue<Integer>) hash.keys();
		try {
			assertTrue(q.dequeue() != null);
		} catch (noExisteObjetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
