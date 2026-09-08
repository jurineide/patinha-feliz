package com.site.patinha_feliz.controllers;

import com.site.patinha_feliz.dtos.UsuarioRequestDTO;
import com.site.patinha_feliz.dtos.UsuarioResponseDTO;
import com.site.patinha_feliz.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private UsuarioResponseDTO respostaComId(Long id) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(id);
        return dto;
    }

    @Test
    void deveCriarUsuario() {
        when(usuarioService.salvarUsuario(any(UsuarioRequestDTO.class))).thenReturn(respostaComId(1L));

        ResponseEntity<UsuarioResponseDTO> response = usuarioController.criarUsuario(new UsuarioRequestDTO());

        assertEquals(201, response.getStatusCode().value());
        assertEquals("/usuarios/1", response.getHeaders().getLocation().toString());
    }

    @Test
    void deveListarUsuarios() {
        when(usuarioService.listarUsuarios()).thenReturn(List.of(respostaComId(1L)));

        ResponseEntity<List<UsuarioResponseDTO>> response = usuarioController.buscarUsuarios();

        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveRetornar204AoDeletar() {
        ResponseEntity<Void> response = usuarioController.deletarUsuario(1L);

        assertEquals(204, response.getStatusCode().value());
        verify(usuarioService).deletarUsuario(1L);
    }
}
