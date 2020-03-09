package com.oodrive.poei.securedbox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Invalid password")
public class InvalidPasswordException extends RuntimeException {
}
