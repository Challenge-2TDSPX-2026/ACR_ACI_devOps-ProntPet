package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Clinic;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ClinicRequest(


        @NotBlank(message = "name is required")
        String name,

        @CNPJ(message = "the CNPJ must be valid")
        String CNPJ,

        @NotBlank(message = "address is required")
        String address,

        @Size(min = 11, max = 14, message = "the value must have between 11 and 14 characters")
        String phone,

        @NotBlank(message = "openingHousrs is required")
        LocalTime openingHours,

        @NotBlank(message = "closingHours is required")
        LocalTime closingHours
) {
        public Clinic toEntity(){
                return Clinic.builder()
                        .name(name)
                        .CNPJ(CNPJ)
                        .address(address)
                        .phone(phone)
                        .openingHours(openingHours)
                        .closingHours(closingHours)
                        .build();
        }
}
