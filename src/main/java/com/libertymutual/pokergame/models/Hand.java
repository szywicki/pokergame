package com.libertymutual.pokergame.models;

import java.util.ArrayList;

public class Hand {
	
	ArrayList<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public int[] getValues() {
		int[] sums = new int[] {0, 0 };
		
		for (Card c : cards) {
			int[] values = c.getValues();
			sums[0] += values[0];
			sums[1] += values[1];
		}
		
		return sums;
	}
}
