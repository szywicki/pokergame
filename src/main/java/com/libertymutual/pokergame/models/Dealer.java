package com.libertymutual.pokergame.models;

import java.util.List;

public class Dealer {

	private Hand hand = new Hand();
	
	public void takeCard(Card secondCard) {
		hand.addCard(secondCard);		
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

}
