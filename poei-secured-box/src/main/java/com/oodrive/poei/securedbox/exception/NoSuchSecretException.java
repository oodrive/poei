package com.oodrive.poei.securedbox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Nonexistent secret")
public class NoSuchSecretException extends RuntimeException {
}
