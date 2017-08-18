package com.libertymutual.pokergame.commands;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poker")

public class TableController {

	@PostMapping("")
	public String returnsTheBet() {
		return ("home/default");
	}
	
	@GetMapping("")
	public String showTheTable() {
		return ("home/default");
	}
}
