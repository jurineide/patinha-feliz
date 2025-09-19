package com.site.patinha_feliz.controllers;

import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    public UsuarioControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.salvarUsuario(any(Usuario.class))).thenReturn(
                new com.site.patinha_feliz.dtos.UsuarioResponseDTO() {{ setId(1L); }}
        );

        ResponseEntity<?> response = usuarioController.criarUsuario(usuario);

        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void deveListarUsuarios() {
        when(usuarioService.listarUsuarios()).thenReturn(List.of(new Usuario()));

        ResponseEntity<List<Usuario>> response = usuarioController.buscarUsuarios();

        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().isEmpty());
    }
}
