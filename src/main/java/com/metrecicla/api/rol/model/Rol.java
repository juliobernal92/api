package com.metrecicla.api.rol.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "roles")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
