package com.metrecicla.api.detalleCompra.controller;

import com.metrecicla.api.detalleCompra.dto.DetalleCompraDto;
import com.metrecicla.api.detalleCompra.service.DetalleCompraService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/detalle-compras")
public class DetalleCompraController {

    private final DetalleCompraService detalleCompraService;

    public DetalleCompraController(DetalleCompraService detalleCompraService) {
        this.detalleCompraService = detalleCompraService;
    }

    @GetMapping
    public List<DetalleCompraDto> listar() {
        return detalleCompraService.findAll();
    }

    @GetMapping("/{id}")
    public DetalleCompraDto buscarPorId(@PathVariable Long id) {
        return detalleCompraService.findById(id);
    }

    @PostMapping
    public DetalleCompraDto crear(@RequestBody @Valid DetalleCompraDto dto) {
        return detalleCompraService.save(dto);
    }

    @PutMapping("/{id}")
    public DetalleCompraDto actualizar(@PathVariable Long id, @RequestBody @Valid DetalleCompraDto dto) {
        dto.setId(id);
        return detalleCompraService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        detalleCompraService.deleteById(id);
    }
}
