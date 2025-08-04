package com.metrecicla.api.compras.service;

import com.metrecicla.api.compras.dto.CompraDto;
import com.metrecicla.api.compras.model.Compra;
import com.metrecicla.api.compras.repository.CompraRepository;
import com.metrecicla.api.config.ResourceNotFoundException;
import com.metrecicla.api.empleados.model.Empleado;
import com.metrecicla.api.empleados.repository.EmpleadoRepository;
import com.metrecicla.api.proveedores.model.Proveedor;
import com.metrecicla.api.proveedores.repository.ProveedorRepository;
import com.metrecicla.api.sucursales.model.Sucursal;
import com.metrecicla.api.sucursales.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Convertir entidad a DTO
    private CompraDto toDto(Compra compra) {
        CompraDto dto = new CompraDto();
        dto.setId(compra.getId());
        dto.setTotal(compra.getTotal());
        dto.setFecha(compra.getFecha());
        dto.setProveedorId(
                compra.getProveedor() != null ? compra.getProveedor().getId() : null
        );
        dto.setSucursalId(
                compra.getSucursal() != null ? compra.getSucursal().getId() : null
        );
        dto.setEmpleadoId(
                compra.getEmpleado() != null ? compra.getEmpleado().getId() : null
        );
        return dto;
    }

    // Convertir DTO a entidad
    private Compra toEntity(CompraDto dto) {
        Compra compra = new Compra();
        compra.setId(dto.getId());
        compra.setTotal(dto.getTotal());
        compra.setFecha(dto.getFecha());

        Proveedor proveedor = dto.getProveedorId() != null
                ? proveedorRepository.findById(dto.getProveedorId()).orElse(null)
                : null;
        compra.setProveedor(proveedor);

        Sucursal sucursal = dto.getSucursalId() != null
                ? sucursalRepository.findById(dto.getSucursalId()).orElse(null)
                : null;
        compra.setSucursal(sucursal);

        Empleado empleado = dto.getEmpleadoId() != null
                ? empleadoRepository.findById(dto.getEmpleadoId()).orElse(null)
                : null;
        compra.setEmpleado(empleado);

        return compra;
    }

    public List<CompraDto> findAll() {
        return compraRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CompraDto findById(Long id) {
        return compraRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Compra no encontrada con id: " + id));
    }


    public CompraDto save(CompraDto dto) {
        Compra compra = toEntity(dto);
        Compra saved = compraRepository.save(compra);
        return toDto(saved);
    }

    public void deleteById(Long id) {
        compraRepository.deleteById(id);
    }
}
