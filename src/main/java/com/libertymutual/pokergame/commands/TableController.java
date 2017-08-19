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

	private Player newPlayer;
	private Dealer newDealer;
	
	public TableController () {
		newPlayer = new Player(100);
		newDealer = new Dealer();
		
	}
	
	@PostMapping("") 
	public ModelAndView startTheGame() {				
		ModelAndView mv = new ModelAndView("home/new-game");
		return mv;
	}
	
	@PostMapping("/bet")
	public String returnsTheBet(int amount, Model model) {
		
		// Player makes bet, wallet balance changes
		model.addAttribute("currentBet", amount);
		newPlayer.makeBet(amount);
		model.addAttribute("walletBalance", newPlayer.getWalletBalance());
		
		// Deal the cards
		newDealer.newRound();
		newDealer.giveDealerCard();
		newDealer.givePlayerCard(newPlayer);
		newDealer.giveDealerCard();
		newDealer.givePlayerCard(newPlayer);
		newDealer.giveDealerCard();
		
		// Display the cards
		model.addAttribute("dealerHand", newDealer.getCards());
		model.addAttribute("playerHand", newPlayer.getCards());
	
		return ("home/hand-form");
			
	}
		
	@GetMapping("")
	public String showTheTable() {
		return ("home/default");
	}
	
	@PostMapping("/hit")
	public String playerHit(int amount, Model model) {
		model.addAttribute("currentBet", amount);
		model.addAttribute("walletBalance", newPlayer.getWalletBalance());
		newDealer.givePlayerCard(newPlayer);
		return ("home/hand-form");
	}
	
//	@PostMapping("")
//	public String playerStand() {
//		newDealer.endRound();
//		if (newDealer.isBust()) {
//			newPlayer.p
//		}
//	}
	
}
