package com.metrecicla.api.sucursales.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "sucursales")
@Data
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private LocalDate fechaApertura;
    private String telefono;
    private Boolean activo;
}
