package com.doselect.carddecks.rest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.doselect.carddecks.dao.IPersist;
import com.doselect.carddecks.shuffling.IShuffle;

@Component
@Path("storeapi/rest")
public class CardDecksStoreRest {
	
	@Inject
	IPersist persist;
	
	@Autowired
	Map<String, IShuffle> shuffleTypeMap;
	
	@Value("${carddeck.shuffle.type}")
	private String shuffleType;
	
	public IPersist getPersist() {
		return persist;
	}

	public void setPersist(IPersist persist) {
		this.persist = persist;
	}
	
	public Map<String, IShuffle> getShuffleTypeMap() {
		return shuffleTypeMap;
	}

	public void setShuffleTypeMap(Map<String, IShuffle> shuffleTypeMap) {
		this.shuffleTypeMap = shuffleTypeMap;
	}

	@GET
	@Path("/allCardDecks")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getAllCardDecks() {
		return persist.getAllDeck();
	}
	
	@GET
	@Path("/allCardDeckByName/{cardDeckName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, List<String>> getCardDeckByName(@PathParam("cardDeckName") String cardDeckName) {
		return persist.getDeckByName(cardDeckName);
	}
	
	@POST
	@Path("/createCardDeck/{cardDeckName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String createCardDeck(@PathParam("cardDeckName") String cardDeckName) {
		return persist.createDeck(cardDeckName);
	}
	
	@DELETE
	@Path("/deleteCardDeck/{cardDeckName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCardDeckByName(@PathParam("cardDeckName") String cardDeckName) {
		return persist.deleteDeckByName(cardDeckName);
	}
	
	@PUT
	@Path("/shuffle/{cardDeckName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String shuffleCardDec(@PathParam("cardDeckName") String cardDeckName) throws Exception{
		IShuffle iShiffle = shuffleTypeMap.get(shuffleType);
		if(iShiffle == null){
			throw new Exception("Check the shuffle type in properties file..");
		} else {
			String status = iShiffle.doShuffle(cardDeckName);
			return status;
		}
	}
}
