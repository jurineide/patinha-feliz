package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.UsuarioRequestDTO;
import com.site.patinha_feliz.dtos.UsuarioResponseDTO;
import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.exceptions.RecursoNaoEncontradoException;
import com.site.patinha_feliz.exceptions.RegraDeNegocioException;
import com.site.patinha_feliz.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO dados) {
        if (usuarioRepository.existsByEmail(dados.getEmail())) {
            throw new RegraDeNegocioException("Ja existe um usuario com o email " + dados.getEmail() + ".");
        }
        Usuario usuario = modelMapper.map(dados, Usuario.class);
        usuario.setId(null);
        usuario.setSenha(passwordEncoder.encode(dados.getSenha()));
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dados) {
        Usuario usuario = buscarEntidade(id);
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setBairro(dados.getBairro());
        usuario.setCidade(dados.getCidade());
        usuario.setEstado(dados.getEstado());
        usuario.setPerfil(dados.getPerfil());
        if (dados.getSenha() != null && !dados.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dados.getSenha()));
        }
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public void deletarUsuario(Long id) {
        usuarioRepository.delete(buscarEntidade(id));
    }

    private Usuario buscarEntidade(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));
    }

    private UsuarioResponseDTO toResponse(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }
}
