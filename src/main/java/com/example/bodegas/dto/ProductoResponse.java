package com.example.bodegas.dto;

import java.math.BigDecimal;

// DTO plano: no incluye la bodega, así se evitan referencias circulares en el JSON
public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer cantidad
) {
}
