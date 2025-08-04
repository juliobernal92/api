package com.metrecicla.api.empleados.controller;

import com.metrecicla.api.empleados.dto.EmpleadoDto;
import com.metrecicla.api.empleados.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<EmpleadoDto> listar() {
        return empleadoService.findAllActivos();
    }


    @GetMapping("/{id}")
    public EmpleadoDto buscarPorId(@PathVariable Long id) {
        return empleadoService.findById(id);
    }

    @PostMapping
    public EmpleadoDto crear(@RequestBody EmpleadoDto dto) {
        return empleadoService.save(dto);
    }

    @PutMapping("/{id}")
    public EmpleadoDto actualizar(@PathVariable Long id, @RequestBody EmpleadoDto dto) {
        dto.setId(id);
        return empleadoService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoService.desactivarEmpleado(id);
    }

}
