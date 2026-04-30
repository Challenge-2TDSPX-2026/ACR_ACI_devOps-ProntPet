package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Pet;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PetRequest(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "species is required")
        String species,

        @NotBlank(message = "race is required")
        String race,

        @Min(value = 0, message = "the age must be greater or equals 0")
        Integer age,

        Long owner_id
        
) {
}
