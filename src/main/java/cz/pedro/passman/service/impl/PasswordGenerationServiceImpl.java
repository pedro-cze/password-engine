package cz.pedro.passman.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cz.pedro.passman.engine.PasswordEngine;
import org.springframework.stereotype.Service;

import com.google.common.primitives.Chars;

import cz.pedro.passman.service.PasswordGenerationService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PasswordGenerationServiceImpl implements PasswordGenerationService {

    private final PasswordEngine passwordEngine;

    @Override
    public String generatePassword(int length, Map<Set<Character>, Boolean> charsetMap) {
        return passwordEngine.generatePassword(length, 3, shuffle(getCharacterList(charsetMap)));
    }

    private List<Character> getCharacterList(Map<Set<Character>, Boolean> charsetMap) {
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
