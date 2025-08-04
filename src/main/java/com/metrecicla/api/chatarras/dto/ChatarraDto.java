package com.metrecicla.api.chatarras.dto;
import lombok.Data;


@Data
public class ChatarraDto {
    private Long id;
    private String nombre;
    private Long precio;
    private Long sucursalId;
    private Boolean activo;
}
