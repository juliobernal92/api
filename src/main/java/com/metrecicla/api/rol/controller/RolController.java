package com.metrecicla.api.rol.controller;

import com.metrecicla.api.empleados.dto.EmpleadoDto;
import com.metrecicla.api.empleados.service.EmpleadoService;
import com.metrecicla.api.rol.dto.RolDto;
import com.metrecicla.api.rol.service.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }


    @PostMapping
    public RolDto crear(@RequestBody RolDto dto) {
        return rolService.save(dto);
    }


}
