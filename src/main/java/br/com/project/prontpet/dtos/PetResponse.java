package br.com.project.prontpet.dtos;

import br.com.project.prontpet.enums.Sex;
import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;

public record PetResponse(

        Long id,
        String name,
        String species,
        String race,
        Integer age,
        Double weight,
        Sex sex,
        Owner owner
) {
    public static PetResponse fromEntity(Pet p){
        return new PetResponse(
                p.getId(),
                p.getName(),
                p.getSpecies(),
                p.getBreed(),
                p.getAge(),
                p.getWeight(),
                p.getSex(),
                p.getOwner()
        );
    }
}
