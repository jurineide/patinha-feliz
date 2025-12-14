package com.site.patinha_feliz.dtos;

import lombok.Data;
import java.util.List;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String bairro;
    private String cidade;
    private String estado;
    private String perfil;
    private List<AnimalResponseDTO> animais;
}
