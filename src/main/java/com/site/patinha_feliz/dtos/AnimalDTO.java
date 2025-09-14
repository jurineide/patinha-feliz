package com.site.patinha_feliz.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AnimalDTO {
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("sexo")
    private String sexo;
    @JsonProperty("raca")
    private String raca;
    @JsonProperty("porte")
    private String porte;
    @JsonProperty("idade")
    private Integer idade;
    @JsonProperty("castracao")
    private String castracao;
    @JsonProperty("vacina")
    private String vacina;
    @JsonProperty("usuario_id")
    private Long usuarioId;
    @JsonProperty("fotos")
    private List<FotoDTO> fotos;

}


