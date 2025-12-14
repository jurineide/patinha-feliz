package com.site.patinha_feliz.controllers;

import com.site.patinha_feliz.dtos.PostagemDTO;
import com.site.patinha_feliz.dtos.PostagemResponseDTO;
import com.site.patinha_feliz.entities.Postagem;
import com.site.patinha_feliz.services.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {
    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public List<PostagemResponseDTO> getAllPostagens() {
        return postagemService.findAll().stream()
            .map(postagemService::toResponseDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getPostagemById(@PathVariable Long id) {
        Optional<Postagem> postagem = postagemService.findById(id);
        return postagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PostagemResponseDTO createPostagem(@RequestBody PostagemDTO dto) {
        Postagem postagem = postagemService.saveFromDTO(dto);
        return postagemService.toResponseDTO(postagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> updatePostagem(@PathVariable Long id, @RequestBody Postagem postagem) {
        if (!postagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        postagem.setId(id);
        return ResponseEntity.ok(postagemService.save(postagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostagem(@PathVariable Long id) {
        if (!postagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        postagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
