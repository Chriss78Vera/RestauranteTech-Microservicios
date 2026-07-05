package com.restaurantetech.orders.exception;

import lombok.*;

import java.time.LocalDateTime;

/** Respuesta sencilla para mostrar errores en Postman. */
@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
