package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.UsuarioResponseDTO;
import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.repositories.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class UsuarioService {

    @Autowired
   UsuarioRepository usuarioRepository;


    // Criar usuário
    public UsuarioResponseDTO salvarUsuario(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuarioRepository.save(usuario).getId());
        return responseDTO;
    }

    // Listar todos
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID
    public Usuario buscarPorId(Long id) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

            if (optionalUsuario.isPresent()) {
                return optionalUsuario.get();
            } else {
                throw new RuntimeException("Usuário com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
        }
    }

    // Atualizar
    public Usuario atualizarUsuario(Long id, Usuario dadosAtualizados) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(dadosAtualizados.getNome());
            usuario.setEmail(dadosAtualizados.getEmail());
            usuario.setBairro(dadosAtualizados.getBairro());
            usuario.setCidade(dadosAtualizados.getCidade());
            usuario.setEstado(dadosAtualizados.getEstado());
            usuario.setPerfil(dadosAtualizados.getPerfil());
            return usuarioRepository.save(usuario);
        }).get();
    }

    // Deletar
    public boolean deletarUsuario(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
