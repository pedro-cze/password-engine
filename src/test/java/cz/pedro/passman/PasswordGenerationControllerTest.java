package cz.pedro.passman;

import cz.pedro.passman.domain.PasswordParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordGenerationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @Test
    public void testSuccessfulCall_201_expected() {
        final PasswordParams passwordParams = getPasswordParams(6, true, true, true, true);
        final RequestEntity<PasswordParams> requestEntity = RequestEntity
                .post(getUri("/generate"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(passwordParams);
        final ResponseEntity result = restTemplate.exchange(requestEntity, String.class);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertTrue(result.hasBody());
        assertEquals(6, result.getBody().toString().length());
    }

    @Test
    public void testInvalidLength_400_expected() {
        final PasswordParams passwordParams = getPasswordParams(-1, true, true, true, true);
        final RequestEntity<PasswordParams> requestEntity = RequestEntity
                .post(getUri("/generate"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(passwordParams);
        final ResponseEntity result = restTemplate.exchange(requestEntity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    private PasswordParams getPasswordParams(int length, boolean lower, boolean upper, boolean number, boolean special) {
        return new PasswordParams(length, lower, upper, number, special);
    }

    private URI getUri(final String path) {
        return URI.create("http://localhost:" + port + path);
    }

}
