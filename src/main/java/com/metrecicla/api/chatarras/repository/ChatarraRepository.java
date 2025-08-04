package com.metrecicla.api.chatarras.repository;

import com.metrecicla.api.chatarras.model.Chatarra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatarraRepository extends JpaRepository<Chatarra, Long> {
    List<Chatarra> findByActivoTrue();
}
