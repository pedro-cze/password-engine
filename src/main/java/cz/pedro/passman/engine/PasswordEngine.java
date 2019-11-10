package cz.pedro.passman.engine;

import java.util.List;


public interface PasswordEngine {

	public String generatePassword(int length, int maxOccurrence, List<Character> characters);

}
