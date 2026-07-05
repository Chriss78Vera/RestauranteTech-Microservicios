package com.restaurantetech.menu.exception;

/** Error usado cuando no se encuentra un recurso pedido. */
public class ResourceNotFoundException extends RuntimeException {
    // Guarda el mensaje que se mostrara al cliente.
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
