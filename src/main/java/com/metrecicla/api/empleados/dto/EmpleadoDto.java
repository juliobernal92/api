package com.metrecicla.api.empleados.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;
    private Long sucursalId;
    private String direccion;
    private LocalDate fechaContratacion;
    private String telefono;
    private Boolean activo;
    private Long rolId;


}