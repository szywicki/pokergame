package com.libertymutual.pokergame.models;

public class NumberCard implements Card{
	private String suit;
	private int value;
		
	public NumberCard(int value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public String getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit();
	}
	
	public int[] getValues() {
		return new int[] {value, value };
	}
	
	public String getVisualRepresentation() {
		return String.valueOf(value);
	}
}
