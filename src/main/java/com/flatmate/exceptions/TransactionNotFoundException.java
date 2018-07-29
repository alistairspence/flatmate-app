package com.flatmate.exceptions;

import org.springframework.http.HttpStatus;

public class TransactionNotFoundException extends RuntimeException {

    private HttpStatus status;

    public TransactionNotFoundException(final HttpStatus status, final Long id) {
        super("Transaction not found with id: " + id);
        this.status = status;
    }

}
