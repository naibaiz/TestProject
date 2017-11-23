package com.sql.test;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author sonic
 * a main class that runs the deck functions
 * using thread to draw card until all is drawn.
 */
public class DeckRun {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Deck d = new DeckImp(false);
		d.shuffle();
		Card c;
		for (int i = 0; i < 56; i++) {
			c = d.draw();
			if (c != null) {
				System.out.println("Drawn: " + c);
			} else {
				System.out.println("All Drawn, current number of draw is " + i + ", please shuffle before draw again.");
			}
		}
		
		d.shuffle();
		Set<Card> cardsTestSet = new HashSet<Card>();		
		Thread t1 = new Thread(new Player(d, cardsTestSet));
		Thread t2 = new Thread(new Player(d, cardsTestSet));
		Thread t3 = new Thread(new Player(d, cardsTestSet));
		Thread t4 = new Thread(new Player(d, cardsTestSet));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();		
		
		Thread.sleep(17000);
		System.out.println(cardsTestSet.size() + ", " + cardsTestSet);
	}

}
