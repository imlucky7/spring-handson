package com.doselect.carddecks.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.doselect.carddecks.manage.CardDeck;

public interface IPersist {
	public String createDeck(String deckName);
	public Set<String> getAllDeck();
	public Map<String, List<String>> getDeckByName(String deckName);
	public String deleteDeckByName(String deckName);
	public CardDeck getCardDeck(String deckName) throws CloneNotSupportedException;
	public boolean udpateCardDeck(String deckName, CardDeck cardDeck);
}
