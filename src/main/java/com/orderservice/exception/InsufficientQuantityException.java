package com.orderservice.exception;

public class InsufficientQuantityException extends Throwable {
    public InsufficientQuantityException(String s) {
        super(s);
    }
}
