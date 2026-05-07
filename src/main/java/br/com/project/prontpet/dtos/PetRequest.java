package br.com.project.prontpet.dtos;

import br.com.project.prontpet.enums.Sex;
import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import jakarta.validation.constraints.*;

public record PetRequest(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "species is required")
        String species,

        @NotBlank(message = "race is required")
        String breed,

        @NotBlank
        @Min(value = 0, message = "the age must be greater or equals 0")
        Integer age,

        @NotBlank
        @DecimalMin(value = "0.5", message = "the weight must be at least '0.5g'")
        Double weight,

        Sex sex,

        @NotBlank
        Owner owner
) {
        public Pet toEntity(){
                return Pet.builder()
                        .name(name)
                        .species(species)
                        .breed(breed)
                        .age(age)
                        .weight(weight)
                        .sex(sex)
                        .owner(owner)
                        .build();
        }
}
