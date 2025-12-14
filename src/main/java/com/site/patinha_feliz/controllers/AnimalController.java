package com.site.patinha_feliz.controllers;


import com.site.patinha_feliz.dtos.AnimalDTO;
import com.site.patinha_feliz.dtos.AnimalGetResponseDTO;
import com.site.patinha_feliz.dtos.AnimalResponseDTO;
import com.site.patinha_feliz.entities.Animal;
import com.site.patinha_feliz.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @PostMapping ()
    public ResponseEntity<AnimalResponseDTO> criarUsuario(@RequestBody AnimalDTO animal) {
        var response = animalService.salvarAnimal(animal);
        URI location = URI.create("/animal/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalGetResponseDTO> buscarUsuarioPoID(@PathVariable Long id){
        var response = animalService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<AnimalGetResponseDTO>> buscarAnimal() {
        var animals = animalService.listarAnimals();
        // Converter para DTOs
        List<AnimalGetResponseDTO> dtos = animals.stream().map(animalService::toGetResponseDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalGetResponseDTO> alterarDadosAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        var updated = animalService.atualizarAnimal(id, animal);
        var dto = animalService.toGetResponseDTO(updated);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarAnimal(@PathVariable Long id){
        var response = animalService.deletarAnimal(id);
        return ResponseEntity.ok(response);
    }

}
