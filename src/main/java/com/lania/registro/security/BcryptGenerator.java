package com.lania.registro.security;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptGenerator {

	public static String generate(String textToEncode) {
		String hash = BCrypt.hashpw(textToEncode, BCrypt.gensalt(12));
		return hash;
	}
}
