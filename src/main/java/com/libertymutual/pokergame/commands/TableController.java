package com.libertymutual.pokergame.commands;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.pokergame.models.Card;
import com.libertymutual.pokergame.models.Dealer;
import com.libertymutual.pokergame.models.Deck;
import com.libertymutual.pokergame.models.Hand;
import com.libertymutual.pokergame.models.Player;

@Controller
@RequestMapping("/")

public class TableController {
	private boolean canShowHitAndStand;
	private boolean canShowPlayAgain;
	
	// Declare variables
	private Player newPlayer;
	private Dealer newDealer;
	private int currentBet;
	
	// Constructor for Table 
	public TableController () {
		newPlayer = new Player(100);
		newDealer = new Dealer();
		
	}
	
	@PostMapping("") 
	public ModelAndView startTheGame() {				
		ModelAndView mv = new ModelAndView("home/new-game");
		return mv;
	}
	
	@GetMapping("/hand")
	public String refreshTheTable(Model model) {
		model.addAttribute("currentBet", currentBet);
		model.addAttribute("walletBalance", newPlayer.getWalletBalance());
		model.addAttribute("dealerHand", newDealer.getCards());
		model.addAttribute("playerHand", newPlayer.getCards());
		model.addAttribute("canShowHitAndStand", canShowHitAndStand);
		model.addAttribute("canShowPlayAgain",canShowPlayAgain);
		return ("home/hand-form");
	}
	@PostMapping("/bet")
	public String returnsTheBet(int amount, Model model) {
		
		// Player makes bet, wallet balance changes
		currentBet = amount;
		newPlayer.makeBet(amount);
		
		// Deal the cards
		newDealer.newRound();
		canShowHitAndStand = true;
		canShowPlayAgain = false;
		newPlayer.playerClearHand();
		newDealer.dealerClearHand();
		newDealer.givePlayerCard(newPlayer);
		newDealer.giveDealerCard();
		newDealer.givePlayerCard(newPlayer);
		newDealer.giveDealerCard();
		
		// Display the cards
		return "redirect:/hand";
			
	}
		
	@GetMapping("")
	public String showTheTable() {
		return ("home/default");
	}
	
	@PostMapping("/hit")
	public String playerHit(Model model) {
		// Give player another card if not a Bust
		if (!newPlayer.isBust()) {
		newDealer.givePlayerCard(newPlayer);
		// If they are a bust, end the round and payout
		} else {
			canShowHitAndStand = false;
			canShowPlayAgain = true;
			newDealer.endRound();
		}
		
		currentBet = 0;
		return "redirect:/hand";
	}
	
	@PostMapping("/stand")
	public String playerStand() {
		
		// Determine winner and payout appropriately
		newDealer.endRound();
		if (newDealer.isBust()) {
			newPlayer.payout(currentBet * 2);
		} else if (newPlayer.hasBlackjack() && !newDealer.hasBlackjack()) {
			newPlayer.payout(currentBet + currentBet / 2);
		} else if (newPlayer.getWinner() == newDealer.getWinner()) {
			newPlayer.payout(currentBet);
		} else if (newPlayer.getWinner() > newDealer.getWinner()) {
			newPlayer.payout(currentBet * 2);
		}
		
		canShowHitAndStand = false;
		canShowPlayAgain = true;
		currentBet = 0;
		return "redirect:/hand";
	}
	
	@PostMapping("/again")
	public String playAgain() {
		return "home/new-game";
	}
}
