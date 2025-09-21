package com.site.patinha_feliz.repositories;

import com.site.patinha_feliz.entities.FotoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoAnimalRepository extends JpaRepository<FotoAnimal, Long> {
}
