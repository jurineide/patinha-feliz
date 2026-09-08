package com.site.patinha_feliz.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class AnimalDTO {

    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @NotBlank
    @JsonProperty("sexo")
    private String sexo;

    @NotBlank
    @JsonProperty("raca")
    private String raca;

    @NotBlank
    @JsonProperty("porte")
    private String porte;

    @NotNull
    @PositiveOrZero
    @JsonProperty("idade")
    private Integer idade;

    @NotBlank
    @JsonProperty("castracao")
    private String castracao;

    @NotBlank
    @JsonProperty("vacina")
    private String vacina;

    @NotNull
    @JsonProperty("usuario_id")
    private Long usuarioId;

    @JsonProperty("fotos")
    private List<FotoDTO> fotos;
}
