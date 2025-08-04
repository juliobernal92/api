package com.metrecicla.api.detalleCompra.service;

import com.metrecicla.api.chatarras.model.Chatarra;
import com.metrecicla.api.chatarras.repository.ChatarraRepository;
import com.metrecicla.api.compras.model.Compra;
import com.metrecicla.api.compras.repository.CompraRepository;
import com.metrecicla.api.detalleCompra.dto.DetalleCompraDto;
import com.metrecicla.api.detalleCompra.model.DetalleCompra;
import com.metrecicla.api.detalleCompra.repository.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ChatarraRepository chatarraRepository;

    // Conversión de entidad a DTO
    private DetalleCompraDto toDto(DetalleCompra detalle) {
        DetalleCompraDto dto = new DetalleCompraDto();
        dto.setId(detalle.getId());
        dto.setCompraId(
                detalle.getCompra() != null ? detalle.getCompra().getId() : null
        );
        dto.setChatarraId(
                detalle.getChatarra() != null ? detalle.getChatarra().getId() : null
        );
        dto.setCantidad(detalle.getCantidad());
        dto.setPreciopagado(detalle.getPreciopagado());
        dto.setSubtotal(detalle.getSubtotal());   // <-- Inclúyelo aquí
        return dto;
    }


    // Conversión de DTO a entidad
    private DetalleCompra toEntity(DetalleCompraDto dto) {
        DetalleCompra detalle = new DetalleCompra();
        detalle.setId(dto.getId());

        Compra compra = dto.getCompraId() != null
                ? compraRepository.findById(dto.getCompraId()).orElse(null)
                : null;
        detalle.setCompra(compra);

        Chatarra chatarra = dto.getChatarraId() != null
                ? chatarraRepository.findById(dto.getChatarraId()).orElse(null)
                : null;
        detalle.setChatarra(chatarra);

        detalle.setCantidad(dto.getCantidad());
        detalle.setPreciopagado(dto.getPreciopagado());

        // Cálculo automático de subtotal
        Float cantidad = dto.getCantidad();
        Long preciopagado = dto.getPreciopagado();
        Long subtotal = (cantidad != null && preciopagado != null)
                ? Math.round(cantidad * preciopagado)
                : 0L;
        detalle.setSubtotal(subtotal);

        return detalle;
    }


    public List<DetalleCompraDto> findAll() {
        return detalleCompraRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public DetalleCompraDto findById(Long id) {
        return detalleCompraRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public DetalleCompraDto save(DetalleCompraDto dto) {
        DetalleCompra detalle = toEntity(dto);
        DetalleCompra saved = detalleCompraRepository.save(detalle);
        return toDto(saved);
    }

    public void deleteById(Long id) {
        detalleCompraRepository.deleteById(id);
    }
}
