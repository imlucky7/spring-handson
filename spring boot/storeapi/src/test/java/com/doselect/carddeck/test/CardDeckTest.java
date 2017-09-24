package com.doselect.carddeck.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.doselect.carddecks.dao.IPersist;
import com.doselect.carddecks.dao.InMemoryPersist;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardDeckTest {
	private static IPersist persist;
	
	@BeforeClass
	public static void setup() {
		persist = new InMemoryPersist();
	}
	
	String cardDeckName = "Test";
	
	@Test
	public void test1_CreateCardDeck() {
		String actual = persist.createDeck(cardDeckName);
		Assert.assertEquals("Card deck is successfully created", actual);

	}
	
	@Test
	public void test2_GetAllDeck() {
		Set<String> actual = persist.getAllDeck();
		Assert.assertEquals(1, actual.size());

	}
	
	@Test
	public void test3_GetDeckByName() {
		Map<String, List<String>> deckMapa = persist.getDeckByName(cardDeckName);
		List<String> actual = deckMapa.get(cardDeckName);
		Assert.assertEquals(52, actual.size());
	}
	
	@Test
	public void test4_CreateDuplicateCardDeck() {
		String actual = persist.createDeck(cardDeckName);
		Assert.assertEquals("Card Deck name is already exist", actual);

	}
	
	@Test
	public void test5_DeleteCardDeck() {
		String actual = persist.deleteDeckByName(cardDeckName);
		Assert.assertEquals("Card deck is successfully deleted", actual);

	}
}
