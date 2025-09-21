package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.AnimalDTO;
import com.site.patinha_feliz.dtos.AnimalGetResponseDTO;
import com.site.patinha_feliz.dtos.AnimalResponseDTO;
import com.site.patinha_feliz.entities.Animal;
import com.site.patinha_feliz.entities.FotoAnimal;
import com.site.patinha_feliz.repositories.AnimalRepository;
import com.site.patinha_feliz.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    // Criar usuário
    public AnimalResponseDTO salvarAnimal(AnimalDTO animalDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Animal animal = modelMapper.map(animalDTO, Animal.class);
        animal.setId(null);

        // Associar o usuário pelo id
        if (animalDTO.getUsuarioId() != null) {
            animal.setUsuario(usuarioRepository.findById(animalDTO.getUsuarioId()).orElse(null));
        }

        // Mapeamento manual das fotos
        if (animalDTO.getFotos() != null && !animalDTO.getFotos().isEmpty()) {
            List<FotoAnimal> fotos = animalDTO.getFotos().stream().map(fotoDTO -> {
                FotoAnimal foto = new FotoAnimal();
                foto.setUrlS3(fotoDTO.getUrlS3()); // Usa o campo correto do DTO
                foto.setAnimal(animal); // Setando referência ao animal
                return foto;
            }).toList();
            animal.setFotos(fotos);
        }

        AnimalResponseDTO response = new AnimalResponseDTO();
        response.setId(animalRepository.save(animal).getId());
        return response;
    }

    // Listar todos
    public List<Animal> listarAnimals() {
        return animalRepository.findAll();
    }

    // Buscar por ID
    public AnimalGetResponseDTO buscarPorId(Long id) {
        try {
            Optional<Animal> optionalAnimal = animalRepository.findById(id);
            if (optionalAnimal.isPresent()) {
                var response = new AnimalGetResponseDTO();
                response.setId(optionalAnimal.get().getId());
                response.setNome(optionalAnimal.get().getNome());
                response.setSexo(optionalAnimal.get().getSexo());
                response.setRaca(optionalAnimal.get().getRaca());
                response.setPorte(optionalAnimal.get().getPorte());
                response.setIdade(optionalAnimal.get().getIdade());
                response.setCastracao(optionalAnimal.get().getCastracao());
                response.setVacina(optionalAnimal.get().getVacina());
                response.setIdUsuario(optionalAnimal.get().getUsuario().getId());
                List<FotoAnimal> fotos = optionalAnimal.get().getFotos();
                fotos.size();
                response.setFotos(fotos);
                return response;
            } else {
                throw new RuntimeException("Usuário com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
        }
    }

    // Atualizar
    public Animal atualizarAnimal(Long id, Animal dadosAtualizados) {
        return animalRepository.findById(id).map(animal -> {
            animal.setNome(dadosAtualizados.getNome());
            animal.setSexo(dadosAtualizados.getSexo());
            animal.setRaca(dadosAtualizados.getRaca());
            animal.setPorte(dadosAtualizados.getPorte());
            animal.setIdade(dadosAtualizados.getIdade());
            animal.setCastracao(dadosAtualizados.getCastracao());
            animal.setVacina(dadosAtualizados.getVacina());
            // Associar usuário pelo id se fornecido
            if (dadosAtualizados.getUsuario() != null && dadosAtualizados.getUsuario().getId() != null) {
                animal.setUsuario(usuarioRepository.findById(dadosAtualizados.getUsuario().getId()).orElse(null));
            }
            animal.setFotos(dadosAtualizados.getFotos());
            return animalRepository.save(animal);
        }).get();
    }

    // Deletar
    public boolean deletarAnimal(Long id) {
        return animalRepository.findById(id).map(animal -> {
            animalRepository.delete(animal);
            return true;
        }).orElse(false);
    }

    public AnimalGetResponseDTO toGetResponseDTO(Animal animal) {
        AnimalGetResponseDTO dto = new AnimalGetResponseDTO();
        dto.setId(animal.getId());
        dto.setNome(animal.getNome());
        dto.setSexo(animal.getSexo());
        dto.setRaca(animal.getRaca());
        dto.setPorte(animal.getPorte());
        dto.setIdade(animal.getIdade());
        dto.setCastracao(animal.getCastracao());
        dto.setVacina(animal.getVacina());
        dto.setIdUsuario(animal.getUsuario() != null ? animal.getUsuario().getId() : null);
        dto.setFotos(animal.getFotos());
        return dto;
    }
}

//TODO ver por que não está salvando as fotos no
