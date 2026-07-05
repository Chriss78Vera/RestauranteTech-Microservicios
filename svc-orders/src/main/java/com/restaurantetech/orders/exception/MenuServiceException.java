package com.restaurantetech.orders.exception;

/** Error usado cuando falla la conexion con svc-menu. */
public class MenuServiceException extends RuntimeException {
    // Guarda el mensaje y la causa original del fallo.
    public MenuServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
