package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.AnimalResponseDTO;
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

    public List<UsuarioResponseDTO> listarUsuariosDTO() {
        List<Usuario> usuarios = listarUsuarios();
        return usuarios.stream().map(this::toResponseDTO).toList();
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setBairro(usuario.getBairro());
        dto.setCidade(usuario.getCidade());
        dto.setEstado(usuario.getEstado());
        dto.setPerfil(usuario.getPerfil());
        if (usuario.getAnimais() != null) {
            dto.setAnimais(usuario.getAnimais().stream().map(animal -> {
                AnimalResponseDTO animalDTO = new AnimalResponseDTO();
                animalDTO.setId(animal.getId());
                animalDTO.setNome(animal.getNome());
                animalDTO.setSexo(animal.getSexo());
                animalDTO.setRaca(animal.getRaca());
                animalDTO.setPorte(animal.getPorte());
                animalDTO.setIdade(animal.getIdade());
                animalDTO.setCastracao(animal.getCastracao());
                animalDTO.setVacina(animal.getVacina());
                return animalDTO;
            }).toList());
        }
        return dto;
    }
}
