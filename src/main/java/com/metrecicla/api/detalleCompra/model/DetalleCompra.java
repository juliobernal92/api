package com.metrecicla.api.detalleCompra.model;
import com.metrecicla.api.chatarras.model.Chatarra;
import com.metrecicla.api.compras.model.Compra;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "detalles_compra")
@Data
public class DetalleCompra{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "chatarra_id")
    private Chatarra chatarra;

    private Float cantidad;
    private Long preciopagado;
    private Long subtotal;
}
