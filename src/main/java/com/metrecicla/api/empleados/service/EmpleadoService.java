package com.metrecicla.api.empleados.service;

import com.metrecicla.api.empleados.model.Empleado;
import com.metrecicla.api.empleados.dto.EmpleadoDto;
import com.metrecicla.api.empleados.repository.EmpleadoRepository;
import com.metrecicla.api.rol.model.Rol;
import com.metrecicla.api.rol.repository.RolRepository;
import com.metrecicla.api.sucursales.model.Sucursal;
import com.metrecicla.api.sucursales.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    private EmpleadoDto toDto(Empleado empleado) {
        EmpleadoDto dto = new EmpleadoDto();
        dto.setId(empleado.getId());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setCedula(empleado.getCedula());
        dto.setPass(empleado.getPass());
        dto.setSucursalId(
                empleado.getSucursal() != null ? empleado.getSucursal().getId() : null
        );

        dto.setDireccion(empleado.getDireccion());
        dto.setFechaContratacion(empleado.getFechaContratacion());
        dto.setTelefono(empleado.getTelefono());
        dto.setActivo(empleado.getActivo());
        dto.setRolId(
                empleado.getRol() !=null ? empleado.getRol().getId() : null
        );
        return dto;
    }

    private Empleado toEntity(EmpleadoDto dto) {
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId());
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setCedula(dto.getCedula());
        empleado.setPass(dto.getPass());
        Sucursal sucursal = dto.getSucursalId() != null
                ? sucursalRepository.findById(dto.getSucursalId()).orElse(null)
                : null;
        empleado.setSucursal(sucursal);
        empleado.setDireccion(dto.getDireccion());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        empleado.setTelefono(dto.getTelefono());
        empleado.setActivo(dto.getActivo());
        Rol rol = dto.getRolId() !=null
                ? rolRepository.findById(dto.getRolId()).orElse(null)
                : null;
        empleado.setRol(rol);
        return empleado;
    }

    public List<EmpleadoDto> findAllActivos() {
        return empleadoRepository.findByActivoTrue()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<EmpleadoDto> findAll() {
        return empleadoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public EmpleadoDto findById(Long id) {
        return empleadoRepository.findById(id).map(this::toDto).orElse(null);
    }

    public EmpleadoDto save(EmpleadoDto dto) {
        Empleado empleado = toEntity(dto);
        empleado.setActivo(true);
        if (dto.getPass() != null && !dto.getPass().isBlank()) {
            empleado.setPass(passwordEncoder.encode(dto.getPass()));
        }
        Empleado saved = empleadoRepository.save(empleado);
        return toDto(saved);
    }

    public void desactivarEmpleado(Long id) {
        empleadoRepository.findById(id).ifPresent(empleado -> {
            empleado.setActivo(false);
            empleadoRepository.save(empleado);
        });
    }

}
