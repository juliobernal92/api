package com.metrecicla.api.proveedores.controller;

import com.metrecicla.api.proveedores.dto.ProveedorDto;
import com.metrecicla.api.proveedores.service.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<ProveedorDto> listar() {
        return proveedorService.findAllActivos();
    }

    @GetMapping("/{id}")
    public ProveedorDto buscarPorId(@PathVariable Long id) {
        return proveedorService.findById(id);
    }

    @PostMapping
    public ProveedorDto crear(@RequestBody ProveedorDto dto) {
        return proveedorService.save(dto);
    }

    @PutMapping("/{id}")
    public ProveedorDto actualizar(@PathVariable Long id, @RequestBody ProveedorDto dto) {
        dto.setId(id);
        return proveedorService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        proveedorService.desactivarProveedor(id);
    }
}
