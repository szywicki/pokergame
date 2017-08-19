package com.libertymutual.pokergame.models;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

	private Hand hand;
	private boolean hideHoleCard;
	private Deck deck;
	
	public Dealer() {
		hand = new Hand();
		deck = new Deck();
		deck.shuffle();
	}
	
	public int getNumberOfCardsLef() {
		return deck.getNumberOfCardsLeft();
	}
	
	public void givePlayerCard(Player player) {
		Card card = deck.getCard();
		if (card != null) {
			player.takeCard(card);
		}
	}
	
	public void giveDealerCard() {
		Card card = deck.getCard();
		if (card != null) {
			hand.addCard(card);
		}
	}
	
	public List<Card> getCards() {
		List<Card> cards = hand.getCards();
		if (!hideHoleCard || cards.size() == 0) {
		return cards;
	}
		Card firstCard = cards.get(0);
		List<Card> cardsToShow = new ArrayList<Card>();
		cardsToShow.add(firstCard);
		cardsToShow.add(new HoleCard());
		return cardsToShow;
	}

	public boolean isBust() {
		int[] values = hand.getValues();
		return values[0] > 21 && values[1] > 21;
	}
		
	public void newRound() {
		hideHoleCard = true;
		hand = new Hand();
	}
	
	public boolean hasBlackjack() {
		return hand.isBlackjack();
	}
	
	public void endRound() {
		hideHoleCard = false;
		int [] values = hand.getValues();
		if (values[0] == 21 || values[1] == 21) {
			return;
		}
		while (values[0] < 17 || values[1] < 17) {
			giveDealerCard();
			values = hand.getValues();
		}
	}
	
}
