package com.site.patinha_feliz.services;

import com.site.patinha_feliz.entities.Postagem;
import com.site.patinha_feliz.repositories.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.patinha_feliz.dtos.PostagemDTO;
import com.site.patinha_feliz.dtos.PostagemResponseDTO;
import com.site.patinha_feliz.entities.Animal;
import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.repositories.AnimalRepository;
import com.site.patinha_feliz.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {
    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Postagem> findAll() {
        return postagemRepository.findAll();
    }

    public Optional<Postagem> findById(Long id) {
        return postagemRepository.findById(id);
    }

    public Postagem save(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    public void deleteById(Long id) {
        postagemRepository.deleteById(id);
    }

    public Postagem saveFromDTO(PostagemDTO dto) {
        Postagem postagem = new Postagem();
        postagem.setTipoPostagem(dto.getTipoPostagem());
        postagem.setLocalizacao(dto.getLocalizacao());
        postagem.setDescricao(dto.getDescricao());
        postagem.setStatus(dto.getStatus());
        if (dto.getAnimalId() != null) {
            postagem.setAnimal(animalRepository.findById(dto.getAnimalId()).orElse(null));
        }
        if (dto.getUsuarioId() != null) {
            postagem.setUsuario(usuarioRepository.findById(dto.getUsuarioId()).orElse(null));
        }
        return postagemRepository.save(postagem);
    }

    public PostagemResponseDTO toResponseDTO(Postagem postagem) {
        PostagemResponseDTO dto = new PostagemResponseDTO();
        dto.setId(postagem.getId());
        dto.setTipoPostagem(postagem.getTipoPostagem());
        dto.setLocalizacao(postagem.getLocalizacao());
        dto.setDescricao(postagem.getDescricao());
        dto.setStatus(postagem.getStatus());
        if (postagem.getAnimal() != null) {
            dto.setAnimalId(postagem.getAnimal().getId());
            dto.setAnimalNome(postagem.getAnimal().getNome());
        }
        if (postagem.getUsuario() != null) {
            dto.setUsuarioId(postagem.getUsuario().getId());
            dto.setUsuarioNome(postagem.getUsuario().getNome());
        }
        return dto;
    }
}
