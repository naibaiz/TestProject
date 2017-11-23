package com.sql.test;

import java.util.Objects;

public class Card {
	public static String suites[] = new String[] {"Spade", "Diamond", "Club", "Heart"};
	public static int ranks[] = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	
	private String suite;
	private int rank;
	
	public Card(String suite, int rank) {
		this.suite = suite;
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		String rankName = String.valueOf(rank);
		if (rank == 1) {
			rankName = "Ace";
		} else if (rank == 11) {
			rankName = "Jack";			
		} else if (rank == 12) {
			rankName = "Queen";			
		} else if (rank == 13) {
			rankName = "King";			
		} 
		return suite + " of " + rankName + " ";
	}
	
	@Override
	public boolean equals(Object o) {	
		if (o instanceof Card) {
			Card c = (Card)o;
			if (c.rank == rank && c.suite.equals(suite)) {
				return true;
			}
		}
		return false;
	}	
	
    @Override
    public int hashCode() {
        return Objects.hash(suite, rank);
    }
}
