package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.AnimalDTO;
import com.site.patinha_feliz.dtos.AnimalGetResponseDTO;
import com.site.patinha_feliz.dtos.AnimalResponseDTO;
import com.site.patinha_feliz.dtos.FotoDTO;
import com.site.patinha_feliz.dtos.FotoResponseDTO;
import com.site.patinha_feliz.entities.Animal;
import com.site.patinha_feliz.entities.Foto;
import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.exceptions.RecursoNaoEncontradoException;
import com.site.patinha_feliz.repositories.AnimalRepository;
import com.site.patinha_feliz.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final UsuarioRepository usuarioRepository;

    public AnimalService(AnimalRepository animalRepository, UsuarioRepository usuarioRepository) {
        this.animalRepository = animalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public AnimalResponseDTO salvarAnimal(AnimalDTO dados) {
        Animal animal = new Animal();
        aplicarDados(animal, dados);
        animal.setFotos(mapearFotos(dados.getFotos(), animal));

        AnimalResponseDTO response = new AnimalResponseDTO();
        response.setId(animalRepository.save(animal).getId());
        return response;
    }

    @Transactional(readOnly = true)
    public List<AnimalGetResponseDTO> listarAnimals() {
        return animalRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AnimalGetResponseDTO buscarPorId(Long id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public AnimalGetResponseDTO atualizarAnimal(Long id, AnimalDTO dados) {
        Animal animal = buscarEntidade(id);
        aplicarDados(animal, dados);
        return toResponse(animalRepository.save(animal));
    }

    @Transactional
    public void deletarAnimal(Long id) {
        animalRepository.delete(buscarEntidade(id));
    }

    private void aplicarDados(Animal animal, AnimalDTO dados) {
        animal.setNome(dados.getNome());
        animal.setSexo(dados.getSexo());
        animal.setRaca(dados.getRaca());
        animal.setPorte(dados.getPorte());
        animal.setIdade(dados.getIdade());
        animal.setCastracao(dados.getCastracao());
        animal.setVacina(dados.getVacina());
        animal.setUsuario(resolverUsuario(dados.getUsuarioId()));
    }

    private Animal buscarEntidade(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Animal", id));
    }

    private Usuario resolverUsuario(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", usuarioId));
    }

    private List<Foto> mapearFotos(List<FotoDTO> fotosDTO, Animal animal) {
        List<Foto> fotos = new ArrayList<>();
        if (fotosDTO == null) {
            return fotos;
        }
        for (FotoDTO fotoDTO : fotosDTO) {
            Foto foto = new Foto();
            foto.setUrlS3(fotoDTO.getUrl());
            foto.setAnimal(animal);
            fotos.add(foto);
        }
        return fotos;
    }

    private AnimalGetResponseDTO toResponse(Animal animal) {
        AnimalGetResponseDTO response = new AnimalGetResponseDTO();
        response.setId(animal.getId());
        response.setNome(animal.getNome());
        response.setSexo(animal.getSexo());
        response.setRaca(animal.getRaca());
        response.setPorte(animal.getPorte());
        response.setIdade(animal.getIdade());
        response.setCastracao(animal.getCastracao());
        response.setVacina(animal.getVacina());
        response.setIdUsuario(Optional.ofNullable(animal.getUsuario()).map(Usuario::getId).orElse(null));
        response.setFotos(mapearFotosResponse(animal.getFotos()));
        return response;
    }

    private List<FotoResponseDTO> mapearFotosResponse(List<Foto> fotos) {
        if (fotos == null) {
            return List.of();
        }
        return fotos.stream().map(foto -> {
            FotoResponseDTO dto = new FotoResponseDTO();
            dto.setId(foto.getId());
            dto.setUrlS3(foto.getUrlS3());
            return dto;
        }).toList();
    }
}
