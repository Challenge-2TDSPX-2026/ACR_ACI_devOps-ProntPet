package br.com.project.prontpet.services;

import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import br.com.project.prontpet.repositories.PetRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Optional<Pet> getByOwner(Owner owner, Pageable pageable){
        petRepository.findByOwnerContainingIgnoreCase(owner, pageable);
    }

    public Pet addPet(Pet pet){
        return petRepository.save(pet);
    }


}
