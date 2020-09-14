package cz.pedro.passman;

import cz.pedro.passman.engine.PasswordEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PassmanApplicationTests {
	
	@Autowired
	public PasswordEngine passwordEngine;
	
	@Test
	void contextLoads() {
	}
	
}
