package com.voucher.util;

import java.util.HashMap;
import java.util.Map;

public class OfferTypes {
	
	public static Map<String ,Integer> map =new HashMap<>();
	
	static
    { 
        map = new HashMap<>(); 
        map.put("OFFER_2", 2);
		map.put("OFFER_5", 5);
		map.put("OFFER_10", 10);
		map.put("OFFER_20", 20);
		map.put("OFFER_50", 50);
		
    } 
	
	
	 public static Integer getOfferTypes(String key) {
	      return map.get(key);
	  }

}
