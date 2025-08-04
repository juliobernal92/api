package com.metrecicla.api.sucursales.service;


import com.metrecicla.api.empleados.dto.EmpleadoDto;
import com.metrecicla.api.sucursales.dto.SucursalDto;
import com.metrecicla.api.sucursales.model.Sucursal;
import com.metrecicla.api.sucursales.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    private SucursalDto toDto(Sucursal sucursal){
        SucursalDto dto = new SucursalDto();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setFechaApertura(sucursal.getFechaApertura());
        dto.setTelefono(sucursal.getTelefono());
        dto.setActivo(sucursal.getActivo());
        return dto;
    }

    private Sucursal toEntity(SucursalDto dto){
        Sucursal sucursal = new Sucursal();
        sucursal.setId(dto.getId());
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setFechaApertura(dto.getFechaApertura());
        sucursal.setTelefono(dto.getTelefono());
        sucursal.setActivo(dto.getActivo());
        return sucursal;
    }

    public List<SucursalDto> findAllActivos() {
        return sucursalRepository.findByActivoTrue()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<SucursalDto> findAll(){
        return sucursalRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public SucursalDto findById(Long id) {
        return sucursalRepository.findById(id).map(this::toDto).orElse(null);
    }

    public SucursalDto save(SucursalDto dto) {
        Sucursal sucursal = toEntity(dto);
        Sucursal saved = sucursalRepository.save(sucursal);
        return toDto(saved);
    }

    public void desactivarSucursal(Long id) {
        sucursalRepository.findById(id).ifPresent(sucursal -> {
            sucursal.setActivo(false);
            sucursalRepository.save(sucursal);
        });
    }

}
