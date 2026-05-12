package br.com.project.prontpet.repositories;

import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetRepository extends JpaRepository <Pet, Long> {

    Page<Pet> findByOwnerContainingIgnoreCase (Owner owner, Pageable pageable);
    Page<Pet> findBySpeciesContainingIgnoreCase(String species, Pageable pageable);
    Page<Pet> findByBreedContainingIgnoreCase(String race, Pageable pageable);
    Page<Pet> findByAgeBetween(Integer age1, Integer age2, Pageable pageable);
}
