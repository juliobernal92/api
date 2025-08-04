package com.metrecicla.api.empleados.repository;

import com.metrecicla.api.empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByActivoTrue();
    Optional<Empleado> findByCedula(String cedula);
}
