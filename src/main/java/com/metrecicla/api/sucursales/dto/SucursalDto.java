package com.metrecicla.api.sucursales.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SucursalDto {
    private Long id;
    private String nombre;
    private String direccion;
    private LocalDate fechaApertura;
    private String telefono;
    private Boolean activo;
}
