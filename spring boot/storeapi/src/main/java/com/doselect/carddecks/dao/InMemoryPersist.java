package com.doselect.carddecks.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;

import com.doselect.carddecks.manage.CardDeck;
import com.doselect.carddecks.util.InMemoryDBUtility;
import com.doselect.carddecks.util.StringUtil;

@Named
public class InMemoryPersist implements IPersist {
	
	Map<String, CardDeck> inMemoryDB = new HashMap<String, CardDeck>();
	
	public String createDeck(String deckName) {
		String status = "";
		if(StringUtil.isEmpty(deckName)){
			status = "Card Deck name is not valid";
		} else if(inMemoryDB.containsKey(deckName)) {
			status = "Card Deck name is already exist";
		} else {
			CardDeck deck = InMemoryDBUtility.creteCardDeck(deckName);
			inMemoryDB.put(deckName, deck);
			status = "Card deck is successfully created";
		}
		
		return status;
	}

	public Set<String> getAllDeck() {
		return inMemoryDB.keySet();

	}

	public Map<String, List<String>> getDeckByName(String deckName) {
		Map<String, List<String>> deckMap = new HashMap<String, List<String>>();
		
		if(StringUtil.isEmpty(deckName)){
			List<String> status = Arrays.asList("Card Deck name is not valid");
			deckMap.put("Error", status);
		} else if(!inMemoryDB.containsKey(deckName)) {
			List<String> status = Arrays.asList("Card Deck name doesn't exist");
			deckMap.put("Error", status);
		} else {
			CardDeck deck = inMemoryDB.get(deckName);
			deckMap.put(deckName, deck.getAllCardValue());
		}
		return deckMap;
	}
	
	public String deleteDeckByName(String deckName) {
		String status = "";
		if(StringUtil.isEmpty(deckName)){
			status = "Card Deck name is not valid";
		} else if(!inMemoryDB.containsKey(deckName)) {
			status = "Card Deck name doesn't exist";
		} else {
			inMemoryDB.remove(deckName);
			status = "Card deck is successfully deleted";
		}
		
		return status;
	}
	
	public CardDeck getCardDeck(String deckName) throws CloneNotSupportedException {
		if(StringUtil.isEmpty(deckName)){
			return null;
		} else if(!inMemoryDB.containsKey(deckName)) {
			return null;
		} else {
			CardDeck deck = inMemoryDB.get(deckName);
			CardDeck deckClone = (CardDeck) deck.clone();
			return deckClone;
		}
	}
	
	public boolean udpateCardDeck(String deckName, CardDeck cardDeck) {
		boolean status = Boolean.FALSE;
		if(StringUtil.isEmpty(deckName)){
			status = Boolean.FALSE;
		} else if(!inMemoryDB.containsKey(deckName)) {
			status = Boolean.FALSE;;
		} else {
			inMemoryDB.remove(deckName);
			inMemoryDB.put(deckName, cardDeck);
			status = Boolean.TRUE;
		}
		return status;
	}

}
