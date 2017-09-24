	package com.spring.practice.mainapp;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Pattern doubleAmountPattern  = Pattern.compile("^(0|[1-9]\\d{0,14})(\\,\\d{1,2})?$");
		boolean b = doubleAmountPattern.matcher("10,00").matches();
		System.out.println(b);
		
		/*
		Double d = Double.parseDouble("12,12");
		System.out.println(d);*/
		
		
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			double d = format.parse("12,12").doubleValue();
			System.out.println(d);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String aa = "12,127";
		aa = aa.replace(",",	".");
		DecimalFormat f = new DecimalFormat("##.00");
	    System.out.println(f.format(Double.parseDouble(aa)));
		
	    BigDecimal a = new BigDecimal("0.995");
	    BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	    
	    System.out.println(roundOff.toPlainString());
	    
	    System.out.println(Math.round(2.895));
		/*
		System.out.println(aa);*/
	}

}
