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
    @DisplayName("Testing successful generation. length=3, maxOccurrence=1, chars={1, 2, 3}")
    public void generatePassword_expectingSuccess() throws UnreachablePrerequisitesException, NoSuchAlgorithmException {
        var result = passwordEngine.generatePassword(5, 2, List.of('1', '2', '3'));
        assertFalse(result.isEmpty());
        assertEquals(5, result.length());
    }

    @Test
    @DisplayName("Testing unreachable constraints. Length < 0")
    public void generatePassword_lengthLessThenZero() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(-1, 1, List.of('a', 'b'))
        );
    }

    @Test
    @DisplayName("Testing unreachable constraints. Length = 0")
    public void generatePassword_lengthEqualsZero() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(0, 1, List.of('a', 'b'))
        );
    }

    @Test
    @DisplayName("Testing unreachable constraints. MaxOccurrence < 0")
    public void generatePassword_maxOccurrenceIsLessThenZero() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(1, -1, List.of('a', 'b'))
        );
    }

    @Test
    @DisplayName("Testing unreachable constraints. MaxOccurrence = 0")
    public void generatePassword_maxOccurrenceEqualsZero() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(1, 0, List.of('a', 'b'))
        );
    }

    @Test
    @DisplayName("Testing unreachable constraints. Empty characters.")
    public void generatePassword_emptyCharacters() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(1, 1, List.of())
        );
    }

    @Test
    @DisplayName("Testing unreachable constraints. Length > 255")
    public void generatePassword_lengthGreaterThen255() {
        assertThrows(UnreachablePrerequisitesException.class, () ->
                passwordEngine.generatePassword(256, 255, List.of('a', 'b'))
        );
    }


}
