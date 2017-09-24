package com.doselect.carddecks.manage;

public class Card {
	
	private CardSuits suit;
	private CardRank rank;
	
	public Card() {
	}
	
	public Card(CardSuits suit, CardRank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public CardSuits getSuit() {
		return suit;
	}

	public void setSuit(CardSuits suit) {
		this.suit = suit;
	}

	public CardRank getRank() {
		return rank;
	}

	public void setRank(CardRank rank) {
		this.rank = rank;
	}
	
	public String getCardValue() {
		return this.rank.getValue() + "-" + this.suit.getValue();
	}
}
