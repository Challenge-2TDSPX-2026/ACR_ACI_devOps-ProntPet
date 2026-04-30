package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Pet;

public record PetResponse(

        Long id,
        String name,
        String species,
        String race,
        Integer age
) {
    public PetResponse fromEntity(Pet p){
        return new PetResponse(
                p.getId(),
                p.getName(),
                p.getSpecies(),
                p.getRace(),
                p.getAge()
        );
    }
}
