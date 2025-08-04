package com.metrecicla.api.compras.dto;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CompraDto {
    private Long id;
    @NotNull(message = "El proveedor es obligatorio")
    private Long proveedorId;
    @NotNull(message = "La Sucursal es obligatoria")
    private Long sucursalId;
    @NotNull(message = "El empleado es obligatorio")
    private Long empleadoId;
    private Long total;
    @NotNull(message = "La Fecha es obligatoria")
    private LocalDateTime fecha;
}
