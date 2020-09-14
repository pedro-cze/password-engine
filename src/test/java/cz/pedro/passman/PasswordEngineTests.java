package cz.pedro.passman;

import cz.pedro.passman.engine.PasswordEngine;
import cz.pedro.passman.exception.UnreachablePrerequisitesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEngineTests {

    private PasswordEngine passwordEngine;

    @BeforeEach
    public void init() {
        this.passwordEngine = new PasswordEngine();
    }

    @Test
    @DisplayName("Testing unreachable constraints: length=2, maxOccurrence=1, chars={a}")
    public void generatePassword_unreachableConstraints_1() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(2, 1, List.of('a'))
        );
    }

    @Test
    @DisplayName("Testing unreachable constraints. length=3, maxOccurrence=1, chars={a, b}")
    public void generatePassword_unreachableConstraints_2() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(3, 1, List.of('a', 'b'))
        );
    }

    @Test
    @DisplayName("Testing successfull generation. length=3, maxOccurrence=1, chars={1, 2, 3}")
    public void generatePassword_expectingSuccess() throws UnreachablePrerequisitesException, NoSuchAlgorithmException {
        var result = passwordEngine.generatePassword(5, 2, List.of('1', '2', '3'));
        assertFalse(result.isEmpty());
        assertEquals(5, result.length());
    }

}
