package org.OracleTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sql.test.Card;
import com.sql.test.Deck;
import com.sql.test.DeckImp;
import com.sql.test.Player;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Test number of cards :-)
	 */
	public void testNumOfCards() {
		Deck d1 = new DeckImp(false);		
		assertEquals(d1.getNumOfCard(), 52);
		Deck d2 = new DeckImp(true);		
		assertEquals(d2.getNumOfCard(), 54);		
	}
	
	/**
	 * Test number of cards :-)
	 */
	public void testDrawCardsNoJokers() {
		Deck d1 = new DeckImp(false);
		Set<Card> cards = new HashSet<Card>();
		for (int i = 0; i < 52; i++) {
			Card c = d1.draw();
			cards.add(c);
		}
		cards.add(new Card("Spade", 8)); // add an duplicate purposely
		assertEquals(52, cards.size()); // should just be 52 cards, no duplicates	
	}	
	
	/**
	 * Test number of cards :-)
	 */
	public void testDrawCardsWithJokers() {
		Deck d1 = new DeckImp(true);
		Set<Card> cards = new HashSet<Card>();
		for (int i = 0; i < 54; i++) {
			Card c = d1.draw();
			cards.add(c);
		}
		cards.add(new Card("Spade", 8)); // add an duplicate purposely
		cards.add(new Card("Joker", 1)); // add an duplicate purposely
		cards.add(new Card("Joker", 2)); // add an duplicate purposely
		assertEquals(54, cards.size()); // should just be 54 cards, no duplicates	
	}
	
	/**
	 * Test get top of cards :-)
	 */
	public void testGetTopAfterAllDrawn() {
		Deck d1 = new DeckImp(true);
		Set<Card> cards = new HashSet<Card>();
		for (int i = 0; i < 54; i++) {
			Card c = d1.draw();
			cards.add(c);
		}
		assertEquals(d1.getTop(), null);
	}	
	
	/**
	 * Test get top of cards :-)
	 */
	public void testGetTopInTheMiddleOfDrawn() {
		Deck d1 = new DeckImp(true);
		Set<Card> cards = new HashSet<Card>();
		for (int i = 0; i < 53; i++) {
			Card c = d1.draw();
			cards.add(c);
		}
		assertNotNull(d1.getTop());
	}	
	
	/**
	 * Non F Test
	 * @throws InterruptedException 
	 */
	public void testNFT() throws InterruptedException {
		Deck d1 = new DeckImp(true);
		Set<Card> cards = new HashSet<Card>();
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(new Player(d1, cards));
			list.add(t);
		}
		for (Thread t : list) {
			t.start();			
		}
		Thread.sleep(5000);
	}	
}
