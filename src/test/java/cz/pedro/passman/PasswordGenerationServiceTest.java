package cz.pedro.passman;

import cz.pedro.passman.engine.PasswordEngine;
import cz.pedro.passman.exception.UnreachablePrerequisitesException;
import cz.pedro.passman.service.PasswordGenerationService;
import cz.pedro.passman.service.impl.PasswordGenerationServiceImpl;
import cz.pedro.passman.utils.CharsetFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class PasswordGenerationServiceTest {

    private PasswordGenerationService passwordGenerationService;
    private PasswordEngine passwordEngine;

    @BeforeEach
    public void init() {
        this.passwordEngine = mock(PasswordEngine.class);
        this.passwordGenerationService = spy(new PasswordGenerationServiceImpl(passwordEngine));
    }

    @Test
    public void testPasswordEngineGetsCalled() throws NoSuchAlgorithmException, UnreachablePrerequisitesException {
        when(passwordGenerationService.generatePassword(anyInt(), eq(Map.of(CharsetFactory.ALFA, true)))).thenCallRealMethod();
        passwordGenerationService.generatePassword(anyInt(), eq(Map.of(CharsetFactory.ALFA, true)));
        verify(passwordEngine, times(1)).generatePassword(anyInt(), anyInt(), anyList());
    }

}
