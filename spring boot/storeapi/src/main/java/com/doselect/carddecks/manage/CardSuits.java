package com.doselect.carddecks.manage;

public enum CardSuits {
	CLUB("club"), DIAMOND("diamond"), HEART("heart"), SPADE("spade");
	
	private String value;
	
	CardSuits(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
