package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.UsuarioResponseDTO;
import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("123456");
        usuario.setBairro("Centro");
        usuario.setCidade("São Paulo");
        usuario.setEstado("SP");
        usuario.setPerfil("ADMIN");
    }

    @Test
    void deveSalvarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO dto = usuarioService.salvarUsuario(usuario);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
    }

    @Test
    void deveListarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        assertEquals(1, usuarios.size());
        assertEquals("Maria", usuarios.get(0).getNome());
    }

    @Test
    void deveBuscarPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Maria", result.getNome());
    }

    @Test
    void deveAtualizarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario atualizado = new Usuario();
        atualizado.setNome("Maria Silva");
        atualizado.setEmail("nova@email.com");
        atualizado.setBairro("Novo Bairro");
        atualizado.setCidade("Rio de Janeiro");
        atualizado.setEstado("RJ");
        atualizado.setPerfil("USER");

        Usuario result = usuarioService.atualizarUsuario(1L, atualizado);

        assertEquals("Maria Silva", result.getNome());
        assertEquals("RJ", result.getEstado());
    }

    @Test
    void deveDeletarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        boolean result = usuarioService.deletarUsuario(1L);

        assertTrue(result);
        verify(usuarioRepository, times(1)).delete(usuario);
    }
}
