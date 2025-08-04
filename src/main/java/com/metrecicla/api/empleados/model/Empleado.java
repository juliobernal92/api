package com.metrecicla.api.empleados.model;

import com.metrecicla.api.rol.model.Rol;
import jakarta.persistence.*;
import lombok.Data;
import com.metrecicla.api.sucursales.model.Sucursal;

import java.time.LocalDate;

@Entity
@Table(
        name = "empleados",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cedula")
        }
)
@Data
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String cedula;
    private String pass;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    private String direccion;
    private LocalDate fechaContratacion;
    private String telefono;
    private Boolean activo;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
