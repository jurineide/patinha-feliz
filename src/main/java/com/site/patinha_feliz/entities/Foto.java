package com.site.patinha_feliz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url_s3")
    private String urlS3;

    // Uma foto pode estar ligada a um animal
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // Ou pode estar ligada a uma postagem
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;

}
