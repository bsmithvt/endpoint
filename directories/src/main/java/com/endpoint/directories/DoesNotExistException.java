package com.endpoint.directories;

public class DoesNotExistException extends Exception {
    DoesNotExistException(String msg) {
        super(msg);
    }
}
