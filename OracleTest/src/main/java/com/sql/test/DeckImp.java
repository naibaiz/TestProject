package com.sql.test;

import com.sql.lock.DeckLock;

public class DeckImp implements Deck {
	/**
	 * An array of cards.
	 */
	private Card[] deck;
	private int numOfCard;
	private int numDrawn;
	private DeckLock lock;

	public DeckImp(boolean withJoker) {
		numDrawn = 0;
		int numSuites = Card.suites.length;
		int numRanks = Card.ranks.length;
		numOfCard = 52;
		if (withJoker) {
			numOfCard += 2;
		}
		deck = new Card[numOfCard];

		// create all cards first
		for (int s = 0; s < numSuites; s++) {
			for (int r = 0; r < numRanks; r++) {
				Card card = new Card(Card.suites[s], Card.ranks[r]);
				deck[s * numRanks + r] = card;
			}
		}
		if (withJoker) {
			deck[52] = new Card("Joker", 1);
			deck[53] = new Card("Joker", 2);
		}
		lock = new DeckLock();
	}

	public void shuffle() {
		numDrawn = 0;
		// then shuffle into random order
		for (int i = deck.length - 1; i > 0; i--) {
			int rand = (int) (Math.random() * (i + 1)); // get a random number
														// between 0 and deck
														// length - 1
			try {
				lock.lock();
				Card temp = deck[i]; // current card
				deck[i] = deck[rand]; // set current card to random card
				deck[rand] = temp; // put current card back to random card location
			} catch(InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	public Card draw() {
		Card selected = null;
		if (numDrawn == deck.length) {
			return null;
		}
		int rand = (int) (Math.random() * (deck.length - numDrawn - 1));
		selected = deck[rand];		
		try {
			lock.lock();
			// swap the selected with the last one in the deck
			// then increse the numDrawn by one
			// so next draw won't get the drawn cards
			Card temp = selected;
			deck[rand] = deck[deck.length - numDrawn - 1];
			deck[deck.length - numDrawn - 1] = temp;

			numDrawn++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return selected;
	}

	/**
	 * get top of the cards
	 */
	public Card getTop() {
		if (numDrawn == deck.length) {
			return null;
		}
		return deck[0];
	}

	/**
	 * get bottom of the undrawn cards
	 */
	public Card getBottom() {
		if (numDrawn == deck.length) {
			return null;
		}
		return deck[deck.length - numDrawn - 1];
	}

	public int getNumOfCard() {
		return numOfCard;
	}
}
