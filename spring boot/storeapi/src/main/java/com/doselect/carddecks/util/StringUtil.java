package com.doselect.carddecks.util;

public class StringUtil {
	
	public static boolean isEmpty(String str){
		if(str != null && !str.isEmpty())
			return false;
		else 
			return true;
	}
}
