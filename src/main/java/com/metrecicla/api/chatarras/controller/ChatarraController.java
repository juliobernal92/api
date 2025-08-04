package com.metrecicla.api.chatarras.controller;

import com.metrecicla.api.chatarras.dto.ChatarraDto;
import com.metrecicla.api.chatarras.service.ChatarraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatarras")
public class ChatarraController {
    private final ChatarraService chatarraService;

    public ChatarraController(ChatarraService chatarraService) {
        this.chatarraService = chatarraService;
    }

    @GetMapping
    public List<ChatarraDto> listar() {
        return chatarraService.findAllActivos();
    }

    @GetMapping("/{id}")
    public ChatarraDto buscarPorId(@PathVariable Long id) {
        return chatarraService.findById(id);
    }

    @PostMapping
    public ChatarraDto crear(@RequestBody ChatarraDto dto) {
        return chatarraService.save(dto);
    }

    @PutMapping("/{id}")
    public ChatarraDto actualizar(@PathVariable Long id, @RequestBody ChatarraDto dto) {
        dto.setId(id);
        return chatarraService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        chatarraService.desactivarChatarra(id);
    }
}
