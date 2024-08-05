package org.kosal.phoneshop.kosal1_phoneshop.security.JWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class Example {

	public static void main(String[] args) {

		log.info(getYou() + getAre() + getMy()+getLove().toUpperCase());
		
		
		
	}

	public static String getAre() {

		return "are ";
	}

	public static String getLove() {

		return "love.";
	}

	public static String getYou() {

		return "You ";
	}
	public static String getMy() {

		return "my ";
	}

	

}
