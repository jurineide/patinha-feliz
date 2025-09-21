package com.site.patinha_feliz.dtos;

import lombok.Data;

@Data
public class AnimalResponseDTO {
    private Long id;
    private String nome;
    private String sexo;
    private String raca;
    private String porte;
    private Integer idade;
    private String castracao;
    private String vacina;
}
