package br.com.project.prontpet.controllers;


import br.com.project.prontpet.dtos.PetRequest;
import br.com.project.prontpet.dtos.PetResponse;
import br.com.project.prontpet.models.Pet;
import br.com.project.prontpet.services.PetService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
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
    public List<Pet> getPets(){
        return petService.getPets();
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
