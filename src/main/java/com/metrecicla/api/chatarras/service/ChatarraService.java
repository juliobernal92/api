package com.metrecicla.api.chatarras.service;


import com.metrecicla.api.chatarras.dto.ChatarraDto;
import com.metrecicla.api.chatarras.model.Chatarra;
import com.metrecicla.api.chatarras.repository.ChatarraRepository;
import com.metrecicla.api.sucursales.model.Sucursal;
import com.metrecicla.api.sucursales.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatarraService {
    @Autowired
    private ChatarraRepository chatarraRepository;
    @Autowired
    private SucursalRepository sucursalRepository;

    private ChatarraDto toDto(Chatarra chatarra){
        ChatarraDto dto = new ChatarraDto();
        dto.setId(chatarra.getId());
        dto.setNombre(chatarra.getNombre());
        dto.setPrecio(chatarra.getPrecio());
        dto.setSucursalId(
                chatarra.getSucursal() != null ? chatarra.getSucursal().getId() : null
        );
        dto.setActivo(chatarra.getActivo());
        return dto;
    }

    private Chatarra toEntity(ChatarraDto dto){
        Chatarra chatarra = new Chatarra();
        chatarra.setId(dto.getId());
        chatarra.setNombre(dto.getNombre());
        chatarra.setPrecio(dto.getPrecio());
        Sucursal sucursal = dto.getSucursalId() != null
                ? sucursalRepository.findById(dto.getSucursalId()).orElse(null)
                : null;
        chatarra.setSucursal(sucursal);
        chatarra.setActivo(dto.getActivo());
        return chatarra;
    }

    public List<ChatarraDto> findAllActivos() {
        return chatarraRepository.findByActivoTrue()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ChatarraDto> findAll(){
        return chatarraRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ChatarraDto findById(Long id) {
        return chatarraRepository.findById(id).map(this::toDto).orElse(null);
    }

    public ChatarraDto save(ChatarraDto dto) {
        Chatarra chatarra = toEntity(dto);
        Chatarra saved = chatarraRepository.save(chatarra);
        return toDto(saved);
    }

    public void desactivarChatarra(Long id) {
        chatarraRepository.findById(id).ifPresent(chatarra -> {
            chatarra.setActivo(false);
            chatarraRepository.save(chatarra);
        });
    }

}
