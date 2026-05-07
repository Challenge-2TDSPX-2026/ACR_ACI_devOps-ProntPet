package br.com.project.prontpet.services;

import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import br.com.project.prontpet.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getPets(){
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id){
        return petRepository.findById(id);
    }

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
