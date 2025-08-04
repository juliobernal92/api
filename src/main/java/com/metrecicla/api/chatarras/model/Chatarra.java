package com.metrecicla.api.chatarras.model;
import com.metrecicla.api.sucursales.model.Sucursal;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chatarras")
@Data
public class Chatarra{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long precio;
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
    private Boolean activo;
}
