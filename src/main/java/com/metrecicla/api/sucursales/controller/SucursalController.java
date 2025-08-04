package com.metrecicla.api.sucursales.controller;

import com.metrecicla.api.sucursales.dto.SucursalDto;
import com.metrecicla.api.sucursales.service.SucursalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {
    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping
    public List<SucursalDto> listar() {
        return sucursalService.findAllActivos();
    }

    @GetMapping("/{id}")
    public SucursalDto buscarPorId(@PathVariable Long id) {
        return sucursalService.findById(id);
    }

    @PostMapping
    public SucursalDto crear(@RequestBody SucursalDto dto) {
        return sucursalService.save(dto);
    }

    @PutMapping("/{id}")
    public SucursalDto actualizar(@PathVariable Long id, @RequestBody SucursalDto dto) {
        dto.setId(id);
        return sucursalService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        sucursalService.desactivarSucursal(id);
    }
}
