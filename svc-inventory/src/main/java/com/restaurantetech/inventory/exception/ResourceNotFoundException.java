package com.restaurantetech.inventory.exception;

/** Error usado cuando no existe stock registrado para un plato. */
public class ResourceNotFoundException extends RuntimeException {
    // Guarda el mensaje que se mostrara al cliente.
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
