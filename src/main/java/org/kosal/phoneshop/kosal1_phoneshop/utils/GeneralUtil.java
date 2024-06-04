package org.kosal.phoneshop.kosal1_phoneshop.utils;

import java.util.List;

public class GeneralUtil {
	public static List<Integer>toIntegerList(List<String>name){
		
		return name.stream()
			.map(s->s.length())
			.toList();
		
	}
	public static List<Integer>getEvenNumber(List<Integer>num){
		return num.stream()
			.filter(s->s%2==0)
			.toList();
	}

}
