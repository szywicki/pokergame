package com.libertymutual.pokergame.models;

import java.util.List;

public class Player {

	private int wallet = 0;
	private Hand hand = new Hand();
		
	public Player (int wallet){
		this.wallet = wallet;
	}
	
	public void makeBet(int betAmount) {
		if (wallet != 0) {
		wallet = wallet - betAmount;
	} else {
		betAmount = 0;
	}
	}
	
	public int getWalletBalance() {
		return wallet;
	}

	public void takeCard(Card firstCard) {
		 hand.addCard(firstCard);
		
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

	public boolean isBust() {
		int[] values = hand.getValues();
		return values[0] > 21 && values[1] > 21;
	}
	
	public boolean hasBlackjack() {
		return hand.isBlackjack();
	}
	
}
