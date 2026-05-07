package br.com.project.prontpet.controllers;

import br.com.project.prontpet.dtos.LoginRequest;
import br.com.project.prontpet.dtos.LoginResponse;
import br.com.project.prontpet.dtos.OwnerRequest;
import br.com.project.prontpet.dtos.OwnerResponse;
import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Owner> getOwner(){
        return ownerService.getOwners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable Long id){
        return ownerService.getOwnerById(id)
                .map((o) -> ResponseEntity.ok(OwnerResponse.fromEntity(o)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginOwner(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse logined = ownerService.login(loginRequest);
        return ResponseEntity.ok(logined);
    }

    @PostMapping
    public ResponseEntity<OwnerResponse> addOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        Owner owner = ownerService.addOwner(ownerRequest.toEntity());
        return ResponseEntity.ok(OwnerResponse.fromEntity(owner));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(@PathVariable Long id, @Valid @RequestBody OwnerRequest ownerRequest){
        Owner owner = ownerService.updateOwner(id, ownerRequest.toEntity());
        return ResponseEntity.ok(OwnerResponse.fromEntity(owner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id){
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();

    }
}
