package com.site.patinha_feliz.services;

import com.site.patinha_feliz.dtos.AnimalDTO;
import com.site.patinha_feliz.dtos.AnimalGetResponseDTO;
import com.site.patinha_feliz.dtos.AnimalResponseDTO;
import com.site.patinha_feliz.entities.Animal;
import com.site.patinha_feliz.entities.Foto;
import com.site.patinha_feliz.repositories.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;


    // Criar usuário
    public AnimalResponseDTO salvarAnimal(AnimalDTO animalDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Animal animal = modelMapper.map(animalDTO, Animal.class);
        animal.setId(null);
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
                List<Foto> fotos = optionalAnimal.get().getFotos();
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
            animal.setUsuario(dadosAtualizados.getUsuario());
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
}

//TODO ver por que não está salvando as fotos no
