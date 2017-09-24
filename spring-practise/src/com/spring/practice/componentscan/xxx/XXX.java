package com.spring.practice.componentscan.xxx;

import org.springframework.stereotype.Component;

@Component
public class XXX {
	public XXX() {
		System.out.println("XXX bean created..." + this);
	}
}
