package cz.pedro.passman.engine;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cz.pedro.passman.exception.UnreachablePrerequisitesException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PasswordEngineImpl implements PasswordEngine {

	@Override
	public String generatePassword(int length, List<Character> characters)  {
		final StringBuilder strBuilder = new StringBuilder();
		try {
			while(strBuilder.length() < length) {
				final var randomIndex = SecureRandom.getInstance("SHA1PRNG").nextInt(length);
				strBuilder.append(characters.get(randomIndex));
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		final var result = strBuilder.toString();
		log.debug("PASSWORD: {}", result.toString());
		return result;
	}
	
	@Override
	public List<Character> pickRandomChars(int count, int maxOccurrence, List<Character> characters) throws UnreachablePrerequisitesException {
		
		if (checkCountAgainstMaxOccurence(count, maxOccurrence, characters)) {
			throw new UnreachablePrerequisitesException();
		}
		
		List<Character> result = new ArrayList<>();
		try {
			final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			while (result.size() < count) {
				int nextIndex = secureRandom.nextInt(characters.size());
				Character nextChar = characters.get(nextIndex);
				if (getCharCount(nextChar, result) >= maxOccurrence) {
					continue;
				}
				result.add(nextChar);
			}
		} catch (Exception e) {
			log.error(e.getMessage());	
		}
		return result;
	}
	
	private boolean checkCountAgainstMaxOccurence(int count, int maxOccurrence, List<Character> characters) {
		return count / characters.size() > maxOccurrence;
	}
	
	private int getCharCount(Character pattern, List<Character> password) {
		int result = 0;
		for (Character ch : password) {
			if (ch.equals(pattern)) {
				result++;
			}
		}
		return result;
		
	}
	
}
