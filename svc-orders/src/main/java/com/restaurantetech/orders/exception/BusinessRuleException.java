package com.restaurantetech.orders.exception;

/** Error usado cuando una regla del negocio no se cumple. */
public class BusinessRuleException extends RuntimeException {
    // Guarda el mensaje de la regla que fallo.
    public BusinessRuleException(String message) {
        super(message);
    }
}
