package br.com.project.prontpet.services;

import br.com.project.prontpet.dtos.LoginRequest;
import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.repositories.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner addOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public Optional<Owner> getOwnerById(Long id){
        return ownerRepository.findById(id);
    }

    public Optional<Owner> getOwnerByEmail(String email){
        return ownerRepository.findByEmail(email);
    }

    public Owner login(LoginRequest loginRequest){
        Owner owner = ownerRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or passwor is invalids"));
        if (!loginRequest.password().matches(owner.getPassword())){throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password is invalids")}
        return owner;

    }


    public void deleteOwner(Long id){
        var optionalOwner = getOwnerById(id);
        if (optionalOwner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        ownerRepository.deleteById(id);
    }


}

