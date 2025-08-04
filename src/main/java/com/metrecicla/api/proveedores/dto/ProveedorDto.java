package com.metrecicla.api.proveedores.dto;
import lombok.Data;


@Data
public class ProveedorDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private Long sucursalId;
    private Boolean activo;
}
