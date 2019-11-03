package cz.pedro.passman.utils;

import java.util.Set;

import com.google.common.collect.Sets;

public class CharsetFactory {
	
	public static final Set<Character> LOWER = Set.of(
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z'
	); 
	
	public static final Set<Character> UPPER = Set.of(
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z'
	);
	
	public static final Set<Character> NUMBERS = Set.of(
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	); 
	
	public static final Set<Character> SPECIAL = Set.of(
			'!', '@', '#', '$', '%', '^', '&', '*',
			'(', ')', '_', '-', '+', '=', '[', ']',
			';', ':', '\\', '|', '<', '>', '?', '{', '}'
	); 
	
	public static final Set<Character> ALFA = Sets.union(LOWER, UPPER);
	
	public static final Set<Character> ALFA_NUMERIC = Sets.union(ALFA, NUMBERS);
	
	public static final Set<Character> SPECIAL_ALFA_NUMERIC = Sets.union(SPECIAL, ALFA_NUMERIC);
	
	public static final Set<Character> SPECIAL_ALFA = Sets.union(SPECIAL, ALFA);
	
	public static final Set<Character> SPECIAL_NUMERIC = Sets.union(SPECIAL, ALFA);
}
