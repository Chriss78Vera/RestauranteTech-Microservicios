package com.restaurantetech.menu.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/** Datos que se mandan desde Postman para crear o editar un plato. */
@Data
public class DishRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    @NotBlank
    @Pattern(regexp = "ENTRADA|PLATO_FUERTE|POSTRE|BEBIDA", message = "debe ser ENTRADA, PLATO_FUERTE, POSTRE o BEBIDA")
    private String category;

    @NotNull
    @Positive
    private Double price;

    private Boolean available = true;
}
