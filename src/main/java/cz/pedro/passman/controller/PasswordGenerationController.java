package cz.pedro.passman.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.pedro.passman.domain.PasswordParams;
import cz.pedro.passman.service.PasswordGenerationService;
import cz.pedro.passman.utils.CharsetFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/generate")
@RequiredArgsConstructor
public class PasswordGenerationController {

	private final PasswordGenerationService passwordGenerationService;
	
	@PostMapping
	public ResponseEntity<String> generatePassword(@RequestBody PasswordParams passwordParams) {
		log.info("Generating password for length: {}", passwordParams.getLength());
		
		final Map<Set<Character>, Boolean> charsetMap = new HashMap<>();
		charsetMap.put(CharsetFactory.LOWER, passwordParams.isLower());
		charsetMap.put(CharsetFactory.UPPER, passwordParams.isUpper());
		charsetMap.put(CharsetFactory.NUMBERS, passwordParams.isNumber());
		charsetMap.put(CharsetFactory.SPECIAL, passwordParams.isSpecial());
		
		final String password = passwordGenerationService.generatePassword(passwordParams.getLength(), charsetMap);
		if (password.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input.");
        }
		return ResponseEntity
				.status(HttpStatus.CREATED).body(password);
	} 
	
}
