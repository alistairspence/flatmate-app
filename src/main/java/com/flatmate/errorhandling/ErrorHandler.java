package com.flatmate.errorhandling;

import com.flatmate.exceptions.AccountNotFoundException;
import com.flatmate.exceptions.TransactionNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    // TODO(alistair): handle methodargumentmismatch/methodargumentnotvalid with the notfounds? ie. if you search for aaa should you get 400 or 404? (ties up with the uuid thoughts)
    @ExceptionHandler(value = {AccountNotFoundException.class, TransactionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundExceptions(final HttpServletRequest request,
                                             final RuntimeException ex) {
        return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    }


}
