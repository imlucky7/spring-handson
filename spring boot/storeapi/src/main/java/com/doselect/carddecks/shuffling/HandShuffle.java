package com.doselect.carddecks.shuffling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

import com.doselect.carddecks.dao.IPersist;
import com.doselect.carddecks.manage.Card;
import com.doselect.carddecks.manage.CardDeck;

@Named("handshuffle")
public class HandShuffle implements IShuffle {

	@Inject
	IPersist persist;
	
	public IPersist getPersist() {
		return persist;
	}

	public void setPersist(IPersist persist) {
		this.persist = persist;
	}

	public String doShuffle(String deckName) {
		String status = "";
		try {
			CardDeck cardDeck = persist.getCardDeck(deckName);
			if (cardDeck == null) {
				status = "Card Deck name not found";
			} else {
				List<Card> newCardList = shuffle(cardDeck);
				CardDeck newCardDeck = new CardDeck();
				newCardDeck.addAllCardsToDeck(newCardList);
				if(persist.udpateCardDeck(deckName, newCardDeck)) {
					status = "Card Deck shuffle (handshuffle) is successful";
				} else {
					status = "Card Deck shuffle is not successful";
				}
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			status = e.getMessage();
		}

		return status;
	}
	
	private List<Card> shuffle(CardDeck cardDeck) {
		List<Card> cardList = cardDeck.getAllCards();
		List<Card> cardListLeft = new ArrayList<Card>();
		List<Card> cardListRight = new ArrayList<Card>();
		
		int loopcnt = getRandNum();
		int deckMid = cardList.size()/2;
		for(int i=0; i<loopcnt; i++) {
			cardListLeft.clear();
			cardListRight.clear();
			for(int j=0; j<deckMid; j++) {
				cardListLeft.add(cardList.get(j));
				cardListRight.add(cardList.get(j+deckMid));
			}
			cardList.clear();
			for(int j=0; j<deckMid; j++) {
				cardList.add(cardListLeft.get(j));
				cardList.add(cardListRight.get(j));
			}
		}
		return cardList;
	}
	
	private int getRandNum() {
		int max = 8, min = 4;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
