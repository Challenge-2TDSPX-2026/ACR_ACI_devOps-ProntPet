package br.com.project.prontpet.dtos;

import br.com.project.prontpet.enums.Sex;
import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PetResponse(

        Long id,
        String name,
        String species,
        String race,
        LocalDate birthDate,
        BigDecimal weight,
        Sex sex,
        Long ownerId
) {
    public static PetResponse fromEntity(Pet p){
        return new PetResponse(
                p.getId(),
                p.getName(),
                p.getSpecies(),
                p.getBreed(),
                p.getBirthDate(),
                p.getWeight(),
                p.getSex(),
                p.getOwner() != null ? p.getOwner().getId() : null
        );
    }
}
