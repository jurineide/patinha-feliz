package com.site.patinha_feliz.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FotoPostagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String urlS3;

    @ManyToOne
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;
}
