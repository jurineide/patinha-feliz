package com.site.patinha_feliz.controllers;

import com.site.patinha_feliz.dtos.AnimalDTO;
import com.site.patinha_feliz.dtos.AnimalGetResponseDTO;
import com.site.patinha_feliz.dtos.AnimalResponseDTO;
import com.site.patinha_feliz.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> criarAnimal(@Valid @RequestBody AnimalDTO animal) {
        var response = animalService.salvarAnimal(animal);
        URI location = URI.create("/animal/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalGetResponseDTO> buscarAnimalPorId(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AnimalGetResponseDTO>> buscarAnimais() {
        return ResponseEntity.ok(animalService.listarAnimals());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalGetResponseDTO> alterarDadosAnimal(@PathVariable Long id,
                                                                  @Valid @RequestBody AnimalDTO animal) {
        return ResponseEntity.ok(animalService.atualizarAnimal(id, animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Long id) {
        animalService.deletarAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
