package com.metrecicla.api.detalleCompra.dto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class DetalleCompraDto {
    private Long id;

    @NotNull(message = "La compra es obligatoria")
    private Long compraId;

    @NotNull(message = "La chatarra es obligatoria")
    private Long chatarraId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Float cantidad;

    @NotNull(message = "El precio pagado es obligatorio")
    @PositiveOrZero(message = "El precio pagado no puede ser negativo")
    private Long preciopagado;
    private Long subtotal;

}
