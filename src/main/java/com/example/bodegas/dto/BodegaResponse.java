package com.example.bodegas.dto;

import java.util.List;

public record BodegaResponse(
        Long id,
        String nombre,
        String ubicacion,
        List<ProductoResponse> productos
) {
}
