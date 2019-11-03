package cz.pedro.passman.utils;

import java.util.List;

import cz.pedro.passman.exception.UnreachablePrerequisitesException;

public interface PasswordCharset {

	public List<Character> pickRandomChars(int count) throws UnreachablePrerequisitesException;
	
}
