package com.metrecicla.api.auth;
import lombok.Data;

@Data
public class LoginRequest {
    private String cedula;
    private String pass;
}
