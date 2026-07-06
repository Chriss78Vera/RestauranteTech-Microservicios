package com.restaurantetech.inventory.exception;

/** Error usado cuando una regla de inventario no se cumple. */
public class BusinessRuleException extends RuntimeException {
    // Guarda el mensaje de la regla que fallo.
    public BusinessRuleException(String message) {
        super(message);
    }
}
