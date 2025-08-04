package com.metrecicla.api.detalleCompra.repository;

import com.metrecicla.api.detalleCompra.model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {

}
