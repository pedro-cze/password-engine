package cz.pedro.passman.service;

import java.util.Map;
import java.util.Set;

public interface PasswordGenerationService {
	
	String generatePassword(int length, Map<Set<Character>, Boolean> charsetMap);
	
}
