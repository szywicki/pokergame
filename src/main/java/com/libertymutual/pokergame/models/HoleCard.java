package com.libertymutual.pokergame.models;

public class HoleCard implements Card {

	@Override
	public String getSuit() {
		return "";
	}

	@Override
	public String getVisualRepresentation() {
		return "HOLE";
	}

	@Override
	public int[] getValues() {
			return new int[] {0, 0};
	}

}
