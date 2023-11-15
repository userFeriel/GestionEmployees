package com.kafka.Spring.controller.exceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

}
