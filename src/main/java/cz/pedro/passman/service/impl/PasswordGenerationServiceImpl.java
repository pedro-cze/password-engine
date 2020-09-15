package cz.pedro.passman.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cz.pedro.passman.engine.PasswordEngine;
import cz.pedro.passman.exception.UnreachablePrerequisitesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.google.common.primitives.Chars;

import cz.pedro.passman.service.PasswordGenerationService;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordGenerationServiceImpl implements PasswordGenerationService {

    private final PasswordEngine passwordEngine;

    @Override
    public String generatePassword(int length, Map<Set<Character>, Boolean> charsetMap) {
        String result = "";
        try {
            result = passwordEngine.generatePassword(length, 3, shuffle(getCharacterList(charsetMap)));
        }  catch (UnreachablePrerequisitesException | NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    private List<Character> getCharacterList(Map<Set<Character>, Boolean> charsetMap) {
        if (charsetMap == null) {
            log.error("Null charset.");
            return List.of();
        }
        List<Character> result = new ArrayList<>();
        for (Set<Character> charset : charsetMap.keySet()) {
            if (charsetMap.get(charset)) {
                result.addAll(charset);
            }
        }
        return result;
    }

    private List<Character> shuffle(List<Character> input) {
        Random random = new Random();
        char[] inputArr = Chars.toArray(input);
        for (int i = 0; i < inputArr.length; i++) {

            var nextInt = random.nextInt(inputArr.length - 1);

            var temp = inputArr[i];
            inputArr[i] = inputArr[nextInt];
            inputArr[nextInt] = temp;

        }

        return Chars.asList(inputArr);
    }
}
