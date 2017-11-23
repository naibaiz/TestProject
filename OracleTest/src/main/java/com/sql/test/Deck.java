package com.sql.test;

public interface Deck {
	void shuffle();
	Card draw();
	Card getTop();
	Card getBottom();
	int getNumOfCard();
}
