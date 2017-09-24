package com.springwiz.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salesrecord")
public class SalesRecordVO {
	private String product;
	private String target;
	private List<WeeklyDataVO> weeklyData;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<WeeklyDataVO> getWeeklyData() {
		return weeklyData;
	}
	public void setWeeklyData(List<WeeklyDataVO> weeklyData) {
		this.weeklyData = weeklyData;
	}
	
	
}
