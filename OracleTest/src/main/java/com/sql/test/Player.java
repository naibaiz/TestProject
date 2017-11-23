package com.sql.test;

import java.util.HashSet;
import java.util.Set;

public class Player implements Runnable {

	private Set<Card> cards;
	private Deck d;
	private Set<Card> cardsTestSet;

	public Player(Deck d) {
		this.d = d;
		cards = new HashSet<Card>();
	}

	// for testing purpose
	public Player(Deck d, Set<Card> cardsTestSet) {
		this(d);
		this.cardsTestSet = cardsTestSet;
	}

	public void run() {
		long startTime = System.currentTimeMillis();	
		long finishTime;
		boolean carryOn = true;
		while (carryOn) {
			Card card = d.draw();
			if (card != null) {
				cards.add(card);
				if (cardsTestSet != null) {
					cardsTestSet.add(card);
				}
			} else {
				carryOn = false;
				System.out.println(Thread.currentThread() + ", " + cards.size() + ", " + cards);
				finishTime = System.currentTimeMillis();
				System.out.println(finishTime - startTime + " millis for " + Thread.currentThread());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Set<Card> getCards() {
		return cards;
	}
}
