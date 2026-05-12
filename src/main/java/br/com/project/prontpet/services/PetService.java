package br.com.project.prontpet.services;

import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import br.com.project.prontpet.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Page<Pet> getPets(Pageable pageable){
        return petRepository.findAll(pageable);
    }

    public Optional<Pet> getPetById(Long id){
        return petRepository.findById(id);
    }

    //Filtro por Raça
    public Page<Pet> getByBreed(String breed, Pageable pageable){
        return petRepository.findByBreedContainingIgnoreCase(breed, pageable);
    }

    //Filtro por idade
    public Page<Pet> getByAgeBetween(Integer age1, Integer age2, Pageable pageable){
        return  petRepository.findByAgeBetween(age1, age2,pageable);
    }

    //Filtro por Species
    public Page<Pet> getBySpecies(String species, Pageable pageable){
        return petRepository.findBySpeciesContainingIgnoreCase(species, pageable);
    }

    //Filtro por dono
    public Page<Pet> getByOwner(Owner owner, Pageable pageable){
        return petRepository.findByOwnerContainingIgnoreCase(owner, pageable);
    }

    public Pet addPet(Pet pet){
        return petRepository.save(pet);
    }

    public void deletePet (Long id) {
        var optionalPet = getPetById(id);
        if (optionalPet.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        petRepository.deleteById(id);}

    public Pet updatePet(Long id, Pet newPet){
        var optionalPet = getPetById(id);
        if (optionalPet.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        newPet.setId(id);
        petRepository.save(newPet);
        return newPet;
    }
}
