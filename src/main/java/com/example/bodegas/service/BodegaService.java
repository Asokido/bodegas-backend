package com.example.bodegas.service;

import com.example.bodegas.dto.BodegaRequest;
import com.example.bodegas.dto.BodegaResponse;
import com.example.bodegas.dto.ProductoResponse;
import com.example.bodegas.entity.Bodega;
import com.example.bodegas.entity.Producto;
import com.example.bodegas.repository.BodegaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BodegaService {

    private final BodegaRepository bodegaRepository;

    public BodegaService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    @Transactional
    public BodegaResponse registrar(BodegaRequest request) {
        Bodega bodega = new Bodega(request.nombre().trim(), request.ubicacion().trim());
        return toResponse(bodegaRepository.save(bodega));
    }

    @Transactional(readOnly = true)
    public List<BodegaResponse> listar() {
        return bodegaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private BodegaResponse toResponse(Bodega bodega) {
        List<ProductoResponse> productos = bodega.getProductos()
                .stream()
                .map(this::toProductoResponse)
                .toList();

        return new BodegaResponse(
                bodega.getId(),
                bodega.getNombre(),
                bodega.getUbicacion(),
                productos
        );
    }

    private ProductoResponse toProductoResponse(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCantidad()
        );
    }
}
