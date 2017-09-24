package com.doselect.carddecks.manage;

import java.util.ArrayList;
import java.util.List;

public class CardDeck implements Cloneable {
	List<Card> cardDeck = new ArrayList<Card>();
	
	public void addCardToDeck(Card card) {
		cardDeck.add(card);
	}
	
	public void addAllCardsToDeck(List<Card> cards) {
		cardDeck.addAll(cards);
	}
	
	public List<String> getAllCardValue() {
		List<String> cardList = new ArrayList<String>();
		if(cardDeck != null) {
			for(Card card : cardDeck) {
				cardList.add(card.getCardValue());
			}
		}
		return cardList;
	}
	
	public List<Card> getAllCards() {
		return cardDeck;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
