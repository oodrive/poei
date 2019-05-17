package com.oodrive.poei.filestorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "File not found")
public class FileNotFoundException extends RuntimeException {

}
