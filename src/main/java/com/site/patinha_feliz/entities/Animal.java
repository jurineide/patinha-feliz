package com.site.patinha_feliz.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String raca;

    @Column(nullable = false)
    private String porte;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String castracao;

    @Column(nullable = false)
    private String vacina;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Foto> fotos;
}
