package com.site.patinha_feliz.dtos;

import lombok.Data;

@Data
public class PostagemDTO {
    private String tipoPostagem;
    private String localizacao;
    private String descricao;
}