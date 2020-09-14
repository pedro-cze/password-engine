package cz.pedro.passman.engine;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import cz.pedro.passman.validation.ValidationUtil;
import org.springframework.stereotype.Component;

import cz.pedro.passman.exception.UnreachablePrerequisitesException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PasswordEngine {

	private static final String RANDOM_ALGORITHM = "SHA1PRNG";

	public String generatePassword(int length, int maxOccurrence, List<Character> characters) throws UnreachablePrerequisitesException, NoSuchAlgorithmException  {
		ValidationUtil.validateConstraints(length, maxOccurrence, characters);
		final var result = pickRandomChars(length, maxOccurrence, characters);
		log.debug("PASSWORD: {}", result);
		return result.toString();
	}
	
	private StringBuilder pickRandomChars(int count, int maxOccurrence, List<Character> characters) throws NoSuchAlgorithmException {
		final StringBuilder strBuilder = new StringBuilder();
		final SecureRandom secureRandom = SecureRandom.getInstance(RANDOM_ALGORITHM);
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
