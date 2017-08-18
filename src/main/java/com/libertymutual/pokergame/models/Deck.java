package com.libertymutual.pokergame.models;

public class Deck {

	private Card[] cards;
	
	public Deck() {
		String[] suits = new String[] {"Diamonds", "Clubs", "Spades", "Hearts"};
		int i = 0;
		cards = new Card[52];
		
		for(String suit : suits) {
			cards[i] = new AceCard(suit);
			i += 1;
			
			cards[i] = new FaceCard("Jack", suit);
			i += 1;
			
			cards[i] = new FaceCard("Queen", suit);
			i += 1;
			
			cards[i] = new FaceCard("King", suit);
			i += 1;
		}
	}
}
