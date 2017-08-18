package com.libertymutual.pokergame.commands;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.pokergame.models.Deck;
import com.libertymutual.pokergame.models.Hand;
import com.libertymutual.pokergame.models.Player;

@Controller
@RequestMapping("/")

public class TableController {

	Player newPlayer;
	Deck newDeck;
	
	public TableController () {
		newPlayer = new Player(100);
		newDeck = new Deck();
	}
	
	@PostMapping("") 
	public ModelAndView startTheGame() {
		
		//Hand newHand = new Hand();
	
		
		ModelAndView mv = new ModelAndView("home/new-game");
		return mv;
//		mv.addObject();
	}
	
	@PostMapping("/bet")
	public String returnsTheBet(int amount, Model model) {
		model.addAttribute("currentBet", amount);
		newPlayer.makeBet(amount);
		model.addAttribute("walletBalance", newPlayer.getWalletBalance());
		return ("home/currentBet-form");
	}
	
//	@PostMapping("/deal")
//	public void dealTheCards(deck)
//	}
	
//	public void keepHittingHand(Deck deck) {
//		int[] values = hand.getValues();
//		while (values[0] < 17 || values[1] < 17){
//			Card theNewCard = deck.getCard();
//			hand.addCard(theNewCard);
//			values = hand.getValues();
//	}
	
	@GetMapping("")
	public String showTheTable() {
		return ("home/default");
	}
	
}
