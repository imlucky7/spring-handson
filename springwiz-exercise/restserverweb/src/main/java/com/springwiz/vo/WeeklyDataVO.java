package com.springwiz.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "weeklydata")
public class WeeklyDataVO {
	private String sales;
	private String achievement;
	
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	
	
}
