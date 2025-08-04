package com.metrecicla.api.compras.repository;

import com.metrecicla.api.compras.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

}
