package com.site.patinha_feliz.controllers;

import com.site.patinha_feliz.entities.FotoAnimal;
import com.site.patinha_feliz.services.FotoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fotos")
public class FotoAnimalController {
    @Autowired
    private FotoAnimalService fotoAnimalService;

    @GetMapping
    public List<FotoAnimal> getAllFotos() {
        return fotoAnimalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FotoAnimal> getFotoById(@PathVariable Long id) {
        Optional<FotoAnimal> foto = fotoAnimalService.findById(id);
        return foto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FotoAnimal createFoto(@RequestBody FotoAnimal fotoAnimal) {
        return fotoAnimalService.save(fotoAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FotoAnimal> updateFoto(@PathVariable Long id, @RequestBody FotoAnimal fotoAnimal) {
        if (!fotoAnimalService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        fotoAnimal.setId(id);
        return ResponseEntity.ok(fotoAnimalService.save(fotoAnimal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoto(@PathVariable Long id) {
        if (!fotoAnimalService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        fotoAnimalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

