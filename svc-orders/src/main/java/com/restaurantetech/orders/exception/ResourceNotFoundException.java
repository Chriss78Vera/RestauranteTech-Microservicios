package com.restaurantetech.orders.exception;

/** Error usado cuando algo no existe. */
public class ResourceNotFoundException extends RuntimeException {
    // Guarda el mensaje para responder al cliente.
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
