package com.flatmate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    //TODO(alistair): sort this out!
    public static void handleError(final String error) {
        System.out.println("-------------------------");
        System.out.println();
        System.out.println("ERROR: " + error);
        System.out.println();
        System.out.println("-------------------------");
    }

}
