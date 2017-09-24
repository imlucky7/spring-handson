package com.spring.practice.componentscan.zzz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.practice.componentscan.xxx.XXX;
import com.spring.practice.componentscan.yyy.YYY;

@Component
public class ZZZ {

	private XXX xxx;
	private YYY yyy;
	
	public ZZZ() {
		System.out.println("ZZZ bean created.."+this);
	}
	
	@Autowired
	public void setXxx(XXX xxx) {
		this.xxx = xxx;
		System.out.println("setting XXX.xxx - " + xxx);
	}
	
	@Autowired
	public void setYyy(YYY yyy) {
		this.yyy = yyy;
		System.out.println("setting YYY.yyy - " + yyy);
	}
	
	public String getMessage() {
		return "Good morning";
	}
}
