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
		newPlayer = new Player();
		newDealer = new Dealer();
		this.currentBet = 0;
		
	}
	
	@PostMapping("") 
	public ModelAndView startTheGame() {
		ModelAndView mv = new ModelAndView("home/new-game");
		mv.addObject("remainingCash", newPlayer.getWalletBalance());
		return mv;
	}
	
	@GetMapping("/hand")
	public String refreshTheTable(Model model) {
		model.addAttribute("currentBet", newPlayer.getBet());
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
		newPlayer.makeBet(currentBet);
				
		if (currentBet > newPlayer.getWalletBalance());{
			canShowHitAndStand = false;
			canShowPlayAgain = false;
			currentBet = 0;
		}
		
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
		
		return "redirect:/hand";
			
	}
		
	@GetMapping("")
	public String showTheTable() {
		return ("home/default");
	}
	
	@PostMapping("/hit")
	public String playerHit(Model model) {
		// Give player another card if not a Bust
		newDealer.givePlayerCard(newPlayer);
		// If they are a bust, end the round and payout
		if (newPlayer.isBust()) {
			canShowHitAndStand = false;
			canShowPlayAgain = true;
			currentBet = 0;
			newDealer.endRound();
		} 		
		return "redirect:/hand";
	}
	
	@PostMapping("/stand")
	public String playerStand() {
		
		// Determine winner and payout appropriately
		newDealer.endRound();
		if (newDealer.isBust()) {
			newPlayer.payout(newPlayer.getBet() * 2);
		} else if (newPlayer.hasBlackjack() && !newDealer.hasBlackjack()) {
			newPlayer.payout(newPlayer.getBet() + newPlayer.getBet() / 2);
		} else if (newPlayer.getWinner() == newDealer.getWinner()) {
			newPlayer.payout(newPlayer.getBet());
		} else if (newPlayer.getWinner() > newDealer.getWinner()) {
			newPlayer.payout(newPlayer.getBet() * 2);
		}
		
		canShowHitAndStand = false;
		canShowPlayAgain = true;
		currentBet = 0;
		return "redirect:/hand";
	}
	
	@PostMapping("/again")
	public String playAgain(Model model) {
		model.addAttribute("remainingCash", newPlayer.getWalletBalance());
		return "home/new-game";
	}
}
