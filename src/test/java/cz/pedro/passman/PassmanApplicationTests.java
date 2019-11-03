package cz.pedro.passman;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.pedro.passman.engine.PasswordEngine;

@SpringBootTest
class PassmanApplicationTests {
	
	@Autowired
	public PasswordEngine passwordEngine;
	
	@Test
	void contextLoads() {
	}
	
}
