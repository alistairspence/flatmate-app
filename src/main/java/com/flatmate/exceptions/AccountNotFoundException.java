package com.flatmate.exceptions;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends RuntimeException {

    private HttpStatus status;

    public AccountNotFoundException(final HttpStatus status, final Long id) {
        super("Account not found with id: " + id);
        this.status = status;
    }

}
