package com.example.bodegas.repository;

import com.example.bodegas.entity.Bodega;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    // Carga los productos de cada bodega en una sola consulta (evita N+1)
    @Override
    @EntityGraph(attributePaths = "productos")
    List<Bodega> findAll();
}
