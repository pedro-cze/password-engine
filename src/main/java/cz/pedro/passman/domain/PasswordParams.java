package cz.pedro.passman.domain;

import lombok.Data;

@Data
public class PasswordParams {

	private int length;
	
	private boolean lower;
	
	private boolean upper;
	
	private boolean number;
	
	private boolean special;
	
}
