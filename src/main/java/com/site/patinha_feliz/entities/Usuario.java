package com.site.patinha_feliz.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private  String email;

    @Column( nullable = false)
    private  String senha;

    @Column( nullable = false)
    private String bairro;

    @Column( nullable = false)
    private  String cidade;

    @Column( nullable = false)
    private  String estado;

    @Column(unique = false, nullable = false)
    private  String perfil;

    // Um usuário pode ter vários animais
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Animal> animais;

    // Um usuário pode ter várias postagens
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Postagem> postagens;

}
