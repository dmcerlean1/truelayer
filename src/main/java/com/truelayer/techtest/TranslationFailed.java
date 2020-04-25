package com.truelayer.techtest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Pokemon text could not be translated," +
        " please try again later")
public class TranslationFailed extends RuntimeException {
}
