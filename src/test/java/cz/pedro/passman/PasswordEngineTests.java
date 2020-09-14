package cz.pedro.passman;

import cz.pedro.passman.engine.PasswordEngine;
import cz.pedro.passman.exception.UnreachablePrerequisitesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordEngineTests {

    private PasswordEngine passwordEngine;

    @BeforeEach
    public void init() {
        this.passwordEngine = new PasswordEngine();
    }

    @Test
    @DisplayName("Testing unreachable constraints: length=2, maxOccurrence=1, chars={a}")
    public void generatePassword_unreachableConstraints() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(3, 1, List.of('a'))
        );
    }

    @Test
    @DisplayName("Testing successfull generation. Constraints: length=3, maxOccurrence=1, chars={a, b}")
    public void generatePassword_expectingSuccess() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(3, 1, List.of('a', 'b'))
        );
    }

}
