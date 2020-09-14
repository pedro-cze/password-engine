package cz.pedro.passman.validation;

import cz.pedro.passman.exception.UnreachablePrerequisitesException;

import java.util.List;

public class ValidationUtil {

    public static void validateConstraints(int count, int maxOccurrence, List<Character> characters) throws UnreachablePrerequisitesException {
        if (checkParamsInvalid(count, maxOccurrence, characters)) {
            throw new UnreachablePrerequisitesException("Cannot construct password. Please adjust input.");
        }
    }

    private static boolean checkParamsInvalid(int count, int maxOccurrence, List<Character> characters) {
        if (count <= 0 || count > 255) {
            return true;
        }
        if (characters.isEmpty()) {
            return true;
        }
        if (maxOccurrence <= 0) {
            return true;
        }
        return constraintsUnreachable(count, maxOccurrence, characters);
    }

    private static boolean constraintsUnreachable(final int count, final int maxOccurrence, List<Character> characters) {
        return Math.floorDiv(count ,characters.size()) + 1 > maxOccurrence;
    }

}
