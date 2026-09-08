package br.com.project.prontpet.dtos;

import br.com.project.prontpet.enums.Sex;
import br.com.project.prontpet.models.Owner;
import br.com.project.prontpet.models.Pet;
import jakarta.validation.constraints.*;
import org.springframework.cglib.core.Local;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PetRequest(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "species is required")
        String species,

        @NotBlank(message = "race is required")
        String breed,

        @PastOrPresent(message = "the birth date might not be in the future")
        LocalDate birthDate,

        @NotNull
        @DecimalMin(value = "0.5", message = "the weight must be at least '0.5g'")
        BigDecimal weight,

        @NotNull(message = "sex is required")
        Sex sex,

        @NotNull
        Owner owner
) {
        public Pet toEntity(){
                return Pet.builder()
                        .name(name)
                        .species(species)
                        .breed(breed)
                        .birthDate(birthDate)
                        .weight(weight)
                        .sex(sex)
                        .owner(owner)
                        .build();
        }
}
