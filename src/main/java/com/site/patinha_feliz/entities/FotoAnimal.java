package com.site.patinha_feliz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FotoAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String urlS3;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "animal_id")
    private Animal animal;


}
