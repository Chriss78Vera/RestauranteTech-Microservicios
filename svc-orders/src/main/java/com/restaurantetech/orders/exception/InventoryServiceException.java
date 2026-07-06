package com.restaurantetech.orders.exception;

/** Error usado cuando falla la conexion con svc-inventory. */
public class InventoryServiceException extends RuntimeException {
    // Guarda el mensaje y la causa original del fallo.
    public InventoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
