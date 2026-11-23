package com.example.bodegas.repository;

import com.example.bodegas.entity.Bodega;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    // Trae los productos en la misma consulta
    @EntityGraph(attributePaths = "productos")
    List<Bodega> findAllByOrderByIdAsc();
}
