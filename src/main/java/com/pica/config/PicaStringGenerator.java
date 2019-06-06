package com.pica.config;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class PicaStringGenerator {

	public static String generatePassword() {

		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z')
				.filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS).build();

		return generator.generate(8);
	}
	
	
	

	public static void main(String[] args) {
		String str = PicaStringGenerator.generatePassword();
		System.out.println(str);
	}




	public static String generateBase29Code(String string) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z')
				.filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS).build();

		string+= generator.generate(8);
		return string;
	}

}
