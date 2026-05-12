package br.com.project.prontpet.controllers;


import br.com.project.prontpet.dtos.PetRequest;
import br.com.project.prontpet.dtos.PetResponse;
import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import br.com.project.prontpet.services.PetService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<Page<PetResponse>> getPets(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<PetResponse> pets = petService.getPets(pageable).map(PetResponse::fromEntity);
        return ResponseEntity.ok(pets);
    }


    @GetMapping(params = "species")
    public ResponseEntity<Page<PetResponse>> getPetsBySpecies(Pageable pageable, @RequestParam String species){
        Page<PetResponse> pets = petService.getBySpecies(species, pageable).map(PetResponse::fromEntity);
        return ResponseEntity.ok(pets);
    }

    @GetMapping(params = "breeds")
    public ResponseEntity<Page<PetResponse>> getPetsByBreeds(Pageable pageable, @RequestParam String breed){
        Page<PetResponse> pets = petService.getByBreed(breed, pageable).map(PetResponse::fromEntity);
        return ResponseEntity.ok(pets);
    }

    @GetMapping(params = "owners")
    public ResponseEntity<Page<PetResponse>> getPetsByOwners(Pageable pageable, @RequestParam Owner owner){
        Page<PetResponse> pets = petService.getByOwner(owner, pageable).map(PetResponse::fromEntity);
        return ResponseEntity.ok(pets);
    }

    @GetMapping(params = {"ages", "age2"})
    public ResponseEntity<Page<PetResponse>> getPetsByOwners(Pageable pageable, @RequestParam Integer age1, @RequestParam Integer age2){
        Page<PetResponse> pets = petService.getByAgeBetween(age1, age2, pageable).map(PetResponse::fromEntity);
        return ResponseEntity.ok(pets);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> getPetById(@PathVariable Long id){
        return petService.getPetById(id)
                .map((p) -> ResponseEntity.ok(PetResponse.fromEntity(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PetResponse> addPet (@Valid @RequestBody PetRequest petRequest){
        Pet pet = petService.addPet(petRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(PetResponse.fromEntity(pet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable Long id , @Valid @RequestBody PetRequest petRequest){
        Pet pet = petService.updatePet(id, petRequest.toEntity());
        return ResponseEntity.ok(PetResponse.fromEntity(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id){
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
