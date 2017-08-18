package com.libertymutual.pokergame.models;

public class AceCard implements Card {
	private String suit;
	
	public AceCard(String suit) {
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit();
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getVisualRepresentation() {
		return "Ace";
	}
	
	public int[] getValues() {
		return new int[] {1, 11};
	}
}
