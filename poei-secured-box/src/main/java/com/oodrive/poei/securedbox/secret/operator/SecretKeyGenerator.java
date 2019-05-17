package com.oodrive.poei.securedbox.secret.operator;

import javax.crypto.SecretKey;


public interface SecretKeyGenerator {

	SecretKey generate(char[] privateKey, byte[] salt);
}
