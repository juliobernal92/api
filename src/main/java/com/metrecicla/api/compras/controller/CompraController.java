package com.metrecicla.api.compras.controller;

import com.metrecicla.api.compras.dto.CompraDto;
import com.metrecicla.api.compras.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public List<CompraDto> listar() {
        return compraService.findAll();
    }

    @GetMapping("/{id}")
    public CompraDto buscarPorId(@PathVariable Long id) {
        return compraService.findById(id);
    }

    @PostMapping
    public CompraDto crear(@RequestBody @Valid CompraDto dto) {
        return compraService.save(dto);
    }

    @PutMapping("/{id}")
    public CompraDto actualizar(@PathVariable Long id, @RequestBody @Valid CompraDto dto) {
        dto.setId(id);
        return compraService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        compraService.deleteById(id);
    }
}
