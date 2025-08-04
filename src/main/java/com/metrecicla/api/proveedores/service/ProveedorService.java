package com.metrecicla.api.proveedores.service;


import com.metrecicla.api.proveedores.dto.ProveedorDto;
import com.metrecicla.api.proveedores.model.Proveedor;
import com.metrecicla.api.proveedores.repository.ProveedorRepository;
import com.metrecicla.api.sucursales.model.Sucursal;
import com.metrecicla.api.sucursales.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private SucursalRepository sucursalRepository;

    private ProveedorDto toDto(Proveedor proveedor){
        ProveedorDto dto = new ProveedorDto();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setDireccion(proveedor.getDireccion());
        dto.setTelefono(proveedor.getTelefono());
        dto.setSucursalId(
                proveedor.getSucursal() != null ? proveedor.getSucursal().getId() : null
        );
        dto.setActivo(proveedor.getActivo());
        return dto;
    }

    private Proveedor toEntity(ProveedorDto dto){
        Proveedor proveedor = new Proveedor();
        proveedor.setId(dto.getId());
        proveedor.setNombre(dto.getNombre());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setTelefono(dto.getTelefono());
        Sucursal sucursal = dto.getSucursalId() != null
                ? sucursalRepository.findById(dto.getSucursalId()).orElse(null)
                : null;
        proveedor.setSucursal(sucursal);
        proveedor.setActivo(dto.getActivo());
        return proveedor;
    }

    public List<ProveedorDto> findAllActivos() {
        return proveedorRepository.findByActivoTrue()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ProveedorDto> findAll(){
        return proveedorRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProveedorDto findById(Long id) {
        return proveedorRepository.findById(id).map(this::toDto).orElse(null);
    }

    public ProveedorDto save(ProveedorDto dto) {
        Proveedor proveedor = toEntity(dto);
        Proveedor saved = proveedorRepository.save(proveedor);
        return toDto(saved);
    }

    public void desactivarProveedor(Long id) {
        proveedorRepository.findById(id).ifPresent(proveedor -> {
            proveedor.setActivo(false);
            proveedorRepository.save(proveedor);
        });
    }

}
