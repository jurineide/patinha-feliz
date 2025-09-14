package com.site.patinha_feliz.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String tipoPostagem;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL)
    private List<Foto> fotos;
}
