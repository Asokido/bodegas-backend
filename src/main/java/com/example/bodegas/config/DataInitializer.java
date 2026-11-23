package com.example.bodegas.config;

import com.example.bodegas.entity.Bodega;
import com.example.bodegas.entity.Producto;
import com.example.bodegas.repository.BodegaRepository;
import com.example.bodegas.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

// Carga los datos de prueba al arrancar, usando los repositorios.
// Solo inserta si la base de datos está vacía.
@Component
public class DataInitializer implements CommandLineRunner {

    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;

    public DataInitializer(BodegaRepository bodegaRepository, ProductoRepository productoRepository) {
        this.bodegaRepository = bodegaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (bodegaRepository.count() > 0) {
            return;
        }

        Bodega norte = bodegaRepository.save(new Bodega("Bodega Norte", "Zona norte"));
        Bodega sur = bodegaRepository.save(new Bodega("Bodega Sur", "Zona sur"));

        productoRepository.save(new Producto(
                "Computador portátil", "Portátil de uso general",
                new BigDecimal("2500000"), 15, norte));

        productoRepository.save(new Producto(
                "Monitor", "Monitor de escritorio",
                new BigDecimal("750000"), 20, norte));

        productoRepository.save(new Producto(
                "Impresora", "Impresora láser",
                new BigDecimal("1200000"), 8, sur));
    }
}
