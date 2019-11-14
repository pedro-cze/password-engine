package cz.pedro.passman.engine;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import cz.pedro.passman.exception.UnreachablePrerequisitesException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PasswordEngineImpl implements PasswordEngine {

	@Override
	public String generatePassword(int length, int maxOccurrence, List<Character> characters)  {
		StringBuilder strBuilder = new StringBuilder();
		try {
			strBuilder = pickRandomChars(length, maxOccurrence, characters);
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		final var result = strBuilder.toString();
		log.debug("PASSWORD: {}", result);
		return result;
	}
	
	private StringBuilder pickRandomChars(int count, int maxOccurrence, List<Character> characters) throws UnreachablePrerequisitesException, NoSuchAlgorithmException {
		
		if (checkCountAgainstMaxOccurence(count, maxOccurrence, characters)) {
			throw new UnreachablePrerequisitesException();
		}
		
		final StringBuilder strBuilder = new StringBuilder();
		final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		while (strBuilder.length() < count) {
			int nextIndex = secureRandom.nextInt(characters.size());
			Character nextChar = characters.get(nextIndex);
			if (getCharCount(nextChar, strBuilder.toString()) >= maxOccurrence) {
				continue;
			}
			strBuilder.append(nextChar);
		}
		return strBuilder;
	}

	private boolean checkCountAgainstMaxOccurence(int count, int maxOccurrence, List<Character> characters) {
		return characters.isEmpty() || maxOccurrence <= 0 || count / characters.size() > maxOccurrence;
	}
	
	private int getCharCount(Character pattern, String password) {
		int result = 0;
		for (Character ch : password.toCharArray()) {
			if (ch.equals(pattern)) {
				result++;
			}
		}
		return result;
		
	}
	
}
