package com.site.patinha_feliz.services;

import com.site.patinha_feliz.entities.FotoAnimal;
import com.site.patinha_feliz.repositories.FotoAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotoAnimalService {
    @Autowired
    private FotoAnimalRepository fotoAnimalRepository;

    public List<FotoAnimal> findAll() {
        return fotoAnimalRepository.findAll();
    }

    public Optional<FotoAnimal> findById(Long id) {
        return fotoAnimalRepository.findById(id);
    }

    public FotoAnimal save(FotoAnimal fotoAnimal) {
        return fotoAnimalRepository.save(fotoAnimal);
    }

    public void deleteById(Long id) {
        fotoAnimalRepository.deleteById(id);
    }
}

