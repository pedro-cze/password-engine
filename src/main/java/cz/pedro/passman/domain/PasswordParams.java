package cz.pedro.passman.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordParams {

	private int length;
	
	private boolean lower;
	
	private boolean upper;
	
	private boolean number;
	
	private boolean special;
	
}
