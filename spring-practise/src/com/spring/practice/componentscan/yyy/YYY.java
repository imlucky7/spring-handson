package com.spring.practice.componentscan.yyy;

import org.springframework.stereotype.Component;

@Component
public class YYY {

	public YYY() {
		System.out.println("YYY bean created..." + this);
	}

}
