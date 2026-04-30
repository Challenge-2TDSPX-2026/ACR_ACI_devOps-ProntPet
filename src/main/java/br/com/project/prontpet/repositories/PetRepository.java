package br.com.project.prontpet.repositories;

import br.com.project.prontpet.models.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetRepository extends JpaRepository <Pet, Long> {

    Page<Pet> findBySpeciesContainingIgnoreCase(String species, Pageable pageable);
    Page<Pet> findByRaceContainingIgnoreCase(String race, Pageable pageable);
    Page<Pet> findByAgeLessThanEqual(Integer age, Pageable pageable);
    Page<Pet> findByAgeGreaterThanEqual(Integer age, Pageable pageable);
}
