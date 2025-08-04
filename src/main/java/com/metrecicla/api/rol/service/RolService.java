package com.metrecicla.api.rol.service;

import com.metrecicla.api.rol.dto.RolDto;
import com.metrecicla.api.rol.model.Rol;
import com.metrecicla.api.rol.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;


    private RolDto toDto(Rol rol) {
        RolDto dto = new RolDto();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        return dto;
    }

    private Rol toEntity(RolDto dto) {
        Rol rol = new Rol();
        rol.setId(dto.getId());
        rol.setNombre(dto.getNombre());
        return rol;
    }



    public List<RolDto> findAll() {
        return rolRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public RolDto findById(Long id) {
        return rolRepository.findById(id).map(this::toDto).orElse(null);
    }

    public RolDto save(RolDto dto) {
        Rol rol = toEntity(dto);
        Rol saved = rolRepository.save(rol);
        return toDto(saved);
    }



}
