package com.doselect.carddecks.util;

import java.util.List;

import com.doselect.carddecks.manage.Card;
import com.doselect.carddecks.manage.CardDeck;
import com.doselect.carddecks.manage.CardRank;
import com.doselect.carddecks.manage.CardSuits;

public class InMemoryDBUtility {
	
	public static CardDeck creteCardDeck(String deckName) {
		CardDeck deck = new CardDeck();
		
		for(CardRank rank : CardRank.values()) {
			for(CardSuits suit : CardSuits.values()) {
				deck.addCardToDeck(new Card(suit, rank));
			}
		}
		
		return deck;
	}
}
