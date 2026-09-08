package com.site.patinha_feliz.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AnimalGetResponseDTO {

    private Long id;

    private String nome;

    private String sexo;

    private String raca;

    private String porte;

    private Integer idade;

    private String castracao;

    private String vacina;

    private Long idUsuario;

    private List<FotoResponseDTO> fotos;

}
