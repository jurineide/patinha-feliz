package com.site.patinha_feliz.dtos;

import com.site.patinha_feliz.entities.Foto;
import com.site.patinha_feliz.entities.Usuario;
import jakarta.persistence.*;
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

    private List<Foto> fotos;

}
