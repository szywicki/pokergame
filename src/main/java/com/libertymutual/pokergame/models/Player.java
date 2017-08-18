package com.libertymutual.pokergame.models;

import java.util.List;

public class Player {

	public int wallet = 0;
		
	public Player (int wallet){
		this.wallet = wallet;
	}
	
	public void makeBet(int betAmount) {
		wallet = wallet - betAmount;
	}
	
	public int getWalletBalance() {
		return wallet;
	}
	
	
}
