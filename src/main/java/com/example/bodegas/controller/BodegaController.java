package com.example.bodegas.controller;

import com.example.bodegas.dto.BodegaRequest;
import com.example.bodegas.dto.BodegaResponse;
import com.example.bodegas.service.BodegaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    private final BodegaService bodegaService;

    public BodegaController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    // POST /bodegas -> registrar bodega
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BodegaResponse registrar(@Valid @RequestBody BodegaRequest request) {
        return bodegaService.registrar(request);
    }

    // GET /bodegas -> consultar bodegas con sus productos asociados
    @GetMapping
    public List<BodegaResponse> listar() {
        return bodegaService.listar();
    }
}
