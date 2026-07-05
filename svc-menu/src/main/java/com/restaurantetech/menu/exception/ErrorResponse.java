package com.restaurantetech.menu.exception;

import lombok.*;

import java.time.LocalDateTime;

/** Respuesta sencilla para mostrar errores de forma ordenada. */
@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
