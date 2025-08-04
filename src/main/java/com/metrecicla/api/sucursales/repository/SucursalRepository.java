package com.metrecicla.api.sucursales.repository;

import com.metrecicla.api.sucursales.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByActivoTrue();
}
