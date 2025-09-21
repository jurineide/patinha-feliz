package com.site.patinha_feliz.dtos;

import lombok.Data;

@Data
public class PostagemResponseDTO {
    private Long id;
    private String tipoPostagem;
    private String localizacao;
    private String descricao;
    private String status;
    private Long animalId;
    private String animalNome;
    private Long usuarioId;
    private String usuarioNome;
}

