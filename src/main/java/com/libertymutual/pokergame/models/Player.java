package com.libertymutual.pokergame.models;

import java.util.List;

public class Player {

	private int wallet;
	private Hand hand = new Hand();
	private int bet;
		
	public Player (){
		this.wallet = 100;
		this.bet = 0;
	}
	
	public void makeBet(int betAmount) {
		bet = betAmount;
		if (bet <= wallet) {
		wallet = wallet - bet;
	} else {
		bet = 0;
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

	public int getWinner() {
		return hand.getBestHand();
	}

	public void payout(int money) {
		wallet += money;
	}
	
	public void playerClearHand() {
		hand.clearHand();
	}

	public int getBet() {
		return bet;
	}
	
}
