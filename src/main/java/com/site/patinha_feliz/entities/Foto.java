package com.site.patinha_feliz.entities;

import jakarta.persistence.*;

@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url_s3;

    // Uma foto pode estar ligada a um animal
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // Ou pode estar ligada a uma postagem
    @ManyToOne
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;


}
