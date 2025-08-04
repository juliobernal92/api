package com.metrecicla.api.compras.model;
import com.metrecicla.api.empleados.model.Empleado;
import com.metrecicla.api.proveedores.model.Proveedor;
import com.metrecicla.api.sucursales.model.Sucursal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
@Data
public class Compra{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;


    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
    private LocalDateTime fecha;
    private Long total;
}
