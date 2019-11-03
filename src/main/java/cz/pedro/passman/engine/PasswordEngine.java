package cz.pedro.passman.engine;

import java.util.List;

import cz.pedro.passman.exception.UnreachablePrerequisitesException;

public interface PasswordEngine {

	public String generatePassword(int length, List<Character> characters);
	
	public List<Character> pickRandomChars(int count, int maxOccurrence, List<Character> characters) throws UnreachablePrerequisitesException;

}
