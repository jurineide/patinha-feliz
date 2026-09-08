package com.site.patinha_feliz.dtos;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String bairro;
    private String cidade;
    private String estado;
    private String perfil;
}
