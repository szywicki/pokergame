package com.libertymutual.pokergame.models;

import java.util.List;

public class Player {

	private int wallet = 0;
	private Hand hand = new Hand();
		
	public Player (int wallet){
		this.wallet = wallet;
	}
	
	public void makeBet(int betAmount) {
		wallet = wallet - betAmount;
	}
	
	public int getWalletBalance() {
		return wallet;
	}

	public void takeCard(Card firstCard) {
		 hand.addCard(firstCard);
		
	}
	
	
}
