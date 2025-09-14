package com.site.patinha_feliz.controllers;


import com.site.patinha_feliz.dtos.UsuarioResponseDTO;
import com.site.patinha_feliz.entities.Usuario;
import com.site.patinha_feliz.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Criar usuário
    @PostMapping ()
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody Usuario usuario) {
        var response = usuarioService.salvarUsuario(usuario);
        URI location = URI.create("/usuarios/" + response.getId());
        return ResponseEntity.created(location).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPoID(@PathVariable Long id){
        var response = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> buscarUsuarios() {
        var response = usuarioService.listarUsuarios();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarDadosUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        var response = usuarioService.atualizarUsuario(id, usuario);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarUsuario(@PathVariable Long id){
        var response = usuarioService.deletarUsuario(id);
        return ResponseEntity.ok(response);
    }
}
