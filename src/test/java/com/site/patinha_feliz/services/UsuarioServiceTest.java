package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.UsuarioRequestDTO;
import com.site.patinha_feliz.dtos.UsuarioResponseDTO;
import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.exceptions.RecursoNaoEncontradoException;
import com.site.patinha_feliz.exceptions.RegraDeNegocioException;
import com.site.patinha_feliz.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UsuarioService usuarioService;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService(usuarioRepository, passwordEncoder, new ModelMapper());

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("hash-existente");
        usuario.setBairro("Centro");
        usuario.setCidade("São Paulo");
        usuario.setEstado("SP");
        usuario.setPerfil("ADMIN");
    }

    private UsuarioRequestDTO novaRequisicao() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("Maria");
        dto.setEmail("maria@email.com");
        dto.setSenha("segredo123");
        dto.setBairro("Centro");
        dto.setCidade("São Paulo");
        dto.setEstado("SP");
        dto.setPerfil("ADMIN");
        return dto;
    }

    @Test
    void deveSalvarUsuarioComSenhaCriptografada() {
        when(usuarioRepository.existsByEmail("maria@email.com")).thenReturn(false);
        when(passwordEncoder.encode("segredo123")).thenReturn("hash-gerado");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> {
            Usuario u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });

        UsuarioResponseDTO dto = usuarioService.salvarUsuario(novaRequisicao());

        assertEquals(1L, dto.getId());
        assertEquals("maria@email.com", dto.getEmail());

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(captor.capture());
        assertEquals("hash-gerado", captor.getValue().getSenha());
    }

    @Test
    void deveRejeitarEmailDuplicado() {
        when(usuarioRepository.existsByEmail("maria@email.com")).thenReturn(true);

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.salvarUsuario(novaRequisicao()));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveListarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();

        assertEquals(1, usuarios.size());
        assertEquals("Maria", usuarios.get(0).getNome());
    }

    @Test
    void deveBuscarPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO result = usuarioService.buscarPorId(1L);

        assertEquals("Maria", result.getNome());
    }

    @Test
    void deveLancarQuandoUsuarioNaoExiste() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> usuarioService.buscarPorId(99L));
    }

    @Test
    void deveAtualizarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));
        when(passwordEncoder.encode(any())).thenReturn("novo-hash");

        UsuarioRequestDTO atualizado = novaRequisicao();
        atualizado.setNome("Maria Silva");
        atualizado.setEstado("RJ");

        UsuarioResponseDTO result = usuarioService.atualizarUsuario(1L, atualizado);

        assertEquals("Maria Silva", result.getNome());
        assertEquals("RJ", result.getEstado());
    }

    @Test
    void deveDeletarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.deletarUsuario(1L);

        verify(usuarioRepository, times(1)).delete(usuario);
    }
}
